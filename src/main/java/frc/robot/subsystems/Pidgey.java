package frc.robot.subsystems;

import com.ctre.phoenix.sensors.Pigeon2;

import frc.robot.SubsystemBaseWrapper;

public class Pidgey extends SubsystemBaseWrapper {
    public final Pigeon2 pigeon = new Pigeon2(10);

    public double getRotation() {
        return pigeon.getYaw();
    }

    @Override
    public void periodic() {
        System.out.println(getRotation());
    }
}
