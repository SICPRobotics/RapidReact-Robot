package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.rumble.Rumbler;
import frc.robot.subsystems.MotorSubsystem;

public class MotorCommand extends CommandBase {
    private final MotorSubsystem motorSubsystem;
    private final double velocity;
    public MotorCommand(MotorSubsystem motorSubsystem, double velocity){
        this.motorSubsystem = motorSubsystem;
        this.velocity = velocity;
        addRequirements(motorSubsystem);
    }
    
    @Override
    public void initialize() {
        if (motorSubsystem.canTurn(velocity)) {
            this.motorSubsystem.setMotor(velocity);
        } else {
            Rumbler.rumble(1, 0.1);
        }
        
    }

    @Override
    public void end(boolean interrupted) {
        this.motorSubsystem.turnOff();

        if (!motorSubsystem.canTurn(velocity)) {
            Rumbler.rumble(1, 0.1);
        }
    }

    @Override
    public boolean isFinished() {
        return !motorSubsystem.canTurn(velocity);
    }
}
