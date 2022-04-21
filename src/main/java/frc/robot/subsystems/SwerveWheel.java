package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.math.geometry.Translation2d;

public class SwerveWheel {

    private TalonFX driveMotor;
    private TalonFX steerMotor;
    private double setpoint = 0;
    private double steerVelocity = 0;
    private final double maxError = Math.PI * 2;
    private double P = 1;

    public SwerveWheel(int driveMotorId, int steerMotorId){
        this.driveMotor = new TalonFX(driveMotorId);
        this.steerMotor = new TalonFX(steerMotorId);
        motorInit(this.driveMotor);
        motorInit(this.steerMotor);
    }

    private void motorInit(TalonFX motor){
        motor.setNeutralMode(NeutralMode.Brake);
        motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        motor.setSelectedSensorPosition(0);
    }

    public void setDriveMotor(double velocity){
        this.driveMotor.set(TalonFXControlMode.PercentOutput, velocity);
    }
    public void setSteerMotor(double setpoint){
        this.setpoint = setpoint;
    }
    public double getDriveEncoder(){
        return this.driveMotor.getSelectedSensorPosition();
    }
    public double getSteerEncoder(){
        return this.steerMotor.getSelectedSensorPosition();
    }

    public void updateSteer(){
        double error = (this.setpoint - getSteerEncoder()) / this.maxError;
        this.steerMotor.set(TalonFXControlMode.PercentOutput, error * P);
    }



}
