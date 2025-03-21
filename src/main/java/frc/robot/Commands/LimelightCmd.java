package frc.robot.Commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import frc.robot.Subsystems.CommandSwerveDrivetrain;


public class LimelightCmd extends Command {
    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private final NetworkTableEntry ta = table.getEntry("ta");
    private final NetworkTableEntry tx = table.getEntry("tx");

    private final CommandSwerveDrivetrain drivetrain;

    // SwerveRequest configuration
    public static final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(0.1)  // 10% deadband (scaled based on max speed)
            .withRotationalDeadband(0.1) // Rotational deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Open-loop control
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
    private final SwerveRequest.FieldCentric swerveRequest = new SwerveRequest.FieldCentric();

    // Control gains
    private static final double kP_AIM = 0.05; // Proportional aiming
    private static final double kP_RANGE = 0.15; // Proportional forward

    public LimelightCmd(CommandSwerveDrivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain); // Ensure proper resource management
    }

    @Override
    public void execute() {
        System.out.println("LimelightCmd is executing.");

        // Get Limelight values
        double txValue = tx.getDouble(0.0); // Horizontal offset
        double taValue = ta.getDouble(0.0); // Target area

        System.out.println("Limelight tx: " + txValue + ", ta: " + taValue);

        // Calculate adjustments
        double rotationalAdjust = -txValue * kP_AIM; // Align to the target
        double forwardAdjust = -taValue * kP_RANGE; // Approach the target

        System.out.println("Forward Adjust: " + forwardAdjust);
        System.out.println("Rotational Adjust: " + rotationalAdjust);

        // Create and apply a swerve request dynamically
        drivetrain.applyRequest(() -> 
            drive.withVelocityX(forwardAdjust)
            .withVelocityY(0.0)
            .withRotationalRate(rotationalAdjust)
        );

        // Optional: Use brake or point modes if required
        // drivetrain.applyRequest(() -> brake); // Apply braking
        // drivetrain.applyRequest(() -> point); // Point wheels at a specific direction
    }

    @Override
    public boolean isFinished() {
        return false; // Command runs indefinitely
    }
}
