package frc.robot;

import java.io.ObjectInputFilter.Config;
import java.util.List;

import com.google.gson.Gson;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.math.geometry.Translation2d;

public class TrajectoryGeneration {

    public TrajectoryGeneration(){}
    
    public void generate(Pose2d start, List<Translation2d> waypoints, Pose2d end, TrajectoryConfig config, String name){
        GsonSaver.saveObject(TrajectoryGenerator.generateTrajectory(start, waypoints, end, config), GsonSaver.TRAJECTORY_PATH, name);
        
    }
    public Trajectory getTrajectory(String name){
        return GsonSaver.getObject(Trajectory.class, GsonSaver.TRAJECTORY_PATH, name);
    } 
    public Command getTrajectoryCommand(DriveTrain driveTrain, String trajectoryName){
        return new RamseteCommand(this.getTrajectory(trajectoryName), 
            driveTrain::getPose,
             new RamseteController(),
              new SimpleMotorFeedforward(Constants.DriveTrain.kinematics.S, Constants.DriveTrain.kinematics.V, Constants.DriveTrain.kinematics.A),
               driveTrain.kinematics,
                driveTrain::getWheelSpeeds,
                 new PIDController(Constants.DriveTrain.kinematics.P, Constants.DriveTrain.kinematics.I, Constants.DriveTrain.kinematics.D),
                  new PIDController(Constants.DriveTrain.kinematics.P, Constants.DriveTrain.kinematics.I, Constants.DriveTrain.kinematics.D),
                   driveTrain::voltDrive,
                    driveTrain);
    }
}


