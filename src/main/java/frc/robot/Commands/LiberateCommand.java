package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;


public class LiberateCommand extends Command{
    private static LiberatorSubsystem liberator;
    private static ElevatorSubsystem elevator;
    private static Timer time = new Timer();

    public LiberateCommand(LiberatorSubsystem liberator, ElevatorSubsystem elevator){
        LiberateCommand.liberator = liberator;
        time.start();

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
        return time.get() >= 3;
    }

    public void end(){
        time.stop();
        time.reset();
        liberator.stop();
        elevator.setLock(false);
        elevator.home();
        liberator.setCoralToggle(false);
    }
}
