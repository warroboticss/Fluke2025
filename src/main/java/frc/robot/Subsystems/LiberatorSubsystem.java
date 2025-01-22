package frc.robot.Subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DigitalInput;

public class LiberatorSubsystem extends SubsystemBase {
    private static TalonFX liberatorMotor1 = new TalonFX(15);
    private static TalonFX liberatorMotor2 = new TalonFX(69);
    private DigitalInput inDeep = new DigitalInput(0);
    private DigitalInput in = new DigitalInput(0);

    private static boolean lock;
      
    public LiberatorSubsystem(){
        liberatorMotor2.setInverted(true);
        lock = false;
    }

    public boolean getLock(){
        return lock;
    }

    public void setLock(boolean lock){
        LiberatorSubsystem.lock = lock;
    }

    public void stop(){
        liberatorMotor1.set(0);
        liberatorMotor2.set(0);
    }

    public void liberate(){
        liberatorMotor1.set(-0.25);
        liberatorMotor2.set(-0.25);
    }

    public void run(double speed){
        liberatorMotor1.set(speed);
        liberatorMotor2.set(speed);
    }

    public void intake(){
        liberatorMotor1.set(0.25);
        liberatorMotor2.set(0.25);
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
