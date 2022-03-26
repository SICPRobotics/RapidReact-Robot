package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CargoIntake extends SubsystemBase implements MotorSubsystem{

    WPI_TalonSRX intakeMotor;
    public CargoIntake(){
        this.intakeMotor = new WPI_TalonSRX(Constants.Arm.INTAKE_MOTOR_ID);
    }
    public void setMotor(double velocity){
        this.intakeMotor.set(velocity);
    }
    public void turnOff(){
        this.intakeMotor.set(0);
    }
    public void intakeIn() {
        setMotor(1);
    }

    public void intakeOut() {
        setMotor(-0.8);
    }

    public void intakeOff() {
        setMotor(0);
    }
}
