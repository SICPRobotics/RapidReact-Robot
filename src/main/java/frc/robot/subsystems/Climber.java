package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import frc.robot.Constants;

public class Climber extends SimpleMotorSubsystem{
    
    private final WPI_TalonSRX CLimberMotor = new WPI_TalonSRX(Constants.Climber.CLIMBER_MOTOR_ID);
    private final Servo servo = new Servo(Constants.Climber.CLIMBER_SERVO_ID);
    private boolean locked;

    public Climber(){
        super();
        this.CLimberMotor.setNeutralMode(NeutralMode.Brake);
        setMotorController(this.CLimberMotor);
        locked = true;
    }
    @Override
    public void setMotor(double velocity){
        setMotor(velocity, () -> lockClimber(velocity > 0));
    }
    
    public void lockClimber(boolean lock){
        this.locked = lock;
        this.servo.set(lock ? 1 : 0);
    }
}
