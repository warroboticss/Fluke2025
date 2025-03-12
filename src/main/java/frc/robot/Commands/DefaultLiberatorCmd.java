package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.LiberatorSubsystem;


public class DefaultLiberatorCmd extends Command{
    private static LiberatorSubsystem liberator;
    public DefaultLiberatorCmd(LiberatorSubsystem liberator){
        DefaultLiberatorCmd.liberator = liberator;

        addRequirements(liberator);
    }

    public void execute(){
        liberator.state();
    }
    
}
