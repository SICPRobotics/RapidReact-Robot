package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

public class CargoArm extends SubsystemBaseWrapper implements MotorSubsystem{

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
