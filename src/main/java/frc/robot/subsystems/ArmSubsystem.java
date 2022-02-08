package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.SubsystemBaseWrapper;

public class ArmSubsystem extends SubsystemBaseWrapper{ 

    private final VictorSP shoulder;
    private final VictorSP shoulderTwist;
    private final WPI_TalonSRX elbow;
    private final WPI_TalonSRX wrist;
    private final VictorSP claw;


    public ArmSubsystem(){
        shoulder = new VictorSP(1);
        shoulderTwist = new VictorSP(8);
        elbow = new WPI_TalonSRX(10);
        wrist = new WPI_TalonSRX(11);
        claw = new VictorSP(0);

        elbow.setNeutralMode(NeutralMode.Brake);
        wrist.setNeutralMode(NeutralMode.Brake);
    }

    


    public void twist(double velocity){
        shoulderTwist.set(velocity);
    }
    public void shoulder(double velocity){
        shoulder.set(velocity);
    }
    public void elbow(double velocity){
        elbow.set(velocity);
    }
    public void wrist(double velocity){
        wrist.set(velocity);
    }
    public void claw(double velocity){
        claw.set(velocity);
    }



}
