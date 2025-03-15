package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.LiberatorSubsystem;

public class ManualLiberate extends Command {

    private static LiberatorSubsystem liberator;
    private static boolean move;

    public ManualLiberate(LiberatorSubsystem liberator){
        ManualLiberate.liberator = liberator;

        addRequirements(liberator);
    }
    
    @Override
    public void execute() {
        // TODO Auto-generated method stub
     
            liberator.intake();
}};
