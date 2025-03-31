package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;

public class l4ElevatorCmd extends Command{

    private static ElevatorSubsystem elevator;
    private Timer time = new Timer();
    private static LiberatorSubsystem liberator;

    // height: 1,2,3,4 for each level
    public l4ElevatorCmd(ElevatorSubsystem elevator, LiberatorSubsystem liberator){
        l4ElevatorCmd.elevator = elevator;
        l4ElevatorCmd.liberator = liberator;

        addRequirements(elevator, liberator);
    }

    @Override
    public void initialize(){
        time.start();
        elevator.setLock(true);
        liberator.stop();
        liberator.setLibLock(true);
        elevator.setl4Toggle(true);
    }

    @Override
    public void execute(){
    //    elevator.run((setpoint*Constants.INCHES_TO_ROTATIONS_ELEVATOR));
        //elevator.test();
        //System.out.println(elevator.getPosition() * Constants.INCHES_PER_ROTATION_ELEVATOR);
        elevator.l4();
    }

    @Override
    public boolean isFinished(){
        return time.get() > 1.25;    
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        elevator.setl4Toggle(true);
        time.stop();
        time.reset();
        liberator.setLibLock(false);
        System.out.println("DONE");
    }

    public void end(){
        time.stop();
        time.reset();
        liberator.setLibLock(false);
        elevator.setl4Toggle(true);
    }

    
}
