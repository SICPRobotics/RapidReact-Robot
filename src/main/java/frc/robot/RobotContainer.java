/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//simport edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.controllers.joystick.Joystick;
import frc.robot.subsystems.Climber;
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
    private final Joystick joystick = new Joystick(0);
    private final DriveTrain driveTrain;
    private final Climber climber = new Climber();


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        driveTrain = new DriveTrain();

            
        driveTrain.setDefaultCommand(
            new DriveWithJoystick(driveTrain, joystick::getY, joystick::getX, joystick::getScale, false));

        // Configure the button bindings
        configureButtonBindings();
        SmartDashboard.putNumber("Auton Chooser", 0);
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        joystick.thumb.toggleWhenPressed(
            new DriveWithJoystick(driveTrain, joystick::getY, joystick::getX, joystick::getScale, false));

        joystick.button(7).whileHeld(new FunctionalCommand(() -> {}, () -> climber.setRatchetServoPosition(climber.getRatchetServoPosition() + 0.005), b -> {}, () -> false, climber));
        joystick.button(8).whileHeld(new FunctionalCommand(() -> {}, () -> climber.setRatchetServoPosition(climber.getRatchetServoPosition() - 0.005), b -> {}, () -> false, climber));
    }
    
    public void motorSubsystemButton(Button jB, MotorSubsystem subsystem, double velocity, boolean toggle) {
        if(toggle){
            jB.toggleWhenPressed(new FunctionalCommand(() -> subsystem.turnOn(velocity), () -> {}, (b) -> subsystem.turnOff(), () -> false, subsystem));
        }
        else{
            jB.whileHeld(new FunctionalCommand(() -> subsystem.turnOn(velocity), () -> {}, (b) -> subsystem.turnOff(), () -> false, subsystem));
        }
    }

    // public void trajectory(TrajectoryGeneration trajectoryGeneration, DriveTrain driveTrain, Pose2d ){
    //   trajectoryGeneration = new TrajectoryGeneration(driveTrain.getPose(),
    //         new Pose2d(new Translation2d(0, 2), new Rotation2d(Math.PI/2)), 
    //         List.of(new Translation2d(0,1)), driveTrain);
    // }
    // * @return the command to run in autonomous
    public Command getAutonomousCommand() {
        return null;
    }
    
}