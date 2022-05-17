/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import com.google.gson.Gson;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//simport edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.commands.drive.DriveWithJoystick;
import frc.robot.commands.drive.SwerveWithJoystick;
import frc.robot.commands.MotorCommand;
import frc.robot.commands.ResetClimber;
import frc.robot.commands.arm.DownArmCommand;
import frc.robot.commands.arm.SimpleArmCommand;
import frc.robot.commands.drive.DriveWithJoystick;
import frc.robot.commands.arm.UpArmCommand;
import frc.robot.commands.rumble.Rumbler;
import frc.robot.controllers.joystick.Joystick;
import frc.robot.controllers.operator.OperatorController;
import frc.robot.subsystems.CargoArm;
import frc.robot.commands.MotorCommand;
import frc.robot.controllers.joystick.Joystick;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ClimberPivot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.Pidgey;
import frc.robot.subsystems.SDriveTrain;
/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public final class RobotContainer {
    public static final Gson gson = new Gson();
    public final Joystick joystick;
    public final SDriveTrain sDriveTrain;

    //public final TrajectoryGeneration trajectoryGeneration = new TrajectoryGeneration();
    public final GsonSaver gsonSaver;
    public final OperatorController operator = new OperatorController(1);
    public final CargoArm cargoArm;
    public final CargoIntake cargoIntake;
    //public SmartDashBoardClass<Double> autoVersion, autoDelay;
    public final Climber climber;
    public final Pidgey pidgey;
    private final ClimberPivot climberPivot;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        CameraServer.startAutomaticCapture();
        Rumbler.setOperator(operator);
        pidgey = new Pidgey();
        sDriveTrain = new SDriveTrain(pidgey);
        joystick = new Joystick(0);
        gsonSaver = new GsonSaver();
        cargoArm = new CargoArm();
        cargoIntake = new CargoIntake();
        // autoVersion = new SmartDashBoardClass<Double>("autoVersion", 0.0);
        // autoDelay = new SmartDashBoardClass<Double>("autoDelay", 0.0);
        //trajectoryGeneration.addGson(gsonSaver);
        climber = new Climber();
        climberPivot = new ClimberPivot();

            
            sDriveTrain.setDefaultCommand(
            new SwerveWithJoystick(sDriveTrain, this::getX, this::getY, joystick::getZ, joystick::getScale, false));

        // Configure the button bindings
        configureButtonBindings();
        //SmartDashboard.putNumber("Auton Chooser", 0);
        // trajectoryGeneration.generate(new Pose2d(new Translation2d(0,0), new Rotation2d(0)), List.of(
        //     new Translation2d(2,1),
        //     new Translation2d(5, -1)
        // ), 
        // new Pose2d(new Translation2d(7,0), new Rotation2d(0)), 
        // new TrajectoryConfig(4, 2), "test");
        // trajectoryGeneration.generate(new Pose2d(new Translation2d(0,0), new Rotation2d(0)), List.of(
        //     new Translation2d(1,2),
        //     new Translation2d(3, 3)
        // ), 
        // new Pose2d(new Translation2d(7,0), new Rotation2d(0)),
        //  new TrajectoryConfig(4, 2), "nottest");
        //trajectoryGeneration.printTrajectory("test");
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        joystick.thumb.toggleWhenPressed(
            new SwerveWithJoystick(sDriveTrain, this::getX, this::getY, joystick::getZ, joystick::getScale, true));
       
        operator.buttons.RB.whileHeld(new MotorCommand(cargoIntake, -0.8));
        operator.buttons.LB.whileHeld(new MotorCommand(cargoIntake,  1));

        operator.buttons.dPad.up.whileHeld(new UpArmCommand(cargoArm, pidgey));
        operator.buttons.dPad.down.whileHeld(new DownArmCommand(cargoArm, pidgey));
        cargoArm.setDefaultCommand(new RunCommand(() -> cargoArm.setMotor(operator.sticks.left.getY() * 0.5), cargoArm));

        operator.buttons.Y.whileHeld(new MotorCommand(climber,  1));
        operator.buttons.A.whileHeld(new MotorCommand(climber, -1, true));

        operator.buttons.B.whileHeld(new MotorCommand(climberPivot, -0.2));
        operator.buttons.X.whileHeld(new MotorCommand(climberPivot, 0.2));
        
        operator.buttons.start.whenPressed(new ResetClimber(climber));

        operator.buttons.back.whileHeld(new FunctionalCommand(() -> climber.override = true, () -> {}, (b) -> climber.override = false, () -> false));
    }

    public double getY() {
        double joystickY = joystick.getY();
        double operatorY = -operator.sticks.right.getY();
       // System.out.println("Joystick: " + joystickY + " Operator: " + operatorY);

        if (Math.abs(joystickY) > Math.abs(operatorY)) {
            return joystickY;
        } else {
            return operatorY;
        }
    }

    public double getX() {
        double joystickX = joystick.getX();
        double operatorX = operator.sticks.right.getX();

        if (Math.abs(joystickX) > Math.abs(operatorX)) {
            return joystickX;
        } else {
            return operatorX;
        }
    }

    // public void trajectory(TrajectoryGeneration trajectoryGeneration, DriveTrain driveTrain, Pose2d ){
    //   trajectoryGeneration = new TrajectoryGeneration(driveTrain.getPose(),
    //         new Pose2d(new Translation2d(0, 2), new Rotation2d(Math.PI/2)), 
    //         List.of(new Translation2d(0,1)), driveTrain);
    // }
    // * @return the command to run in autonomous
    public Command getAutonomousCommand() {
        // Command auto;
        // String key = "useCustomAuto";

        // if (!SmartDashboard.containsKey(key)) {
        //     SmartDashboard.putBoolean(key, false);
        // }

        // SmartDashboard.setPersistent(key);

        // if (SmartDashboard.getBoolean("useCustomAuto", false)) {
        //     auto = new CustomAuto(this);
        // } else {
        //     auto = new OldAutoCommand(driveTrain, cargoArm, cargoIntake, this.autoVersion.getValue().intValue(), this.autoDelay.getValue().doubleValue());
        // }
        
        // return new ParallelCommandGroup(
        //     auto,
        //     new ResetClimber(climber)
        // );
        return null;
    }
    
}