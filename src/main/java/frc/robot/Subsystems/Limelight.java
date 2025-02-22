package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase{


    // limelight one starts here


NetworkTable tableLimelight1 = NetworkTableInstance.getDefault().getTable("Limelight1");
    NetworkTableEntry ty1 = tableLimelight1.getEntry("ty");

    double limelight1TargetOffsetAngle_Vertical = ty1.getDouble(0.0);


    // how many degrees back is your limelight rotated from perfectly vertical? (for limelight one)
    double limelight1MountAngleDegrees = 25.0; 

    // distance from the center of the Limelight lens to the floor for limelight one
    double limelight1LensHeightInches = 20.0; 

    // distance from the target to the floor for limelight one
    double goalHeightInchesLimelight1 = 60.0; 

    // vertical offset for limelight one
    double angleToGoalDegreesLimelight1 = limelight1MountAngleDegrees + limelight1TargetOffsetAngle_Vertical;
    double angleToGoalRadiansLimelight1 = angleToGoalDegreesLimelight1 * (3.14159 / 180.0);

    //calculate distance for limelight one
    double distanceFromLimelight1ToGoalInches = (goalHeightInchesLimelight1 - limelight1LensHeightInches) / Math.tan(angleToGoalRadiansLimelight1);

    //converts distance in inches to meters for limelight one
    double distanceFromLimelight1ToGoalMeters = distanceFromLimelight1ToGoalInches * Constants.INCHES_TO_METERS;



    // limelight three starts here



NetworkTable tableLimelight3 = NetworkTableInstance.getDefault().getTable("Limelight3");
    NetworkTableEntry ty3 = tableLimelight1.getEntry("ty");

    double limelight3TargetOffsetAngle_Vertical = ty1.getDouble(0.0);


    // how many degrees back is your limelight rotated from perfectly vertical? (for limelight one)
    double limelight3MountAngleDegrees = 25.0; 

    // distance from the center of the Limelight lens to the floor for limelight one
    double limelight3LensHeightInches = 20.0; 

    // distance from the target to the floor for limelight one
    double goalHeightInchesLimelight3 = 60.0; 

    // vertical offset for limelight one
    double angleToGoalDegreesLimelight3 = limelight3MountAngleDegrees + limelight1TargetOffsetAngle_Vertical;
    double angleToGoalRadiansLimelight3 = angleToGoalDegreesLimelight1 * (3.14159 / 180.0);

    //calculate distance for limelight one
    double distanceFromLimelight3ToGoalInches = (goalHeightInchesLimelight3 - limelight3LensHeightInches) / Math.tan(angleToGoalRadiansLimelight1);

    //converts distance in inches to meters for limelight one
    double distanceFromLimelight3ToGoalMeters = distanceFromLimelight1ToGoalInches * Constants.INCHES_TO_METERS;

}

