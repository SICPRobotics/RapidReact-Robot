/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.GenericHID;
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
import frc.robot.commands.NudgeServo;
import frc.robot.controllers.operator.OperatorController;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CargoArm;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.controllers.joystick.Joystick;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.SimpleMotorSubsystem;
/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public final class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final Joystick joystick;
    private final DriveTrain driveTrain;
    private final ClimbSubsystem climber;
    private final ArmSubsystem arm;
    private final TrajectoryGeneration trajectoryGeneration = new TrajectoryGeneration();
    private final GsonSaver gsonSaver;
    private final OperatorController controller = new OperatorController(1);
    private final CargoArm cargoArm;
    private final Intake intake;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        driveTrain = new DriveTrain();
        joystick = new Joystick(0);
        climber = new ClimbSubsystem();
        gsonSaver = new GsonSaver();
        arm = new ArmSubsystem();
        cargoArm = new CargoArm();
        intake = new Intake();
        trajectoryGeneration.addGson(gsonSaver);
        driveTrain.setDefaultCommand(
            new DriveWithJoystick(driveTrain, joystick::getY, joystick::getX, joystick::getScale, false));

        // Configure the button bindings
        configureButtonBindings();
        //SmartDashboard.putNumber("Auton Chooser", 0);
        trajectoryGeneration.generate(new Pose2d(new Translation2d(0,0), new Rotation2d(0)), List.of(
            new Translation2d(2,1),
            new Translation2d(5, -1)
        ), 
        new Pose2d(new Translation2d(7,0), new Rotation2d(0)), 
        new TrajectoryConfig(4, 2), "test");
        trajectoryGeneration.generate(new Pose2d(new Translation2d(0,0), new Rotation2d(0)), List.of(
            new Translation2d(1,2),
            new Translation2d(3, 3)
        ), 
        new Pose2d(new Translation2d(7,0), new Rotation2d(0)),
         new TrajectoryConfig(4, 2), "nottest");
        trajectoryGeneration.printTrajectory("test");
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        joystick.thumb.toggleWhenPressed(
            new DriveWithJoystick(driveTrain, joystick::getY, joystick::getX, joystick::getScale, true));
        motorSubsystemButton(this.controller.buttons.A, this.cargoArm, 0.4, false);
        motorSubsystemButton(this.controller.buttons.B, this.cargoArm, -0.4, false);
        motorSubsystemButton(this.controller.buttons.X, this.intake, 0.6, true);
        motorSubsystemButton(this.controller.buttons.Y, this.intake, -0.6, true);
    }
    
    public void motorSubsystemButton(Button jB, MotorSubsystem subsystem, double velocity, boolean toggle) {
        if(toggle){
            jB.toggleWhenPressed(new FunctionalCommand(() -> subsystem.setMotor(velocity), () -> {}, (b) -> subsystem.turnOff(), () -> false, subsystem));
        }
        else{
            jB.whileHeld(new FunctionalCommand(() -> subsystem.setMotor(velocity), () -> {}, (b) -> subsystem.turnOff(), () -> false, subsystem));
        }
    }

    // public void trajectory(TrajectoryGeneration trajectoryGeneration, DriveTrain driveTrain, Pose2d ){
    //   trajectoryGeneration = new TrajectoryGeneration(driveTrain.getPose(),
    //         new Pose2d(new Translation2d(0, 2), new Rotation2d(Math.PI/2)), 
    //         List.of(new Translation2d(0,1)), driveTrain);
    // }
    // * @return the command to run in autonomous
    public Command getAutonomousCommand() {
        return trajectoryGeneration.getTrajectoryCommand(driveTrain, "nottest");
    }
    
}