package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;

public class ManualElevatorCmd extends Command{

    private static boolean up;
    private static ElevatorSubsystem elevator;


    public ManualElevatorCmd(ElevatorSubsystem elevator, boolean up){
        ManualElevatorCmd.elevator = elevator;
        ManualElevatorCmd.up = up;

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
        if(up){
            elevator.manual(1);
        }
        else{
            elevator.manual(-1);
        }
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        elevator.setLock(false);
    }
    
}
