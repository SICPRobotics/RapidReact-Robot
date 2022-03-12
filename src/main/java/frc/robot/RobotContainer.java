/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//simport edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.MotorCommand;
import frc.robot.commands.arm.SimpleArmCommand;
import frc.robot.controllers.joystick.Joystick;
import frc.robot.controllers.operator.OperatorController;
import frc.robot.subsystems.CargoArm;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.Pidgey;
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
    private final OperatorController operator = new OperatorController(1);
    private final DriveTrain driveTrain;
    private final CargoArm cargoArm;
    private final CargoIntake cargoIntake;
    private final Climber climber;
    private final Pidgey pidgey;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        CameraServer.startAutomaticCapture();
        driveTrain = new DriveTrain();
        cargoArm = new CargoArm();
        cargoIntake = new CargoIntake();
        climber = new Climber();
        pidgey = new Pidgey();

            
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
       
        operator.buttons.RB.whileHeld(new MotorCommand(climber, -1));
        operator.buttons.LB.whileHeld(new MotorCommand(climber,  1));

        operator.buttons.dPad.up.whileHeld(new SimpleArmCommand(cargoArm, 0.4));
        operator.buttons.dPad.down.whileHeld(new SimpleArmCommand(cargoArm, -0.4));
        cargoArm.setDefaultCommand(new RunCommand(() -> cargoArm.setMotor(operator.sticks.left.getY() * 0.4), cargoArm));

        operator.buttons.Y.whileHeld(new MotorCommand(cargoIntake,  1));
        operator.buttons.A.whileHeld(new MotorCommand(cargoIntake, -1));
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