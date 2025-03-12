package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;

public class ManualElevatorUp extends Command{

    private static ElevatorSubsystem elevator;


    public ManualElevatorUp(ElevatorSubsystem elevator){
        ManualElevatorUp.elevator = elevator;

        addRequirements(elevator);
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        //super.initialize();
        elevator.setLock(true);
    }

    @Override
    public void execute() {
        elevator.manual(1);
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        elevator.setLock(false);
    }
    
}

