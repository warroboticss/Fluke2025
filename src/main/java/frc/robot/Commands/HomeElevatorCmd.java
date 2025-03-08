package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.Subsystems.ElevatorSubsystem;

public class HomeElevatorCmd extends Command{

    private static ElevatorSubsystem elevator;

    // height: 1,2,3,4 for each level
    public HomeElevatorCmd(ElevatorSubsystem elevator){
        HomeElevatorCmd.elevator = elevator;

        addRequirements(elevator);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
    //    elevator.run((setpoint*Constants.INCHES_TO_ROTATIONS_ELEVATOR));
        //elevator.test();
        //System.out.println(elevator.getPosition() * Constants.INCHES_PER_ROTATION_ELEVATOR);
        elevator.home();
    }

    @Override
    public boolean isFinished(){
        return false;   
    }

    public void end(){
        
    }

    
}
