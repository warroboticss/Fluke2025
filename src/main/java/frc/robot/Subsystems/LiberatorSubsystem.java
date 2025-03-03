package frc.robot.Subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;

import au.grapplerobotics.LaserCan;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiberatorSubsystem extends SubsystemBase {
    private static TalonFX liberatorMotor1 = new TalonFX(15, "rio");
    private static TalonFX liberatorMotor2 = new TalonFX(16, "rio");
    private static TalonFX algaeMotor = new TalonFX(19, "rio");
    // private LaserCan lc = new LaserCan(20);

    Orchestra m_orchestra = new Orchestra();

    PositionDutyCycle pid = new PositionDutyCycle(0);

    //private static boolean lock;
      
    public LiberatorSubsystem(){
        liberatorMotor2.setControl(new Follower(liberatorMotor1.getDeviceID(), true));
        //lock = false;
        m_orchestra.addInstrument(liberatorMotor1);
        m_orchestra.addInstrument(liberatorMotor2);
        m_orchestra.loadMusic("output.chrp");
    }


    // public boolean getLock(){
    //     return lock;
    // }

    // public void setLock(boolean lock){
    //     LiberatorSubsystem.lock = lock;
    // }

    public void stop(){
        liberatorMotor1.set(0);
    }

    public void liberate(){
        liberatorMotor1.set(0.25);
    }

    public void run(double speed){
        liberatorMotor1.set(speed);
    }

    public void intake(){
        liberatorMotor1.set(-0.25);
    }

    // public boolean ifCoral(){
    //     return lc.getMeasurement().distance_mm < 50;
    // }


    // public void state(){
    //     if(ifCoral()){
    //         stop();
    //     }
    //     else{
    //         liberate();
    //     }
    // }


    // ALGAE REMOVAL
    public void removeAlgae(){
        algaeMotor.setControl(pid.withPosition(44.66));
    }

    public void resetAlgae(){
        algaeMotor.setControl(pid.withPosition(0));
    }

    public double getAlgaePosition(){
        return algaeMotor.getPosition().getValueAsDouble();
    }
}
