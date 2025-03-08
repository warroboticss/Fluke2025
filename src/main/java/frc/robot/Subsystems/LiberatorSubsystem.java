package frc.robot.Subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;

import au.grapplerobotics.LaserCan;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LiberatorSubsystem extends SubsystemBase {
    private static TalonFX liberatorMotor1 = new TalonFX(15, "rio");
    private static TalonFX liberatorMotor2 = new TalonFX(16, "rio");
    private static TalonFX algaeMotor = new TalonFX(19, "rio");
    private LaserCan lc = new LaserCan(20);
    Orchestra m_orchestra = new Orchestra();
    PositionDutyCycle pid = new PositionDutyCycle(0);
    TalonFXConfiguration cfg = new TalonFXConfiguration();
    private static Timer time = new Timer();
    private boolean coralToggle;

     private final PIDController algaePID = new PIDController(0.1, 0, 0);

    //private static boolean lock;
      
    public LiberatorSubsystem(){
        liberatorMotor2.setControl(new Follower(liberatorMotor1.getDeviceID(), true));
        //lock = false;
        m_orchestra.addInstrument(liberatorMotor1);
        m_orchestra.addInstrument(liberatorMotor2);
        m_orchestra.loadMusic("output.chrp");
        coralToggle = false;

        Slot0Configs slot0 = cfg.Slot0;
        //slot0.kS = 0.25; // Add 0.25 V output to overcome static friction
        // slot0.kG = 0.02;
        // slot0.GravityType = GravityTypeValue.Elevator_Static;
        //slot0.kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
        //slot0.kA = 0.01; // An acceleration of 1 rps/s requires 0.01 V output
        slot0.kP = 0.03; // A position error of 0.2 rotations results in 12 V output
        //slot0.kI = 0; // No output for integrated error
        //slot0.kD = 0.5; // A velocity error of 1 rps results in 0.5 V output

        algaeMotor.setPosition(0);
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
        liberatorMotor1.set(0.15);
    }

    public void run(double speed){
        liberatorMotor1.set(speed);
    }

    public void intake(){
        liberatorMotor1.set(0.075);
    }

    public boolean ifCoral(){
        return lc.getMeasurement().distance_mm < 55;
    }

    public void setCoralToggle(boolean toggle){
        coralToggle = toggle;
    }



    public void state(){
        //System.out.println(coralToggle);
        if(ifCoral()){
            if(time.get() == 0){
                time.start();
            }
            if(time.get() > 0.3){
                coralToggle = true;
            }
        }
        else{
            if(coralToggle){
                System.out.println("RAN");
                stop();
            }
            else{
                intake();
            }
            if(time.get() > 0){
                time.stop();
                time.reset();
            }
        }
    }


    // ALGAE REMOVAL
    public void removeAlgae(){
       // algaeMotor.set(algaePID.calculate(getAlgaePosition(), -42.66));
        System.out.println("RAN");
       algaeMotor.setControl(pid.withPosition(42.66));
    }

    public void resetAlgae(){
        algaeMotor.setControl(pid.withPosition(0).withSlot(0));
    }

    public double getAlgaePosition(){
        return algaeMotor.getPosition().getValueAsDouble();
    }

    public boolean algaeAtPosition(){
        return algaePID.atSetpoint();
    }


    // public void test(){
    //     System.out.println("RAN");
    //     time.start();
    //     algaeMotor.set(0.2);

    //     if(time.get() > 0.5){
    //         algaeMotor.set(0);
    //         time.stop();
    //         time.reset();
    //     }

    // }
}
