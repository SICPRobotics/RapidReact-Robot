package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import frc.robot.SubsystemBaseWrapper;

public class Climber extends SubsystemBaseWrapper {
    private final Servo ratchetServo;

    /**
     * Creates an instance of the climber.
     */
    public Climber() {
        ratchetServo = new Servo(3);
    }

    public double getRatchetServoPosition() {
        return ratchetServo.get();
    }

    public void setRatchetServoPosition(final double pos) {
        ratchetServo.set(pos);
    }
        
}
