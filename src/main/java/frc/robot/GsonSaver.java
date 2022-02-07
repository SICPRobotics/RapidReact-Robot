package frc.robot;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.Map;

import javax.swing.text.StyledEditorKit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSaver {
    public final Path TEST = Path.of("test.json");
    public static final String TRAJECTORY_PATH = "/Jsons/Trajectories/";
    public static final String TRAJECTORY_CONFIG_PATH = "/robot/Jsons/TrajectoryConfigs/";
    public static final String OTHER_PATH = "/robot/Jsons/Other/";
    private Gson gson;
    private Map<String, String> env = System.getenv();

    public GsonSaver(){
        gson = new GsonBuilder()
                    .generateNonExecutableJson()
                    .serializeNulls()
                    .setPrettyPrinting()    
                    .create();
    }
    public void setBuild(GsonBuilder builder){
        gson = builder.create();
    }
    public void saveObject(Object save, String filePath, String fileName){
        
        try {
            File file = new File("test.json");
            file.mkdirs();
            file.createNewFile();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            System.out.println("DID NOT CREATE");
            e1.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(this.env.get("HOME") + "/test.json")) {
            gson.toJson(save, writer);
        } catch (IOException e) {
            System.out.println("DID NOT SAVE");
            e.printStackTrace();
        }
    }
    public <T> T getObject(Class<T> classOfT, String filePath, String fileName){
    
        try (Reader reader = new FileReader(filePath + fileName + ".json")) {

            // Convert JSON File to Java Object
            return gson.fromJson(reader, classOfT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}