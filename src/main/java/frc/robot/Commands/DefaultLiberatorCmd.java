package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.LiberatorSubsystem;


public class DefaultLiberatorCmd extends Command{
    private static LiberatorSubsystem liberator;
    private Supplier<Boolean> intaking;

    public DefaultLiberatorCmd(LiberatorSubsystem liberator, Supplier<Boolean> intaking){
        DefaultLiberatorCmd.liberator = liberator;
        this.intaking = intaking;
    }

    public void execute(){
        if(intaking.get()){
            liberator.intake();
        }
        else {
            liberator.state();
        }
    }
    
}
