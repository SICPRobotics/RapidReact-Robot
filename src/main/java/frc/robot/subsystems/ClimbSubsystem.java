package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;

public class ClimbSubsystem implements MotorSubsystem{
    
    WPI_TalonSRX climbMotor;

    public ClimbSubsystem(){
        climbMotor = new WPI_TalonSRX(Constants.Climber.CLIMBER_MOTOR_ID);
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
}
