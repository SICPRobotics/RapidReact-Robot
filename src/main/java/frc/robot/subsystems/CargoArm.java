package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.SubsystemBaseWrapper;

public class CargoArm extends SubsystemBaseWrapper implements MotorSubsystem {
    private final CANSparkMax motor = new CANSparkMax(5, MotorType.kBrushless);
    public void turnOn (double velocity){
        motor.set(velocity);
    }
    public void turnOff (){
        motor.set(0);
    }
    public void setMotor (double value){
        motor.set (value);
    }
}
