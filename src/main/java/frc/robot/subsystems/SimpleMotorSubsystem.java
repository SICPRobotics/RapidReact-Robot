package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class SimpleMotorSubsystem implements MotorSubsystem{

    private MotorController motorController;
    private DoubleSupplier sensor;
    public SimpleMotorSubsystem(){}
    public SimpleMotorSubsystem(MotorController motorController){
        this.motorController = motorController;
    }
    public SimpleMotorSubsystem(MotorController motorController, DoubleSupplier sensor){
        this.motorController = motorController;
        this.sensor = sensor;
    }
    public void setMotorController(MotorController motorController){
        System.out.println("RUNNING");
        this.motorController = motorController;
    }
    public void setSensor(DoubleSupplier sensor){
        this.sensor = sensor;
    }
    public double getsensorReading(){
        return this.sensor.getAsDouble();
    }
    @Override
    public void setMotor(double value) {
        motorController.set(value);   
    }
    public <T> T getMotorController(Class<T> classOfMotorController){
        return (T)this.motorController;
    }
    @Override
    public void turnOff() {
       motorController.set(0);
        
    }

    
}
