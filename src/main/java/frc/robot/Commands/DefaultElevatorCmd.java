package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;


public class DefaultElevatorCmd extends Command{
    private static ElevatorSubsystem elevator;
    public DefaultElevatorCmd(ElevatorSubsystem elevator){
        DefaultElevatorCmd.elevator = elevator;

        addRequirements(elevator);
    }

    public void execute(){
        elevator.home();
    }
    
}

