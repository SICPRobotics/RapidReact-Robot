package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.PistonSubsystem;

public class WolfbyteJoystick extends Joystick{

    JoystickButton[] joystickButtons  = new JoystickButton[12];
    public static final boolean TOGGLE_BUTTON = true;
    public static final boolean HELD_BUTTON = false;

    public WolfbyteJoystick(int port) {
        super(port);
        for(int i = 0; i < 13; i++){
            joystickButtons[i] = new JoystickButton(this, i);
        }
    }

    public void setButtonMotorCommand(MotorSubsystem motorSubsystem, int button, double velocity, boolean toggle){
        if(toggle){
            joystickButtons[button].toggleWhenPressed(new FunctionalCommand(() -> motorSubsystem.turnOn(velocity), () -> {}, (b) -> motorSubsystem.turnOff(), () -> false, motorSubsystem));
        }
        else{
            joystickButtons[button].whileHeld(new FunctionalCommand(() -> motorSubsystem.turnOn(velocity), () -> {}, (b) -> motorSubsystem.turnOff(), () -> false, motorSubsystem));
        }
    }
    public void setButtonPistonCommand(PistonSubsystem pistonSubsystem, int button, boolean toggle){
        if(toggle){
            joystickButtons[button].toggleWhenPressed(new FunctionalCommand(() -> pistonSubsystem.pistonForward(), () -> {}, (b) -> pistonSubsystem.pistonReverse(), () -> false, pistonSubsystem));
        }
        else{
            joystickButtons[button].whileHeld(new FunctionalCommand(() -> pistonSubsystem.pistonForward(), () -> {}, (b) -> pistonSubsystem.pistonReverse(), () -> false, pistonSubsystem));
        }
    } 
    public void setButtonCommand(Subsystem subsystem, int button, Command command, boolean toggle){
        if(toggle){
            joystickButtons[button].toggleWhenPressed(command);
        }
        else{
            joystickButtons[button].whileHeld(command);
        }
    } 
    
}
