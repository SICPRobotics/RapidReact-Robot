package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.SubsystemBaseWrapper;

public class CargoIntake extends SubsystemBaseWrapper implements MotorSubsystem {
    private final WPI_TalonSRX motor = new WPI_TalonSRX(8);

    public void setMotor(double velocity) {
        motor.set(velocity);
    }

    public void turnOn(double velocity) {
        setMotor(velocity);
    }

    public void turnOff() {
        motor.set(0);
    }
}
