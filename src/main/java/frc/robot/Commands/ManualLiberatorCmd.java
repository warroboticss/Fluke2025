package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.LiberatorSubsystem;

public class ManualLiberatorCmd extends Command {

    private static LiberatorSubsystem liberator;
    private static Supplier<Boolean> move;

    public ManualLiberatorCmd(LiberatorSubsystem liberator, Supplier<Boolean> move){
        ManualLiberatorCmd.liberator = liberator;
        ManualLiberatorCmd.move = move;

        addRequirements(liberator);
    }
    
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        if(move.get()){
            liberator.intake();
        }
    }
}
