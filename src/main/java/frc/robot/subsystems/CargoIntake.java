package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;

public class CargoIntake extends SimpleMotorSubsystem{

    WPI_TalonSRX intakeMotor = new WPI_TalonSRX(Constants.Arm.INTAKE_MOTOR_ID);

    public CargoIntake(){
        super();
        setMotorController(this.intakeMotor);
    }
}
