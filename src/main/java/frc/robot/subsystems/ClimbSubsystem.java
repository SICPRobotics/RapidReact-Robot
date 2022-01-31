package frc.robot.subsystems;

import frc.robot.Constants;

public class ClimbSubsystem implements MotorSubsystem{
    
    WPI_TalonSRX climbMotor;

    public ClimbSubsystem(){
        climbMotor = new WPI_TalonSRX(Constants.Climber.CLIMER_MOTOR_ID);
    }
    
    public void setMotor(double value){
        climbMotor.set()
    }
    public void turnOn(double velocity){

    }
    public void turnOff(){

    }
}
