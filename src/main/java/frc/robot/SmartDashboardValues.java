package frc.robot;

import java.util.ArrayList;

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
                SmartDashboard.putData(value.getKey(), value.getDefaultValue());
                value.post();
            }
            else{

            }
        }
    }
}
