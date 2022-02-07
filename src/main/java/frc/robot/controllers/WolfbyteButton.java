package frc.robot.controllers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.MotorCommand;
import frc.robot.commands.SignedCommand;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.PistonSubsystem;

public class WolfbyteButton extends JoystickButton{
 
    public WolfbyteButton(GenericHID joystick, int buttonNumber) {
        super(joystick, buttonNumber);
    }
    
    public void setButtonMotorCommand(MotorSubsystem motorSubsystem, double velocity, boolean toggle){
        if(toggle){
            this.toggleWhenPressed(new MotorCommand(motorSubsystem, velocity));
        }
        else{
            this.whileHeld(new MotorCommand(motorSubsystem, velocity));
        }
    }
    public void setButtonPistonCommand(PistonSubsystem pistonSubsystem, boolean toggle){
        if(toggle){
            this.toggleWhenPressed(new FunctionalCommand(() -> pistonSubsystem.pistonForward(), () -> {}, (b) -> pistonSubsystem.pistonReverse(), () -> false, pistonSubsystem));
        }
        else{
            this.whileHeld(new FunctionalCommand(() -> pistonSubsystem.pistonForward(), () -> {}, (b) -> pistonSubsystem.pistonReverse(), () -> false, pistonSubsystem));
        }
    } 
    public void setButtonCommand(Subsystem subsystem, Command command, boolean toggle){
        if(toggle){
            this.toggleWhenPressed(command);
        }
        else{
            this.whileHeld(command);
        }
    } 
    public void setDoubleButtonSignedCommand(Subsystem subsystem, WolfbyteButton wolfbyteButton, SignedCommand command, boolean toggle){
        this.setButtonCommand(subsystem, command, toggle);
        wolfbyteButton.setButtonCommand(subsystem, command.changeSign(), toggle);
    }
    public void setDoubleButtonMotorCommand(MotorSubsystem motorSubsystem, WolfbyteButton wolfbyteButton, double velocity, boolean toggle){
        this.setButtonMotorCommand(motorSubsystem, velocity, toggle);
        wolfbyteButton.setButtonMotorCommand(motorSubsystem, -velocity, toggle);
    }



}
