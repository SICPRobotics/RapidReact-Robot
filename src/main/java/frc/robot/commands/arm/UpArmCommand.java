package frc.robot.commands.arm;

import frc.robot.subsystems.CargoArm;
import frc.robot.subsystems.Pidgey;

public class UpArmCommand extends SmartArmCommand {

    public UpArmCommand(CargoArm arm, Pidgey pidgey) {
        super(arm, pidgey, 60);
    }

    @Override
    public double getOutput() {
        if (progress < 0.2) {
            return 1;
        } else {
            return 0.4;
        }
    }
    
}
