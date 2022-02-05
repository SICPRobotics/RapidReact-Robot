package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmHoldY extends CommandBase implements SignedCommand{
    private final ArmSubsystem arm;
    private int sign;
    public ArmHoldY(ArmSubsystem arm, int sign){
        this.arm = arm;
        this.sign = sign;
    }
  
  @Override
  public void initialize() {
    this.arm.shoulder(0.5 * sign);
    this.arm.elbow(0.5 * sign * 0.9);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }

@Override
public SignedCommand changeSign() {
    return new ArmHoldY(this.arm, this.sign * -1);
}





}
