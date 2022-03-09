package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardValues {
    private static ArrayList<SmartDashBoardClass> values;

    public static void addValue(SmartDashBoardClass value){
        values.add(value);
    }
    public static ArrayList<SmartDashBoardClass> getValues() {
        return values;
    }
    public static void postAllValues(){
        for(SmartDashBoardClass value : values){
            if(!value.getPosted()){
                postValue(value);
                value.post();
            }
            else{

            }
        }
    }
    public static void saveAllValues(){
        for(SmartDashBoardClass value : values){
            value.setDefaultValue(value.getValue());
        }
        //save as Json 
    }
    private static void postValue(SmartDashBoardClass value){
        if(value.getDefaultValue().getClass() == double.class){
            SmartDashboard.putNumber(value.getKey(), (double)value.getDefaultValue());
        }
        else if(value.getDefaultValue().getClass() == boolean.class){
            SmartDashboard.putBoolean(value.getKey(), (boolean)value.getDefaultValue());
        }
        else if(value.getDefaultValue().getClass() == String.class){
            SmartDashboard.putString(value.getKey(), (String)value.getDefaultValue());
        }
        else if(value.getDefaultValue().getClass() == boolean[].class){
            SmartDashboard.putBooleanArray(value.getKey(), (boolean[])value.getDefaultValue());
        }
        else if(value.getDefaultValue().getClass() == double[].class){
            SmartDashboard.putNumberArray(value.getKey(), (double[])value.getDefaultValue());
        }
        else if(value.getDefaultValue().getClass() == String[].class){
            SmartDashboard.putStringArray(value.getKey(), (String[])value.getDefaultValue());
        }
        else if(Sendable.class.isAssignableFrom(value.getClass())){
            SmartDashboard.putData(value.getKey(), (Sendable)value.getDefaultValue());
        }
        else{
            System.err.println("Not Able to post to SmartDashBoard");
        }
    }
}
