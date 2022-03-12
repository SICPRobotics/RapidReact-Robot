package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.Constants;

public class CargoArm implements MotorSubsystem{

    private final CANSparkMax armMotor;

    public CargoArm() {
        this.armMotor = new CANSparkMax(Constants.Arm.ARM_MOTOR_ID, MotorType.kBrushless);
        this.armMotor.setIdleMode(IdleMode.kBrake);
    }
    public void setMotor(double velocity){
        this.armMotor.set(velocity);
    }
    public void turnOff(){
        this.armMotor.set(0);
    }
}
