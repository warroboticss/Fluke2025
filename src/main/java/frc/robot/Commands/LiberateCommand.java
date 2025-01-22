package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.LiberatorSubsystem;

public class LiberateCommand extends Command{
    private LiberatorSubsystem liberator;

    public void initialize(){
        //slow drive
    }

    public void execute(){
        if (!liberator.getLock()){
            liberator.liberate();
        }
    }

    public boolean isFinished(){
        return !liberator.inDeep();
    }

    public void end(){
        liberator.stop();
        //send elevator back to home
        //regular drive
    }
}
