package frc.robot.commands.autoclimb;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ClimberPivot;
import frc.robot.subsystems.Pidgey;

public class AutoClimb extends CommandBase {
    private final Climber climber;
    private final ClimberPivot pivot;
    private final Pidgey pidgey;
    private final ArrayList<CommandBase> steps = new ArrayList<CommandBase>();
    private int stepIndex;

    public AutoClimb (Climber climber, ClimberPivot pivot, Pidgey pidgey) {
        this.climber = climber;
        this.pivot = pivot;
        this.pidgey = pidgey;

        addRequirements(climber, pivot);
    }

    /**
     * Climber up
     * drive back
     * climber down
     * hooks forwards
     * climber up
     * on mid rung
     * climber down
     * hooks forwards
     * climber down
     * hooks backwards
     * climber up
     * hooks backwards
     * on high rung 
     * climber down
     * hooks forwards
     * climber up
     * climber down
     * hooks forwards 
     * climber up
     * hooks forwards
     * climber down
     * hooks backwards
     */

    @Override
    public void initialize() {
        stepIndex = 0;
    }

    @Override
    public void execute() {
        if (isFinished()) {
            return;
        }

        CommandBase step = steps.get(stepIndex);

        step.execute();

        if (step.isFinished()) {
            step.end(false);

            stepIndex++;

            if (stepIndex < steps.size()) {
                steps.get(stepIndex).initialize();
            }
        }
    }

    @Override
    public boolean isFinished() {
        return stepIndex >= steps.size();
    }
}