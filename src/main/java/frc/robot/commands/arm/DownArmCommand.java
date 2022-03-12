package frc.robot.commands.arm;

import frc.robot.subsystems.CargoArm;
import frc.robot.subsystems.Pidgey;

public class DownArmCommand extends SmartArmCommand {

    public DownArmCommand(CargoArm arm, Pidgey pidgey) {
        super(arm, pidgey, 0);
    }

    @Override
    public double getOutput() {
        if (progress > 0.8) {
            return 0;
        } else {
            return -0.4;
        }
    }
    
}
