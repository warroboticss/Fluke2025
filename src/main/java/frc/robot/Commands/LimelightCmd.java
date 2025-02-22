package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.CommandSwerveDrivetrain;

public class LimelightCmd extends Command{
    private static Supplier<Double> tv;
    private static CommandSwerveDrivetrain swerve;

    // for Double side: 0 for leftReef, 1 for algae, 2 for rightReef
    public LimelightCmd(CommandSwerveDrivetrain swerve, Double side, Supplier<Double> tv){
        this.tv = tv;
        LimelightCmd.swerve = swerve;


        addRequirements(swerve);
    }

    public void execute(){
        // get translational distance from april tag in meters
        
    }

    public boolean isFinished(){
        if(tv.get() == 0){
            return true;
        }
        return false;
    }
}
