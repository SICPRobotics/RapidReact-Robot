package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CargoArm;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.DriveTrain;

public class AutonumusCommand extends CommandBase{
    private final DriveTrain driveTrain;
    private final CargoArm arm; 
    private CargoIntake intake;
    private Timer timer;
    private final int version;
    private final double waitTime;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutonumusCommand(DriveTrain driveTrain, CargoArm arm, CargoIntake intake, int verison, double waitTime){
    this.driveTrain = driveTrain;
    this.arm = arm;
    this.intake = intake;
    this.timer = new Timer();
    this.version = verison;
    this.waitTime = waitTime;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain, arm, intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      this.timer.start();
    //   if(version == 0 || version == 2){
    //       this.driveTrain.cheesyDrive(0.2, 0);
    //   }
        if(version == 1){
            this.intake.setMotor(1);
        }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(version == 0){
        if(this.timer.get() > this.waitTime){
            this.driveTrain.cheesyDrive(0.4, 0);
        }
        else if(this.timer.get() > this.waitTime + 5){
            this.intake.setMotor(1);
        } 
    }
    else if(version == 2){
        if(this.timer.get() > 2){
            this.intake.setMotor(0);
            this.driveTrain.cheesyDrive(-0.5, 0);
        }
    }
    else if(version == 3){
        this.intake.setMotor(1);
    }
    else{

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      this.timer.reset();
      this.arm.turnOff();
      this.driveTrain.cheesyDrive(0, 0);
      this.intake.turnOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.timer.get() > 15;
  }
}
