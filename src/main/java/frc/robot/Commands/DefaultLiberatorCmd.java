package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.LiberatorSubsystem;


public class DefaultLiberatorCmd extends Command{
    private LiberatorSubsystem liberator;
    private Supplier<Boolean> liberating;

    public DefaultLiberatorCmd(LiberatorSubsystem liberator, Supplier<Boolean> liberating){
        this.liberator = liberator;
        this.liberating = liberating;
    }

    public void execute(){
        if(liberating.get()){
            liberator.liberate();
        }
        else {
            liberator.state();
        }
    }
    
}
