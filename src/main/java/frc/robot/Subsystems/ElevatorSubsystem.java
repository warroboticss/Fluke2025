package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;

public class ElevatorSubsystem extends SubsystemBase{
    private static TalonFX elevatorMotorLeft = new TalonFX(15);
    private static TalonFX elevatorMotorRight = new TalonFX(15);

    private static boolean lock;

    
    public ElevatorSubsystem(){
        
    }

    public void run(Double position){
        PositionDutyCycle pid = new PositionDutyCycle(0, 0, lock, 0, 0, lock, lock, lock);
        elevatorMotorLeft.setControl(pid);
    }
    
}
