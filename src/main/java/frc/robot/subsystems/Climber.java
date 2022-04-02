package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

public class Climber extends SubsystemBaseWrapper implements MotorSubsystem {
    
    private final WPI_TalonFX climberMotor;

    // 318285 empirical value, rounded down for extra wiggle room
    private static final int maxEncoderHeight = 335_000;

    public Climber(){
        this.climberMotor = new WPI_TalonFX(Constants.Climber.CLIMBER_MOTOR_ID);
        climberMotor.setNeutralMode(NeutralMode.Brake);
        climberMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        resetEncoder();
    }

    @Override
    public void setMotor(double velocity) {
        setMotor(velocity, false);
    }

    public void setMotor(double velocity, boolean force){
        if (velocity == 0 || force || canTurn(velocity)) {
            this.climberMotor.set(velocity);
        }
    }

    @Override
    public boolean canTurn(double direction) {
        
        return true;
        
        // if (direction > 0) {
        //     return getEncoderPosition() < maxEncoderHeight;
        // } else {
        //     return getEncoderPosition() > 0;
        // }
    }

    @Override
    public void turnOff(){
        this.climberMotor.set(0);
    }

    public int getEncoderPosition() {
        return (int) climberMotor.getSelectedSensorPosition();
    }

    public int getEncoderVelocity() {
        return (int) climberMotor.getSelectedSensorVelocity();
    }

    public void resetEncoder() {
        climberMotor.setSelectedSensorPosition(0);
    }
}
