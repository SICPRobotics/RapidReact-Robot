package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.Constants;

public class CargoArm extends SimpleMotorSubsystem{

    private final CANSparkMax cargoArm = new CANSparkMax(Constants.Arm.ARM_MOTOR_ID, MotorType.kBrushless);

    public CargoArm() {
        super();
        this.setMotorController(this.cargoArm);
        this.getMotorController(this.cargoArm.getClass()).setIdleMode(IdleMode.kBrake);
    }
}
