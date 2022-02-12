package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import frc.robot.Constants;

public class ClimbSubsystem implements MotorSubsystem{
    
    WPI_TalonSRX climbMotor;
    public Servo lock;

    public ClimbSubsystem(){
        climbMotor = new WPI_TalonSRX(Constants.Climber.CLIMBER_MOTOR_ID);
        climbMotor.setNeutralMode(NeutralMode.Brake);
        lock = new Servo(7);
    }
    
    public void setMotor(double value){
        climbMotor.set(ControlMode.PercentOutput, value);
    }
    public void turnOn(double velocity){
        this.setMotor(velocity);
    }
    public void turnOff(){
        this.setMotor(0);
    }
    public void lock(double position){
        this.lock.set(position);
    }

    public void setRachetServoVelocity(double velocity) {
        lock.setSpeed(velocity);
    }
}
