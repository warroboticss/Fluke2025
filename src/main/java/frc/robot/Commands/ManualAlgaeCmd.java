package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;

public class ManualAlgaeCmd extends Command{

    private static boolean up;
    private static LiberatorSubsystem liberator;


    public ManualAlgaeCmd(LiberatorSubsystem liberator, boolean up){
        ManualAlgaeCmd.liberator = liberator;
        ManualAlgaeCmd.up = up;

        addRequirements(liberator);
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        //super.initialize();
    }

    @Override
    public void execute() {
        if(up){
            liberator.manualAlgae(1);
        }
        else{
            liberator.manualAlgae(-1);
        }
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        liberator.setAlgae(0);

    }
    
}
