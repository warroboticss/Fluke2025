package frc.robot.Commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import com.ctre.phoenix6.swerve.SwerveRequest;

public class LimelightCmd extends Command{
    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private final NetworkTableEntry ta = table.getEntry("ta");
    private final NetworkTableEntry tx = table.getEntry("tx");

    private final SwerveRequest.FieldCentric swerveRequest;

    private static final double kP_AIM = 0.035; // Proportional aiming
    private static final double kP_RANGE = 0.1;  // Proportional forward

    public LimelightCmd(SwerveRequest.FieldCentric swerveRequest) {
        this.swerveRequest = swerveRequest;
    }

    @Override
    public void execute() {
        // Get Limelight values
        double txValue = tx.getDouble(0.0); // tx as a double
        double taValue = ta.getDouble(0.0); // ta as a double

        // Calculate adjustments for forward and rotational values
        double rotationalAdjust = -txValue * kP_AIM; // Align to the target
        double forwardAdjust = -taValue * kP_RANGE; // Approach the target

        // Update the Swerve Request with the calculated values
        swerveRequest
            .withVelocityX(forwardAdjust) // Forward movement
            .withVelocityY(0.0) // N/A
            .withRotationalRate(rotationalAdjust); // Rotation adjustment
    }

    @Override
    public boolean isFinished() {
        return false; // GO FORWEVER
    }
}
