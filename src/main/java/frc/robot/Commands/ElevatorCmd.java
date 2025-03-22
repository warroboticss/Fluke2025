package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;

public class ElevatorCmd extends Command{

    private static ElevatorSubsystem elevator;
    private double setpoint;
    private Timer time = new Timer();
    private static LiberatorSubsystem liberator;

    // height: 1,2,3,4 for each level
    public ElevatorCmd(ElevatorSubsystem elevator, double setpoint, LiberatorSubsystem liberator){
        ElevatorCmd.elevator = elevator;
        ElevatorCmd.liberator = liberator;
        this.setpoint = setpoint;

        addRequirements(elevator, liberator);
    }

    @Override
    public void initialize(){
        time.start();
        elevator.setLock(true);
        liberator.stop();
        liberator.setLibLock(true);
    }

    @Override
    public void execute(){
    //    elevator.run((setpoint*Constants.INCHES_TO_ROTATIONS_ELEVATOR));
        //elevator.test();
        //System.out.println(elevator.getPosition() * Constants.INCHES_PER_ROTATION_ELEVATOR);
        elevator.run((setpoint));
    }

    @Override
    public boolean isFinished(){
        if(setpoint == Constants.ELEVATOR_HEIGHTS[3]){
            return time.get() > 0.85; 
        }
        return time.get() > 1.2;    
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        time.stop();
        time.reset();
        liberator.setLibLock(false);
        System.out.println("DONE");
    }

    public void end(){
        time.stop();
        time.reset();
        liberator.setLibLock(false);
    }

    
}
