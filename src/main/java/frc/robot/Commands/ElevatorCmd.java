package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Subsystems.ElevatorSubsystem;

public class ElevatorCmd extends Command{

    private static ElevatorSubsystem elevator;
    private double setpoint;;

    // height: 1,2,3,4 for each level
    public ElevatorCmd(ElevatorSubsystem elevator, double setpoint){
        ElevatorCmd.elevator = elevator;
        this.setpoint = setpoint;

        addRequirements(elevator);
    }

    @Override
    public void initialize(){
        elevator.setLock(true);
    }


    
}
