package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CargoArm;
import frc.robot.subsystems.Pidgey;

public abstract class SmartArmCommand extends CommandBase {
    public final Pidgey pidgey;
    public final CargoArm arm;
    public final double target;
    public double progress = 0;
    public SmartArmCommand(CargoArm arm, Pidgey pidgey, double target) {
        this.arm = arm;
        this.pidgey = pidgey;
        this.target = target;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        progress = target - pidgey.getArmRotation();
        arm.setMotor(getOutput());
    }

    public abstract double getOutput();
}
