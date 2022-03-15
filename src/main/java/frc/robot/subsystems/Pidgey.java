package frc.robot.subsystems;

import com.ctre.phoenix.sensors.Pigeon2;
import com.ctre.phoenix.sensors.Pigeon2.AxisDirection;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.SubsystemBaseWrapper;

public class Pidgey extends SubsystemBaseWrapper {
    public final Pigeon2 pigeon = new Pigeon2(10);
    private final double[] vXYZ = new double[3];

    public Pidgey() {
        super();

        pigeon.configMountPose(AxisDirection.PositiveY, AxisDirection.NegativeX);
        pigeon.setYaw(0);
        pigeon.configMountPosePitch(6.10);
    }
    
    /**
     * 60 degrees at arm up, 0 deg at arm down. No calibration needed
     * @return
     */
    public double getArmRotation() {
        return -pigeon.getPitch() + 6.10;
    }

    public Pidgey resetRobotHeading() {
        pigeon.setYaw(0);
        return this;
    }

    /**
     * Gets the robot heading relative to start position.
     * Automatically calibrated at startup; use resetRobotHeading to calibrate again.
     * @return
     */
    public double getRobotRelativeHeading() {
        return pigeon.getYaw();
    }

    /**
     * Get robot compass heading (relative to north pole). No calibration needed
     * @return
     */
    public double getRobotAbsoluteHeading() {
        return pigeon.getAbsoluteCompassHeading();
    }
    


    /* Not tested yet
    public double getAngularVelocity() {
        pigeon.getRawGyro(vXYZ);
        return vXYZ[2];
    }*/

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Pigeon Yaw", pigeon.getYaw());
        SmartDashboard.putNumber("Pigeon Pitch", pigeon.getPitch());
        SmartDashboard.putNumber("Pigeon Roll", pigeon.getRoll());
        SmartDashboard.putNumber("Pigeon Arm Rotation", getArmRotation());
    }
}
