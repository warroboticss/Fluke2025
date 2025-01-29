// package frc.robot.Subsystems;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants.ShooterConstants;
// import frc.robot.Constants;
// import frc.robot.LimelightHelpers;
// import static java.lang.Math.*;
// import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


// public class Limelight extends SubsystemBase {

//     public static boolean canSee;
//     static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");



//     @Override
//     public void periodic() {
//         //read values periodically
//         double x = LimelightHelpers.getTX(Constants.LLName);
//         double y = LimelightHelpers.getTY(Constants.LLName);
//         double area = LimelightHelpers.getTA(Constants.LLName);

//         if(area == 0){
//             canSee = false;
//         }
//         else{
//             canSee = true;
//         }
//         //boolean tv = RobotContainer.getTv();

//         //post to smart dashboard periodically
//         SmartDashboard.putNumber("LimelightX", x);
//         SmartDashboard.putNumber("LimelightY", y);
//         SmartDashboard.putNumber("LimelightArea", area);
//         SmartDashboard.putNumber("GetDistance", getDistance());
//         SmartDashboard.putBoolean("cansee?", canSee);

        

//     }
    


//     @Override
//     public void simulationPeriodic() {
        
//     }

//     public static int roundDistance(){
//         //takes distance (currently subed for .getTa), and rounds it to the nearest whole number (casted to an int because round returns long)
//         int distance = (int) round(getDistance());
//         return distance;
//     }

//     public static double getDistance(){
//     //https://docs.wpilib.org/en/latest/docs/software/vision-processing/introduction/identifying-and-processing-the-targets.html#distance
//     //uses this equation ^
//     //distance = (targetheight - cameraheight) / tan(cameraangle + Ty)
//         var y = LimelightHelpers.getTY(Constants.LLName);
//         double distance = (ShooterConstants.ApTagHeight - ShooterConstants.CamHeight) / Math.tan((ShooterConstants.CamAngle + (y)) * (Math.PI/180));
//         return distance;
//    }
// }




// /* package frc.robot.Subsystems;

// import java.util.function.Supplier;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class LimelightSubsystem extends SubsystemBase {

// LimelightHelpers.PoseEstimate getBotPoseEstimate_wpiRed_MegaTag2()
// LimelightHelpers.PoseEstimate getBotPoseEstimate_wpiBlue_MegaTag2()
// void SetRobotOrientation(yaw, yawRate, pitch, pitchRate, roll, rollRate)

// int[] validIDs = {3,4};
// LimelightHelpers.SetFiducialIDFiltersOverride("limelight", validIDs);
// // this filters out ids

// NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
// NetworkTableEntry tx = table.getEntry("tx");
// NetworkTableEntry ty = table.getEntry("ty");
// NetworkTableEntry ta = table.getEntry("ta");

// //read values periodically
// double x = tx.getDouble(0.0);
// double y = ty.getDouble(0.0);
// double area = ta.getDouble(0.0);

// //post to smart dashboard periodically
// SmartDashboard.putNumber("LimelightX", x);
// SmartDashboard.putNumber("LimelightY", y);
// SmartDashboard.putNumber("LimelightArea", area);


// // filters for relevant tags through MEGATAG 2
//  */
