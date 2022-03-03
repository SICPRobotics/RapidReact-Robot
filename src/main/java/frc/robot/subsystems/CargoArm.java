package frc.robot.subsystems;

<<<<<<< Updated upstream
=======
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
>>>>>>> Stashed changes
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

<<<<<<< Updated upstream
import frc.robot.SubsystemBaseWrapper;

public class CargoArm extends SubsystemBaseWrapper implements MotorSubsystem {
    private final CANSparkMax motor = new CANSparkMax(5, MotorType.kBrushless);
    public CargoArm() {
        super();
        motor.setIdleMode(IdleMode.kBrake);
    }

    public void turnOn (double velocity){
        motor.set(velocity);
    }
    public void turnOff (){
        motor.set(0);
    }
    public void setMotor (double value){
        motor.set (value);
    }
=======
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.Constants;

public class CargoArm extends SimpleMotorSubsystem{

    private final CANSparkMax cargoArm = new CANSparkMax(5, MotorType.kBrushless);

    public CargoArm() {
        super();
        this.setMotorController(this.cargoArm);
        this.getMotorController(this.cargoArm.getClass()).setIdleMode(IdleMode.kBrake);
    }
    
>>>>>>> Stashed changes
}
