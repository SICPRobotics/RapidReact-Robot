package frc.robot.controllers;

import java.util.function.DoubleConsumer;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.SignedCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.PistonSubsystem;

public class WolfbyteJoystick extends Joystick{

    public final WolfbyteButton[] wolfbyteButtons  = new WolfbyteButton[13];
    public static final boolean TOGGLE_BUTTON = true;
    public static final boolean HELD_BUTTON = false;

    public WolfbyteJoystick(int port) {
        super(port);
        for(int i = 0; i < 13; i++){
            wolfbyteButtons[i] = new WolfbyteButton(this, i + 1);
        }
    }

    public void setDriveTrainCommandButton(DriveTrain driveTrain, int button, int controlSystem, boolean isInverted, boolean toggle){
        if(toggle){
            wolfbyteButtons[button - 1].toggleWhenPressed(new DriveWithJoystick(driveTrain, this::getJoystickY, this::getJoystickX, this::getJoystickAdjust, isInverted, controlSystem));
        }
    }
    // public void setSimpleButtonCommand(Subsystem subsystem, int button, DoubleConsumer method, boolean toggle){
    //     if(toggle){
    //         joystickButtons[button - 1].toggleWhenPressed(new FunctionalCommand(() -> motorSubsystem.turnOn(velocity), () -> {}, (b) -> motorSubsystem.turnOff(), () -> false, motorSubsystem));
    //     }
    //     else{
    //         joystickButtons[button - 1].whileHeld(command);
    //     }
    // }
    public double getJoystickX() {
        return this.getRawAxis(Constants.Joystick.X_AXIS);
    }
    public double getJoystickY() {
        return -this.getRawAxis(Constants.Joystick.Y_AXIS);
    }
    public double getJoystickZ() {
        return this.getRawAxis(Constants.Joystick.Z_AXIS);
    }
    public double getJoystickAdjust() {
        return this.getRawAxis(Constants.Joystick.ADJUST_AXIS);
    }
    
}
