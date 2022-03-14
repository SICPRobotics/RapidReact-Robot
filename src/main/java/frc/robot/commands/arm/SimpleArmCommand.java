package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CargoArm;

public class SimpleArmCommand extends CommandBase {
    private final CargoArm arm;
    private final double value;
    public SimpleArmCommand(CargoArm arm, double value) {
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
