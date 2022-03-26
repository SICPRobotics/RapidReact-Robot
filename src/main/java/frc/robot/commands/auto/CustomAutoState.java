package frc.robot.commands.auto;

public class CustomAutoState {
    public boolean intake = false;
    public double timeSinceStart = 0;
    public CustomAutoStep step = null;
    public double time = 0;

    protected CustomAutoState () {

    }

    public static CustomAutoState createDefault() {
        return new CustomAutoState();
    }
}
