package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CargoArm;

public class ArmCommand extends CommandBase {
    private final CargoArm arm;
    private final double amount;
    private final Timer timer = new Timer();
    public ArmCommand(CargoArm arm, double amount) {
        this.arm = arm;
        addRequirements(arm);
        this.amount = amount;
    }

    @Override
    public void initialize () {
        timer.reset();
        timer.start();
        arm.setMotor(amount);
    }
    @Override 
    public void end(boolean b){
        arm.turnOff();
    }
    @Override
    public boolean isFinished(){
        return timer.get() > 5;
    }
}
