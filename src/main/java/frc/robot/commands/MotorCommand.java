package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MotorSubsystem;

public class MotorCommand extends CommandBase{
    private final MotorSubsystem motorSubsystem;
    private final double velocity;
    public MotorCommand(MotorSubsystem motorSubsystem, double velocity){
        this.motorSubsystem = motorSubsystem;
        System.out.println("Constructor : " +  motorSubsystem.toString());
        this.velocity = velocity;
        addRequirements(motorSubsystem);
    }
    
    @Override
    public void initialize() {
        System.out.println(motorSubsystem.toString() + ": " + this.velocity);
        this.motorSubsystem.setMotor(velocity);
    }

    @Override
    public void end(boolean interrupted) {
        this.motorSubsystem.turnOff();
    }
    @Override
    public boolean isFinished() {
        return false;
    }
}
