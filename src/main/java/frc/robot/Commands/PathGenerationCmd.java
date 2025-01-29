package frc.robot.Commands;

import java.util.List;
import java.util.function.Supplier;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.GoalEndState;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.path.Waypoint;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.CommandSwerveDrivetrain;

public class PathGenerationCmd extends Command {
    private final CommandSwerveDrivetrain swerve;
    private final Supplier<Double> x;
    private final Supplier<Double> y;

    public PathGenerationCmd(CommandSwerveDrivetrain swerve, Supplier<Double> x, Supplier<Double> y){
        this.swerve = swerve;
        this.x = x;
        this.y = y;
    }
    
    public void execute(){
      //   Pose2d currentPose = swerve.getPose();

      //   Pose2d startPose = new Pose2d(currentPose.getTranslation(), new Rotation2d());
      //   Pose2d endPose = new Pose2d(currentPose.getTranslation().plus(new Translation2d(2.0,0.0)), new Rotation2d());

      //   List<Waypoint> waypoints = PathPlannerPath.waypointsFromPoses(startPose, endPose);
      // PathPlannerPath path = new PathPlannerPath(
      //   waypoints, 
      //   new PathConstraints(
      //     0.1, 0.2, 
      //     Units.degreesToRadians(360), Units.degreesToRadians(540)
      //   ),
      //   null, // Ideal starting state can be null for on-the-fly paths
      //   new GoalEndState(0.0, currentPose.getRotation())
      // );

      // // Prevent this path from being flipped on the red alliance, since the given positions are already correct
      // path.preventFlipping = true;

      // AutoBuilder.followPath(path).schedule();
    }
}
