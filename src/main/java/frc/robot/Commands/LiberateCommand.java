package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;


public class LiberateCommand extends Command{
    private static LiberatorSubsystem liberator;
    private static ElevatorSubsystem elevator;

    public LiberateCommand(LiberatorSubsystem liberator, ElevatorSubsystem elevator){
        LiberateCommand.liberator = liberator;

        addRequirements(liberator);
    }

    public void initialize(){
        elevator.setLock(true);
        //slow drive
    }

    @Override
    public void execute(){
        liberator.liberate();
    }

    public boolean isFinished(){
        return !liberator.inDeep();
    }

    public void end(){
        liberator.stop();
        elevator.setLock(false);
        //send elevator back to home
        //regular drive
    }
}
