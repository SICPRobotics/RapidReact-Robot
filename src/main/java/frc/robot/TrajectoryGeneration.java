package frc.robot;

import java.io.ObjectInputFilter.Config;
import java.util.List;

import com.google.gson.Gson;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.math.geometry.Translation2d;

public class TrajectoryGeneration {
    private Trajectory trajectory;
    private DriveTrain driveTrain;

    public TrajectoryGeneration(DriveTrain driveTrain){
        this.driveTrain = driveTrain;
    }
    public void saveConfig(){
        
    }

    public Trajectory generate(Pose2d start, List<Translation2d> waypoints, Pose2d end, TrajectoryConfig config){
        this.trajectory = TrajectoryGenerator.generateTrajectory(start, waypoints, end, config);
        return this.trajectory;
    }
    public Trajectory getTrajectory(String code){
        return this.trajectory;
    }
    // private class ConfigSaver{
    //     public ConfigSaver(){

    //     }
    // }
}
