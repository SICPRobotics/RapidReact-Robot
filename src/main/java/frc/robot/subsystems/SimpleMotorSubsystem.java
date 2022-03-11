package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class SimpleMotorSubsystem implements MotorSubsystem{

    private MotorController motorController;
    private DoubleSupplier sensor;
    private String sensorKey;
    //private Servo servo;
    public SimpleMotorSubsystem(){}
    public SimpleMotorSubsystem(MotorController motorController){
        this.motorController = motorController;
    }
    public SimpleMotorSubsystem(MotorController motorController, DoubleSupplier sensor, String sensorKey){
        this.motorController = motorController;
        this.sensor = sensor;
        this.sensorKey = sensorKey;
    } 
    // public SimpleMotorSubsystem(MotormotorController motorController, DoubleSupplier sensor, Servo servo){
    //     this.motorController = motorController;
    //     this.sensor = sensor;
    //     this.servo = servo;
    // }
    public void setMotorController(MotorController motorController){
        this.motorController = motorController;
    }
    public void setSensor(DoubleSupplier sensor, String sensorKey){
        this.sensor = sensor;
        this.sensorKey = sensorKey;
    }
    // public void setServo(Servo servo){
    //     this.servo = servo;
    // }
    public double getSensorReading(){
        return this.sensor.getAsDouble();
    }
    
    @Override
    public void periodic(){
        SmartDashboard.putNumber(this.sensorKey, this.getSensorReading());
    }

    @Override
    public void setMotor(double value){
        motorController.set(value);   
    }
    public void setMotor(double value, Runnable secondaryAction){
        secondaryAction.run();
        motorController.set(value);   
    }
    public <T> T getMotorController(Class<T> classOfMotorController){
        return (T)this.motorController;
    }
    @Override
    public void turnOff(){
       motorController.set(0);
        
    }

    
}
