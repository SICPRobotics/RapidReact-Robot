package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.SmartDashBoardClass;

public class CargoIntake extends SimpleMotorSubsystem{

    WPI_TalonSRX intakeMotor = new WPI_TalonSRX(Constants.Arm.INTAKE_MOTOR_ID);
    private final SmartDashBoardClass<Double> intakeValue;
    public CargoIntake(){
        super(); 
        setMotorController(this.intakeMotor);
        intakeValue = new SmartDashBoardClass<Double>("intakeValue", 0.0);
        SmartDashboard.putNumber("intakeValue", 0);
    }
    @Override
    public void setMotor(double value) {
        super.setMotor(this.intakeValue.getValue());
    }
}
