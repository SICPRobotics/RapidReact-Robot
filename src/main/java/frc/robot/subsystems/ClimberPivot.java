package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

public class ClimberPivot extends SubsystemBaseWrapper implements MotorSubsystem {

    private TalonSRX pivotMotor;


    public ClimberPivot(){
        this.pivotMotor = new TalonSRX(Constants.Climber.PIVOT_MOTOR_ID);
    }




    @Override
    public void setMotor(double value) {
        this.pivotMotor.set(ControlMode.PercentOutput, value);
        
    }

    @Override
    public void turnOff() {
        this.pivotMotor.set(ControlMode.PercentOutput, 0);
        
    }
    
}
