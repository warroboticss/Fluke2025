package frc.robot.Subsystems.vision;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LimelightSubsystem extends SubsystemBase {
    // Coordinate stuffs
    private double robotX = 0.0;
    private double robotY = 0.0;
    private double robotHeading = 0.0;

    public LimelightSubsystem() {
        // so empty
    }

    // Update pose based on Limelight data method so much funnnnnn
    public void updatePose() {
        // Get pose from MegaTag2 :)
        double[] botPose = LimelightHelpers.getBotPose_wpiBlue("limelight");
        if (botPose != null && botPose.length >= 6) {
            robotX = botPose[0]; // X-coordinate in meters
            robotY = botPose[1]; // Y-coordinate in meters
            robotHeading = botPose[5]; // Heading (yaw) in degrees

            System.out.println("robotX: " +  robotX);
            System.out.println("robotY: " +  robotY);
            System.out.println("robotHeading: " +  robotHeading);
        } else {
            System.out.println("No valid pose data from Limelight.");
        }
    }

    // Get robot X-coordinate
    public double getRobotX() {
        return robotX;
    }

    // Get robot Y-coordinate
    public double getRobotY() {
        return robotY;
    }

    // Get robot heading (yaw)
    public double getRobotHeadingRadians() {
        return robotHeading * Constants.DEGREES_TO_RADIANS;
    }
}
