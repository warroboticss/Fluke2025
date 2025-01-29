package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;

public class ElevatorSubsystem extends SubsystemBase{
    private static TalonFX elevatorMotorLeft = new TalonFX(15);
    private static TalonFX elevatorMotorRight = new TalonFX(15);

    private static ElevatorFeedforward feed = new ElevatorFeedforward(0, 0, 0);
    private static PowerDistribution pdh = new PowerDistribution(0,ModuleType.kRev);
    PositionDutyCycle pid;
    private static boolean lock;
    DigitalInput home = new DigitalInput(0);

    
    public ElevatorSubsystem(){
        elevatorMotorRight.setInverted(true);
        home();
    }

    public void run(double setpoint){
        // velocity should be in rotations per seconds
        //pid = new PositionDutyCycle(setpoint, 0.0, false, (feed.calculate(0))/pdh.getVoltage(), 0.0, false, false, false);
        pid = new PositionDutyCycle(setpoint);
        elevatorMotorLeft.setControl(pid);
        elevatorMotorRight.setControl(pid);
    }

    public void home(){
        while(!home.get()){
            elevatorMotorLeft.set(0.2);
            elevatorMotorRight.set(0.2);
        }
        elevatorMotorLeft.set(0);
        elevatorMotorRight.set(0);
        elevatorMotorLeft.setPosition(0);
    }
    
}
