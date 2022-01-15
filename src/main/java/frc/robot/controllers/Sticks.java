package frc.robot.controllers;

import edu.wpi.first.wpilibj.GenericHID;

public class Sticks extends Mirrored<Stick>{
    public final Stick left, right;
    public Sticks(GenericHID controller) {
        // Temp code
        left =  new Stick(() -> controller.getRawAxis(0),  ()-> controller.getRawAxis(1));
        right = new Stick(() -> controller.getRawAxis(2), ()->  controller.getRawAxis(3));
    }
}