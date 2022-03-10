package frc.robot;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashBoardClass <T>{
    private final String key;
    private T defaultValue;
    private boolean posted;



    public SmartDashBoardClass(String key, T defaultValue){
        this.key = key;
        this.defaultValue = defaultValue;
        SmartDashboardValues.addValue(this);
    }
    // public static Sendable toSendable(Object value){
    //     SendableBuilder builder = new SendableBuilder().;
    
    
    // }

    public String getKey() {
        return key;
    }
    // public T getValue(){
    //     return (T)SmartDashboard.getData(key);
    // }
    public Double getValue(){
        return SmartDashboard.getNumber(this.key, (Double)this.defaultValue);
    }
    // add for other classes but it works bitch
    public T getDefaultValue() {
        return defaultValue;
    }
    public boolean getPosted(){ 
        return this.posted;
    }
    public void post(){
        this.posted = true;
    }
    public void setDefaultValue(T value){
        this.defaultValue = value;
    }
    public void jsonSave(GsonSaver gsonSaver){
        gsonSaver.saveObject(this, this.key);
    }
    public SmartDashBoardClass<T> replace(){
        return new SmartDashBoardClass<T>(this.key, this.defaultValue);
    }
}

// class toSendable<T> implements Sendable{

//     private String key;
//     private final Object value;

//     public toSendable(String key, Object value){
//         this.value = value;
//         this.key = key;

//     }

//     @Override
//     public void initSendable(SendableBuilder builder) {
//         if(this.value.getClass() == double.class){
//             builder.addDoubleProperty(this.key, () -> (double)value, (Object value) -> this.value = value);
//         }
        
//     }
//     public Sendable getSendable(){
//         return this;
//     }

// }
