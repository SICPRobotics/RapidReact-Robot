package frc.robot;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.Map;
import edu.wpi.first.wpilibj.Filesystem;
import java.lang.SecurityManager;
import javax.swing.text.StyledEditorKit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSaver {
    //private static final Path = Filesystem.get blab lablalsi
    public static final String TRAJECTORY_PATH = "/Jsons/Trajectories/";
    public static final String TRAJECTORY_CONFIG_PATH = "/robot/Jsons/TrajectoryConfigs/";
    public static final String OTHER_PATH = "/robot/Jsons/Other/";
    private Gson gson;
    private String directory;

    public GsonSaver(){ 
        Filesystem.getDeployDirectory().mkdir();
        directory = Filesystem.getDeployDirectory().toPath().toString();
    
        // Filesystem.getDeployDirectory().setExecutable(true, false);
        // Filesystem.getDeployDirectory().setReadable(true, false);
        // Filesystem.getDeployDirectory().setWritable(true, false);
        
        System.out.println(Filesystem.getDeployDirectory().canWrite() + ", " + Filesystem.getDeployDirectory().canRead() + ", " + Filesystem.getDeployDirectory().canExecute());
        
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

        File file = new File(this.directory + "/test.json");
        file.setExecutable(true, false);
        file.setReadable(true, false);
        file.setWritable(true, false);
        try {   
        file.createNewFile();
        } 
        catch (IOException e1) {
            // TODO Auto-generated catch block
            System.out.println("DID NOT CREATE");
            e1.printStackTrace();
        }
        System.out.println(file.exists() + "WOOOOT");
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(save, writer);
        } catch (IOException e) {
            System.out.println("DID NOT SAVE");
            e.printStackTrace();
        }
    }
    public <T> T getObject(Class<T> classOfT, String filePath, String fileName){
    
        try (Reader reader = new FileReader(this.directory + "/test.json")) {

            // Convert JSON File to Java Object
            return gson.fromJson(reader, classOfT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
