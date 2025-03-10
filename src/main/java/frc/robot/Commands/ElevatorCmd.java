package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.Subsystems.ElevatorSubsystem;

public class ElevatorCmd extends Command{

    private static ElevatorSubsystem elevator;
    private double setpoint;
    private Timer time = new Timer();

    // height: 1,2,3,4 for each level
    public ElevatorCmd(ElevatorSubsystem elevator, double setpoint){
        ElevatorCmd.elevator = elevator;
        this.setpoint = setpoint;

        addRequirements(elevator);
    }

    @Override
    public void initialize(){
        time.start();
        elevator.setLock(true);
    }

    @Override
    public void execute(){
    //    elevator.run((setpoint*Constants.INCHES_TO_ROTATIONS_ELEVATOR));
        //elevator.test();
        System.out.println(elevator.getPosition() * Constants.INCHES_PER_ROTATION_ELEVATOR);
        elevator.run((setpoint));
    }

    @Override
    public boolean isFinished(){
        return Math.abs(elevator.getPosition() - (setpoint*Constants.ROTATIONS_PER_INCH_ELEVATOR)) < 0.2;    
    }

    public void end(){
        time.stop();
        time.reset();
    }

    
}
