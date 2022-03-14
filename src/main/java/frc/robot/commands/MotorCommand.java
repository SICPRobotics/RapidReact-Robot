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
    public void end(boolean interrupted) {
        this.motorSubsystem.turnOff();

        if (!motorSubsystem.canTurn(velocity)) {
        }
    }

    @Override
    public boolean isFinished() {
        return !motorSubsystem.canTurn(velocity);
    }
}
