package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;

public class ScoreCmd extends SequentialCommandGroup{

    public ScoreCmd(ElevatorSubsystem elevator, LiberatorSubsystem liberator, int height, boolean score){
        if(height == 4){
            addCommands(
                new l4ElevatorCmd(elevator, liberator),
                new LiberateCommand(liberator, elevator));
        }
        else if(!score){
            addCommands(
                new ElevatorCmd(elevator, Constants.ELEVATOR_HEIGHTS[height+1], liberator),
                new LiberateCommand(liberator, elevator));
        }
        else{
            addCommands(
                new SlightlyAlgaeCmd(liberator, elevator),
                new ElevatorCmd(elevator, Constants.ELEVATOR_HEIGHTS[height-1], liberator),
                new RemoveAlgae(liberator, elevator)
                // Algae removal cmd
            );
        }
    }
    
}
