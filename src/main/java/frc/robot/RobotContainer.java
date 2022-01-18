/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
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
import frc.robot.commands.DriveWithJoystick;
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
    private final JoystickButton thumbButton;
    private final JoystickButton twelveButton;
    private final JoystickButton trigger;
    private final JoystickButton threeButton;
    private final JoystickButton five;
    private final JoystickButton six;
    private final JoystickButton eleven;
    private final JoystickButton four;
    private final JoystickButton ten;
    private final JoystickButton nine;
    private final JoystickButton eight;


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        driveTrain = new DriveTrain();

            
        driveTrain.setDefaultCommand(
            new DriveWithJoystick(driveTrain, this::getJoystickY, this::getJoystickX, this::getJoystickAdjust, true));

        thumbButton = new JoystickButton(joystick, 2);
        twelveButton = new JoystickButton(joystick, 12);
        trigger = new JoystickButton(joystick, 1);
        threeButton = new JoystickButton(joystick, 3);
        five = new JoystickButton(joystick, 5);
        six = new JoystickButton(joystick, 6);
        eleven = new JoystickButton(joystick, 11);
        four = new JoystickButton(joystick, 4);
        ten = new JoystickButton(joystick, 10);
        nine = new JoystickButton(joystick, 9);
        eight = new JoystickButton(joystick, 8);
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
        thumbButton.toggleWhenPressed(
            new DriveWithJoystick(driveTrain, this::getJoystickY, this::getJoystickX, this::getJoystickAdjust, false));
    }
    
    public void motorSubsystemButton(Button jB, MotorSubsystem subsystem, double velocity, boolean toggle) {
        if(toggle){
            jB.toggleWhenPressed(new FunctionalCommand(() -> subsystem.turnOn(velocity), () -> {}, (b) -> subsystem.turnOff(), () -> false, subsystem));
        }
        else{
            jB.whileHeld(new FunctionalCommand(() -> subsystem.turnOn(velocity), () -> {}, (b) -> subsystem.turnOff(), () -> false, subsystem));
        }
    }   
    public double getJoystickX() {
        return this.joystick.getRawAxis(Constants.Joystick.X_AXIS);
    }
    public double getJoystickY() {
        return -this.joystick.getRawAxis(Constants.Joystick.Y_AXIS);
    }
    public double getJoystickZ() {
        return this.joystick.getRawAxis(2);
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
        return null;
    }
    
}