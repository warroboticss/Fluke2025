package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;

public class SlightlyAlgaeCmd extends Command{

    public static LiberatorSubsystem liberator;
    private static ElevatorSubsystem elevator;
    private static Timer time = new Timer();
    
    public SlightlyAlgaeCmd(LiberatorSubsystem liberator, ElevatorSubsystem elevator){
        SlightlyAlgaeCmd.liberator = liberator;
        SlightlyAlgaeCmd.elevator = elevator;

        System.out.println("RAN");

        addRequirements(liberator);
    }

    public void initialize(){
        elevator.setLock(true);
        time.start();
        //slow drive
    }

    @Override
    public void execute(){
        System.out.println("RAN");
        liberator.slightAlgae();
    }

    public boolean isFinished(){
        return (liberator.getAlgaePosition() <= -30) || time.get() > 1;
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        time.stop();
        time.reset();
    }

    public void end(){
        time.stop();
        time.reset();
    }
    
}
