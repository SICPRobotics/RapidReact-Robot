package frc.robot.commands;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.CargoArm;

public class SimpleArmCommand extends CommandBase {
    private final CargoArm arm;
    private final double value;
    public SimpleArmCommand(CargoArm arm, double value) {
        this.arm = arm;
        this.value = value;
    }


    @Override
    public void initialize() {
        arm.setMotor(this.value);
    }
    @Override 
    public void end(boolean b){
        arm.turnOff();
    }
    @Override
    public boolean isFinished(){
        return false;
        //return !arm.getController().atSetpoint();
    }
}
