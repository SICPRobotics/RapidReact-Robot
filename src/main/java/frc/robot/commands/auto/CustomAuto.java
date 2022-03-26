package frc.robot.commands.auto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.commands.arm.DownArmCommand;
import frc.robot.commands.arm.UpArmCommand;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.drive.TurnRelative;

public class CustomAuto extends CommandBase {

    private List<CustomAutoStep> steps = new ArrayList<CustomAutoStep>();
    private int index = 0;
    private double currentStepStart = 0;
    private double currentStepEnd = 0;
    private Timer timer = new Timer();
    private RobotContainer robot;
    private CustomAutoState state;

    public CustomAuto(RobotContainer container) {
        this.robot = container;
    }

    @Override
    public void initialize() {
        index = 0;
        timer.reset();
        timer.start();

        state = CustomAutoState.createDefault();

        // Fetch and parse auto code on command start.
        NetworkTable autoTable = NetworkTableInstance.getDefault().getTable("WolfbyteAuto");
        String selectedAuto = autoTable.getEntry("SelectedAuto").getString("");
        String autosString = autoTable.getEntry("Autos").getString("{}");
        JsonObject autos = JsonParser.parseString(autosString).getAsJsonObject();
        JsonArray instructions = autos.get(selectedAuto).getAsJsonArray();
        
        double time = 0;
        for (JsonElement instruction : instructions) {
            CustomAutoStep step = parseCustomAutoStep(instruction.getAsJsonObject(), time);
            steps.add(step);
            time = step.endTime;
        }
    }

    private Command parseCustomAutoStep(JsonObject instruction, double startTime) {
        // Parse args:
        double _duration = 1;
        if (instruction.has("duration")) {
            _duration = instruction.get("duration").getAsDouble();
        }
        final double duration = _duration;

        double _speed = 0;
        if (instruction.has("speed")) {
            _speed = instruction.get("speed").getAsDouble();
        }
        final double speed = _speed;

        double _distance = 0;
        if (instruction.has("distance")) {
            _distance = instruction.get("distance").getAsDouble();
        }
        final double distance = _distance;

        double _rotation = 0;
        if(instruction.has("rotation")){
            _rotation = instruction.get("rotation").getAsDouble();
        }
        final double rotation = _rotation;
        
        String type = instruction.get("type").getAsString();
        Runnable start = () -> {};
        Runnable execute = () -> {};
        Runnable end = () -> {};
        BooleanSupplier isFinished = () -> state.time > duration;
        List<Subsystem> requirements = new ArrayList<Subsystem>();

        if (type.equals("diffDrive")) {
            double left = instruction.get("left").getAsDouble();
            double right = instruction.get("right").getAsDouble();
            execute = () -> robot.driveTrain.diffDrive(left, right);
        } else if (type.equals("driveDistance")) {
            execute = () -> new DriveDistance(robot.driveTrain, distance, speed);
        } else if (type.equals("driveDuration")) {
            execute = () -> robot.driveTrain.cheesyDrive(speed, 0);
        } else if (type.equals("wait")) {
            // Do nothing
        } else if (type.equals("relativeTurn")) {
            execute = () -> new TurnRelative(robot.driveTrain, robot.pidgey, rotation);
        } else if (type.equals("turnToHeading")) {
            double heading = instruction.get("heading").getAsDouble();
            execute = () -> new TurnRelative(robot.driveTrain, robot.pidgey, heading - robot.pidgey.getRobotAbsoluteHeading());
        } else if (type.equals("armUp")) {
            return new UpArmCommand(robot.cargoArm, robot.pidgey);
        } else if (type.equals("armDown")) {
            return new DownArmCommand(robot.cargoArm, robot.pidgey);
        } else if (type.equals("intake")) {
            String direction = instruction.get("direction").getAsString();
            if (direction.equals("in")) {
                start = () -> robot.cargoIntake.intakeIn();
            } else if (direction.equals("out")) {
                start = () -> robot.cargoIntake.intakeOut();
            } else {
                start = () -> robot.cargoIntake.intakeOff();
            }
        }

        return new FunctionalCommand(start, execute, b -> end.run(), isFinished, requirements.toArray(Subsystem[]::new));
    }

    @Override
    public void execute() {
        if (isFinished) {
            return;
        }
        
        final CustomAutoStep step = steps.get(index);
        final CustomAutoStep next = steps.size() - 1 > index ? steps.get(index + 1) : null;

        if (timer.get() > step.isFinished.apply()) {
            index++;
            if (next != null) {
                next.start.run();
            } else {
                isFinished = true;
            }
        } else if (timer.get() < step.startTime) {
            System.out.println("Invalid state on auto");
        } else {
            step.execute.accept(timer.get() - step.endTime);
        }
    }

    public boolean isFinished = false;
    public boolean isFinished() {
        return isFinished;
    }

}