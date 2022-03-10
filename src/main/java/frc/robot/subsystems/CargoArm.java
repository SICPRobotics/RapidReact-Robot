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

public class CargoArm extends PIDSubsystem implements MotorSubsystem{

    private final CANSparkMax cargoArm = new CANSparkMax(Constants.Arm.ARM_MOTOR_ID, MotorType.kBrushless);
    private final PigeonIMU gyro = new PigeonIMU(10);
    private final SimpleMotorFeedforward shooterFeedforward = new SimpleMotorFeedforward(2, 2, 0);


    public CargoArm() {
        super(new PIDController(0.1, 1, 0));
        this.m_controller.setTolerance(0.1, 0.1);
        this.cargoArm.setIdleMode(IdleMode.kBrake);
    }
    @Override
    protected void useOutput(double output, double setpoint) {
        this.cargoArm.setVoltage(output + shooterFeedforward.calculate(setpoint));  
    }
    @Override
    public void periodic() {
        super.periodic();
        SmartDashboard.putNumber("ArmRoll", this.gyro.getRoll());        
        SmartDashboard.putNumber("ArmPitch", this.gyro.getPitch());
        SmartDashboard.putNumber("ArmYaw", this.gyro.getYaw());
    }
    public void setMotor(double value) {
        this.setSetpoint(value);
        this.enable();     
    }
    public double getRoll(){
        return this.gyro.getRoll();
    }
    @Override
    protected double getMeasurement() {
        return this.gyro.getRoll();
    }
    @Override
    public void turnOff() {
        this.disable();
        this.cargoArm.set(0);
    }


}
