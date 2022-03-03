package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake extends SimpleMotorSubsystem{

    WPI_TalonSRX intakeMotor = new WPI_TalonSRX(4);

    public Intake(){
        super();
        setMotorController(this.intakeMotor);
    }
}
