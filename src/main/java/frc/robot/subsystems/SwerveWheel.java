package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;

public class SwerveWheel {

    private TalonFX driveMotor;
    private TalonFX steerMotor;
    private double setpoint = 0;
    private double steerVelocity = 0;
    private final double maxError = Math.PI * 2;
    private double P = 1;
    private double I = 0.25;
    private Timer timer = new Timer();

    public SwerveWheel(int driveMotorId, int steerMotorId){
        this.driveMotor = new TalonFX(driveMotorId);
        this.steerMotor = new TalonFX(steerMotorId);
        motorInit(this.driveMotor);
        motorInit(this.steerMotor);
        this.timer.reset();
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
        this.timer.start();
    }
    public double getDriveEncoder(){
        return this.driveMotor.getSelectedSensorPosition();
    }
    public double getSteerEncoder(){
        return this.steerMotor.getSelectedSensorPosition() * (4096/2) * Math.PI;
    }
    public double getSetpoint() {
        return setpoint;
    }

    public void updateSteer(){
        double error = (this.setpoint - getSteerEncoder()) / this.maxError; 
        if(error == 0){
            this.timer.reset();
        }
        double iError = this.timer.get() * error;
        this.steerMotor.set(TalonFXControlMode.PercentOutput, (error * P) + (iError * I)); // PI Controller 
    }



}
