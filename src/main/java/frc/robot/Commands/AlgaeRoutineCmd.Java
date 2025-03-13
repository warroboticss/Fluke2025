package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;

public class ScoreCmd extends SequentialCommandGroup{

    public ScoreCmd(ElevatorSubsystem elevator, LiberatorSubsystem liberator, int height){
        addCommands(
            new SlightlyAlgaeCmd(liberator, elevator),
            new ElevatorCmd(elevator, Constants.ELEVATOR_HEIGHTS[height-1], liberator),
            new RemoveAlgae(liberator, elevator)
            // Algae removal cmd
        );
        }
    }
    
