package frc.robot.commands.arm;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;
import frc.robot.WillowMath;
import frc.robot.subsystems.CargoArm;
import frc.robot.subsystems.Pidgey;

public class UpArmCommand extends SmartArmCommand {

    private double deadZoneprogress = 0;
    private DoubleSupplier p,i;

    public UpArmCommand(CargoArm arm, Pidgey pidgey, DoubleSupplier p, DoubleSupplier i) {
        super(arm, pidgey, 60);
        this.p = p;
        this.i = i;
    }

    @Override
    public double getOutput() {
        return (deadZoneprogress * p.getAsDouble()) + getI();
    }
    private double getI(){
        return i.getAsDouble() * timer.get();
    }
    @Override
    public void execute() {
        deadZoneprogress = WillowMath.deadZoneValue(progress, Constants.Arm.ARM_DEAD_ZONE);
        if(deadZoneprogress == 0){
            timer.reset();
        }
        super.execute();
    }

    
}
