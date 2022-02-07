/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
//simport edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.Constants.Climber;
import frc.robot.commands.ArmHoldY;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.controllers.OperatorController;
import frc.robot.controllers.WolfbyteButton;
import frc.robot.controllers.WolfbyteJoystick;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.MotorSubsystem;
/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public final class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final WolfbyteJoystick joystick;
    private final DriveTrain driveTrain;
    private final ClimbSubsystem climber;
    private final ArmSubsystem arm;
    private final TrajectoryGeneration trajectoryGeneration = new TrajectoryGeneration();
    private final GsonSaver gsonSaver;
    private final OperatorController controller = new OperatorController(1);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        driveTrain = new DriveTrain();
        joystick = new WolfbyteJoystick(0);
        climber = new ClimbSubsystem();
        gsonSaver = new GsonSaver();
        arm = new ArmSubsystem();
        trajectoryGeneration.addGson(gsonSaver);
        driveTrain.setDefaultCommand(
            new DriveWithJoystick(driveTrain, this::getJoystickY, this::getJoystickX, this::getJoystickAdjust, true, Constants.DriveTrain.ControlSystems.ARCADE_DRIVE_STANDARD));
        
            /*arm.setDefaultCommand(
            new FunctionalCommand(() -> arm.twist(joystick.getJoystickZ() * 0.5), () -> {}, (b) -> {}, () -> false, arm)
        );*/

        arm.setDefaultCommand(new FunctionalCommand(() -> {}, () -> {
            arm.claw(controller.triggers.right.get() - controller.triggers.left.get());
            arm.elbow(controller.sticks.right.getY());
            arm.shoulder(controller.sticks.left.getY());
        }, b -> {}, () -> false, arm));
        // Configure the button bindings
        configureButtonBindings();
        //SmartDashboard.putNumber("Auton Chooser", 0);
        trajectoryGeneration.generate(new Pose2d(new Translation2d(0,0), new Rotation2d(0)), List.of(
            new Translation2d(2,1),
            new Translation2d(5, -1)
        ), new Pose2d(new Translation2d(7,0), new Rotation2d(0)), new TrajectoryConfig(4, 2), "test");
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
    //    joystick.wolfbyteButtons[6].setButtonCommand(arm, new FunctionalCommand(() -> arm.claw(0.1), () -> {}, (b) -> {}, () -> false, arm), WolfbyteJoystick.HELD_BUTTON);
    //    joystick.wolfbyteButtons[4].setButtonCommand(arm, new FunctionalCommand(() -> arm.claw(-0.1), () -> {}, (b) -> {}, () -> false, arm), WolfbyteJoystick.HELD_BUTTON);
       joystick.wolfbyteButtons[1].setButtonCommand(climber, new FunctionalCommand(() -> climber.lock(0), () -> {}, (b) -> {}, () -> false, arm), WolfbyteJoystick.HELD_BUTTON);
       joystick.wolfbyteButtons[7].setButtonCommand(climber, new FunctionalCommand(() -> climber.lock(1), () -> {}, (b) -> {}, () -> false, arm), WolfbyteJoystick.HELD_BUTTON);
       joystick.wolfbyteButtons[5].setDoubleButtonSignedCommand(arm, joystick.wolfbyteButtons[3], new ArmHoldY(arm, 1), WolfbyteJoystick.HELD_BUTTON);
       joystick.wolfbyteButtons[11].setDoubleButtonMotorCommand(climber, joystick.wolfbyteButtons[12], 0.5, WolfbyteJoystick.HELD_BUTTON);
       joystick.wolfbyteButtons[10].setButtonMotorCommand(driveTrain, 1, WolfbyteJoystick.HELD_BUTTON);
       joystick.wolfbyteButtons[9].setButtonCommand(driveTrain, new FunctionalCommand(() -> driveTrain.reset(), () -> {}, (b) -> {}, () -> false, driveTrain), WolfbyteJoystick.HELD_BUTTON);
       
       
       joystick.setDriveTrainCommandButton(driveTrain, 8, Constants.DriveTrain.ControlSystems.TANK_DRIVE_WITH_VOLTS, false, WolfbyteJoystick.TOGGLE_BUTTON);
       joystick.setDriveTrainCommandButton(driveTrain, 2, Constants.DriveTrain.ControlSystems.ARCADE_DRIVE_STANDARD, false, WolfbyteJoystick.TOGGLE_BUTTON);
    
    }
    
    // public void motorSubsystemButton(Button jB, MotorSubsystem subsystem, double velocity, boolean toggle) {
    //     if(toggle){
    //         jB.toggleWhenPressed(new FunctionalCommand(() -> subsystem.turnOn(velocity), () -> {}, (b) -> subsystem.turnOff(), () -> false, subsystem));
    //     }
    //     else{
    //         jB.whileHeld(new FunctionalCommand(() -> subsystem.turnOn(velocity), () -> {}, (b) -> subsystem.turnOff(), () -> false, subsystem));
    //     }
    // }   
    public double getJoystickX() {
        return this.joystick.getRawAxis(Constants.Joystick.X_AXIS);
    }
    public double getJoystickY() {
        return -this.joystick.getRawAxis(Constants.Joystick.Y_AXIS);
    }
    public double getJoystickZ() {
        return this.joystick.getRawAxis(Constants.Joystick.Z_AXIS);
    }
    public double getJoystickAdjust() {
        return this.joystick.getRawAxis(Constants.Joystick.ADJUST_AXIS);
    }
    // public void trajectory(TrajectoryGeneration trajectoryGeneration, DriveTrain driveTrain, Pose2d ){
    //   trajectoryGeneration = new TrajectoryGeneration(driveTrain.getPose(),
    //         new Pose2d(new Translation2d(0, 2), new Rotation2d(Math.PI/2)), 
    //         List.of(new Translation2d(0,1)), driveTrain);
    // }
    // * @return the command to run in autonomous
    public Command getAutonomousCommand() {
        return trajectoryGeneration.getTrajectoryCommand(driveTrain, "test");
    }
    
}