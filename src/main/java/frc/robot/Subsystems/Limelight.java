package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase {

    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private final NetworkTableEntry ty = table.getEntry("ty");

    // Limelight parameters
    private final double limelightMountAngleDegrees = 37;
    private final double limelightLensHeightInches = 7.5;
    private final double goalHeightInches = 12;

    // Dummy values above

    private double distanceFromLimelightToGoalMeters;

    public Limelight() {
        // Constructor
    }

    @Override
    public void periodic() {
        updateLimelightDistance(); // Call the updateDistance method periodically (approx 50x per sec)
    }

    private void updateLimelightDistance() {
        // Get the target offset angle from the network table
        double targetOffsetAngle_Vertical = ty.getDouble(0.0);

        // Calculate the angle to the goal
        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = Math.toRadians(angleToGoalDegrees);

        // Calculate the distance
        double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);

        // Convert distance to meters
        distanceFromLimelightToGoalMeters = distanceFromLimelightToGoalInches * Constants.INCHES_TO_METERS;

        // Display the distance on the SmartDashboard
        SmartDashboard.putNumber("Distance to Goal (meters)", distanceFromLimelightToGoalMeters);
    }

    public double getLimelightDistance() {
        return distanceFromLimelightToGoalMeters; // Return the calculated distance
    }
}
