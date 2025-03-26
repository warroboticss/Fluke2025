package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;


public class L1LiberateCommand extends Command{
    private static LiberatorSubsystem liberator;
    private static ElevatorSubsystem elevator;
    private Timer time = new Timer();

    public L1LiberateCommand(LiberatorSubsystem liberator, ElevatorSubsystem elevator){
        L1LiberateCommand.liberator = liberator;
        L1LiberateCommand.elevator = elevator;
        

        addRequirements(liberator);
    }

    public void initialize(){
        liberator.stop();
        
        //slow drive
    }

    @Override
    public void execute(){
        if(!liberator.getLibLock()){
            liberator.l1liberate();
            time.start();
        }
        
    }

    public boolean isFinished(){
        return time.get() >= 1.5;
    }

    @Override
    public void end(boolean interrupted) {
        time.stop();
        time.reset();
        liberator.stop();
        elevator.setLock(false);
        elevator.home();
        liberator.setCoralToggle(false);
        liberator.setLibLock(false);
        System.out.println("RAN");
    }

    public void end(){
        time.stop();
        time.reset();
        liberator.stop();
        elevator.setLock(false);
        elevator.home();
        liberator.setCoralToggle(false);
        liberator.setLibLock(false);
        System.out.println("RAN");
    }
}

