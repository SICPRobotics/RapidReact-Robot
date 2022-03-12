package frc.robot.commands.arm;

import frc.robot.subsystems.CargoArm;
import frc.robot.subsystems.Pidgey;

public class DownArmCommand extends SmartArmCommand {

    public DownArmCommand(CargoArm arm, Pidgey pidgey) {
        super(arm, pidgey, 0);
    }

    @Override
    public double getOutput() {
        return 0;
    }
    
}
