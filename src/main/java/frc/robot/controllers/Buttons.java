package frc.robot.controllers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Button;

public class Buttons {
    public final Button A;         
    public final Button B;         
    public final Button X;         
    public final Button Y;         
    public final Button LB; 
    public final Button RB;
    public final Button back;
    public final Button start;
    public final Button LS;
    public final Button RS;
    public final DPad dPad;
    
    public Buttons(GenericHID controller) {
        A               = new WolfbyteButton(controller, 1); 
        B               = new WolfbyteButton(controller, 2); 
        X               = new WolfbyteButton(controller, 3);
        Y               = new WolfbyteButton(controller, 4); 
        LB              = new WolfbyteButton(controller, 5); 
        RB              = new WolfbyteButton(controller, 6); 
        back            = new WolfbyteButton(controller, 7); 
        start           = new WolfbyteButton(controller, 8);
        LS              = new WolfbyteButton(controller, 9); 
        RS              = new WolfbyteButton(controller, 10);
        
        dPad = new DPad(controller);
    }
}