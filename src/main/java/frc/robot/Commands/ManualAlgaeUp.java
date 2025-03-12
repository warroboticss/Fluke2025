package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;

public class ManualAlgaeUp extends Command{

    private static LiberatorSubsystem liberator;


    public ManualAlgaeUp(LiberatorSubsystem liberator){
        ManualAlgaeUp.liberator = liberator;


        addRequirements(liberator);
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        //super.initialize();
    }

    @Override
    public void execute() {
            liberator.manualAlgaeUp();
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        liberator.setAlgae(0);

    }
    
}
