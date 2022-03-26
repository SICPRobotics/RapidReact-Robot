package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Pidgey;

public class TurnRelative extends CommandBase{
    
    private final DriveTrain driveTrain;
    private final Pidgey pidgey;
    private final double rotation, speed, direction;
    private double rotationError;

    public TurnRelative(DriveTrain driveTrain, Pidgey pidgey, double rotation){
        this.driveTrain = driveTrain;
        this.pidgey = pidgey;
        this.rotation = rotation;
        this.direction = Math.signum(rotation);
        this.rotationError = rotation;
        this.speed = 0.5; // add constatnt for deafult auto speed
    }
    @Override
    public void initialize() {
        this.driveTrain.diffDrive(speed * this.direction , -speed * this.direction);
    }
    @Override
    public void execute() {
        rotationError = rotation - pidgey.getRobotRelativeHeading();
        if(this.driveTrain.getLeftDistanceRaw() - this.driveTrain.getRightDistanceRaw() > Constants.Auto.TURN_ACCEPTED_ERROR){
            this.driveTrain.diffDrive((speed - 0.05) * this.direction , -(speed + 0.05) * this.direction);
        }
        else if(this.driveTrain.getLeftDistanceRaw() - this.driveTrain.getRightDistanceRaw() > Constants.Auto.TURN_ACCEPTED_ERROR){
            this.driveTrain.diffDrive((speed + 0.05) * this.direction , -(speed - 0.05) * this.direction);
        }
        else{
            this.driveTrain.diffDrive(speed * this.direction , -speed * this.direction);
        }
    }
    @Override
    public boolean isFinished() {
        return rotationError < Constants.Auto.TURN_ACCEPTED_ERROR;
    }
    @Override
    public void end(boolean interrupted) {
        this.driveTrain.diffDrive(0, 0);
    }


}
