package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.vision.LimelightSubsystem;

public class UpdateCoordinatesCommand extends Command{
    private final LimelightSubsystem limelightSubsystem;

    // Constructor
    public UpdateCoordinatesCommand(LimelightSubsystem limelightSubsystem) {
        this.limelightSubsystem = limelightSubsystem;
        addRequirements(limelightSubsystem);
    }

    @Override
    public void execute() {
        // Update pose continuously
        limelightSubsystem.updatePose();
    }

    @Override
    public boolean isFinished() {
        // Command runs indefinitely as a default
        return false;
    }
}

