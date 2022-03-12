package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

public class Climber extends SubsystemBaseWrapper implements MotorSubsystem {
    
    private final WPI_TalonFX climberMotor;

    public Climber(){
        this.climberMotor = new WPI_TalonFX(Constants.Climber.CLIMBER_MOTOR_ID);
        climberMotor.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void setMotor(double velocity){
        System.out.println(this.climberMotor.toString());
        this.climberMotor.set(velocity);
    }
    public void turnOff(){
        this.climberMotor.set(0);
    }
}
