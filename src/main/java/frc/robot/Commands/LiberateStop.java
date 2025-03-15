package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.LiberatorSubsystem;

public class LiberateStop extends Command {

    private static LiberatorSubsystem liberator;

    public LiberateStop(LiberatorSubsystem liberator){
        LiberateStop.liberator = liberator;

        addRequirements(liberator);
    }
    
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        liberator.stop();
    }
}
