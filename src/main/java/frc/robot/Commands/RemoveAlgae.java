package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;

public class RemoveAlgae extends Command{

    public static LiberatorSubsystem liberator;
    private static ElevatorSubsystem elevator;
    private static Timer time = new Timer();
    
    public RemoveAlgae(LiberatorSubsystem liberator, ElevatorSubsystem elevator){
        RemoveAlgae.liberator = liberator;
        RemoveAlgae.elevator = elevator;

        System.out.println("RAN");

        addRequirements(liberator);
    }

    public void initialize(){
        elevator.setLock(true);
        //slow drive
    }

    @Override
    public void execute(){
        System.out.println("RAN");
        liberator.removeAlgae();
        if(liberator.getAlgaePosition() >= 42.66){
            time.start();
        }
    }

    public boolean isFinished(){
        return time.get() >= 2;
    }

    public void end(){
        time.stop();
        time.reset();
        liberator.resetAlgae();
        elevator.setLock(false);
        elevator.home();
    }
    
}
