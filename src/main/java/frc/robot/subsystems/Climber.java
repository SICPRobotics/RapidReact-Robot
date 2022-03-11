package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import frc.robot.Constants;

public class Climber extends SimpleMotorSubsystem {
    
    private final WPI_TalonFX motor = new WPI_TalonFX(Constants.Climber.CLIMBER_MOTOR_ID);

    public Climber(){
        super();
        motor.setNeutralMode(NeutralMode.Brake);
        setMotorController(motor);
    }

    @Override
    public void setMotor(double velocity){
        setMotor(velocity);
    }
}
