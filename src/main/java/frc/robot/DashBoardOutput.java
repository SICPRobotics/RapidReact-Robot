package frc.robot;

import java.io.File;
import java.util.Map;

import com.google.gson.JsonObject;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveWithJoystick;

public class DashBoardOutput {

    private NetworkTableEntry entry;
    private GsonSaver saver;
    private String key;
    private Map<?,?> map;

    private final int 
    

    public DashBoardOutput(NetworkTableEntry entry, GsonSaver saver){
        this.entry = entry;
        this.saver = saver;
        this.key = entry.getName();
        this.saver.saveObject(this.entry.getString("nothing"), key);
        this.map = this.saver.getObject(Map.class, key);
    }

    public Command parseAuto(){
        Command completeAuto = new Command(); //  
        for(Map.Entry<?,?> entry : map.entrySet()){
            if(entry)
        }
    }




}
