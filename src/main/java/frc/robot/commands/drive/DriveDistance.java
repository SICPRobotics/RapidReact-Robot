package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase {
    private final DriveTrain driveTrain;
    private final double distance, speed;
    private double distanceError;
    public DriveDistance(DriveTrain driveTrain, double distance, double speed) {
        this.driveTrain = driveTrain;
        this.distance = distance;
        this.speed = speed;
        this.distanceError = distance;
    }




    @Override
    public void initialize() {
        this.driveTrain.diffDrive(speed, speed);
    }
    @Override
    public void execute() {
        distanceError = distance - ((driveTrain.getLeftDistanceRaw() + driveTrain.getRightDistanceRaw()) / 2);
        if(driveTrain.getLeftDistanceRaw() - driveTrain.getRightDistanceRaw() > Constants.Auto.DIST_DRIVE_ACCEPTED_ERROR){
            this.driveTrain.diffDrive(speed - 0.05, speed + 0.05);
        }
        else if(driveTrain.getLeftDistanceRaw() - driveTrain.getRightDistanceRaw() < -Constants.Auto.DIST_DRIVE_ACCEPTED_ERROR){
            this.driveTrain.diffDrive(speed + 0.05, speed - 0.05);
        }
        else{
            this.driveTrain.diffDrive(speed, speed);
        }
    }
    @Override
    public boolean isFinished() {
        return distanceError < Constants.Auto.DIST_DRIVE_ACCEPTED_ERROR;
    }
    @Override
    public void end(boolean interrupted) {
        this.driveTrain.diffDrive(0, 0);
    }




}
