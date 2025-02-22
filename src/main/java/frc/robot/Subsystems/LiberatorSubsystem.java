package frc.robot.Subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiberatorSubsystem extends SubsystemBase {
    private static TalonFX liberatorMotor1 = new TalonFX(15, "rio");
    private static TalonFX liberatorMotor2 = new TalonFX(16, "rio");
    private DigitalInput inDeep = new DigitalInput(0);
    private DigitalInput in = new DigitalInput(0);

    Orchestra m_orchestra = new Orchestra();
    

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

    public boolean inDeep(){
        return !inDeep.get();
    }
    
    public boolean in(){
        return !in.get();
    }

    public void state(){
        if(in() && !inDeep()){
            liberate();
        }
        else if(in() && inDeep()){
            stop();
        }
        else if(!in() && !inDeep()){
            liberate();
        }
        else if(!in() && inDeep()){
            intake();
        }
    }
}
