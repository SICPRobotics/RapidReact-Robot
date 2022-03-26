package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveTrain;

public class DriveDistance extends CommandBase {
    private final DriveTrain driveTrain;
    public DriveDistance(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
    }

    @Override
    public void initialize() {
        this.driveTrain
    }
}
