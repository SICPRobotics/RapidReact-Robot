package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * This is to be implemented by subsystems that essentially wrap around a motor.
 */
public interface MotorSubsystem extends Subsystem {
    public void setMotor(double value);
    public void turnOff();
    default public boolean canTurn(double direction) {
        return true;
    }
}