package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.CoralIndexer;

public class IndexCmd extends Command{
    private final CoralIndexer index;

    public IndexCmd(CoralIndexer index) {
        this.index = index;

        // Set CoralIndexer as a required subsystem
        addRequirements(index);
    }

    @Override
    public void execute() {
        // Continuously run the indexer motor
        index.index();
    }

    @Override
    public boolean isFinished() {
        // Command never ends
        return false;
    }
}
