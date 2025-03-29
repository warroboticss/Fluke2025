package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.CoralIndexer;

public class ReverseIndexCmd extends Command{
    private final CoralIndexer index;

    public ReverseIndexCmd(CoralIndexer index) {
        this.index = index;

        // Set CoralIndexer as a required subsystem
        addRequirements(index);
    }

    @Override
    public void execute() {
        // Continuously run the indexer motor
        index.reverseIndex();
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
    }
}
