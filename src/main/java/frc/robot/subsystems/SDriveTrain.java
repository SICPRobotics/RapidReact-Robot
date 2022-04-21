package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.SubsystemBaseWrapper;
import frc.robot.WillowMath;

public class SDriveTrain extends SubsystemBaseWrapper{
    
    /**
     * The Four Wheels of the Swerve Drive
     */
    private final SwerveWheel frontRight;
    private final SwerveWheel frontLeft;
    private final SwerveWheel backRight;
    private final SwerveWheel backLeft;
    private final SwerveWheel[] modules;



    public SDriveTrain(){
        
        frontRight = new SwerveWheel(0, 1);
        frontLeft = new SwerveWheel(2, 3);
        backRight = new SwerveWheel(4, 5);
        backLeft = new SwerveWheel(6, 7);
        modules = new SwerveWheel[] {frontRight, frontLeft, backRight, backLeft};
    }

    public void wolfDrive(Pose2d inputVector){
    
        Pose2d deadZonedPose = deadZonePose2d(inputVector, 0.01);
        double velocity = highestValue(deadZonedPose);
        double theta = calculateTheta(deadZonedPose);
        for(SwerveWheel wheel : modules){
            wheel.setDriveMotor(velocity);
            wheel.setSteerMotor(theta);
        }
    
    }
    public double calculateTheta(Pose2d inputVector){
        return (inputVector.getY() != 0 ?  Math.atan(inputVector.getX() / inputVector.getY()) : (Math.PI / 2) * Math.signum(inputVector.getX()))
                + (Math.PI * 2 * inputVector.getRotation().getRadians());
    }
    public Pose2d deadZonePose2d(Pose2d inputVector, double deadZoneValue){
        return new Pose2d(inputVector.getX() > deadZoneValue ? inputVector.getX() : 0,
             inputVector.getY() > deadZoneValue ? inputVector.getY() : 0,
                inputVector.getRotation().getRadians() > deadZoneValue ? inputVector.getRotation() : new Rotation2d(0));
    }
    public double highestValue(Pose2d inputVector){
        return WillowMath.max(WillowMath.abs(new double[] {inputVector.getX(), inputVector.getY(), inputVector.getRotation().getRadians()}));
    }
    @Override
    public void periodic() {
        for(SwerveWheel wheel : modules){
            wheel.updateSteer();
        }
    }
}
