package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.Timer;

public class ElevatorSubsystem extends SubsystemBase{
    private static TalonFX elevatorMotorLeft = new TalonFX(17, "Vegetarian-ivore");
    private static TalonFXConfigurator elevatorMotorLeftC = elevatorMotorLeft.getConfigurator();
    private static TalonFX elevatorMotorRight = new TalonFX(18,"Vegetarian-ivore");
    private static TalonFXConfigurator elevatorMotorRightC = elevatorMotorRight.getConfigurator();
    

    // CurrentLimitsConfigs config = new CurrentLimitsConfigs();
    // config.SupplyCurrentLimitEnable = true;
    // config.SupplyCurrentLimit = 40;
    
    // elevatorMotorLeftC.apply(config);
    // elevatorMotorRightC.apply(config);
    


    //private static ElevatorFeedforward feed = new ElevatorFeedforward(0, 0, 0);
    //private static PowerDistribution pdh = new PowerDistribution(0,ModuleType.kRev);
    

    TalonFXConfiguration cfg = new TalonFXConfiguration();

    private static PositionDutyCycle control = new PositionDutyCycle(0).withSlot(0);

    //private final PIDController elevatorPID = new PIDController(0.003, 0, 0);
    private final PIDController elevatorPID = new PIDController(0.06, 0.0, 0.0);
    private static boolean lock = false;
    DigitalInput home = new DigitalInput(0);

    private final Timer time = new Timer();
    
    public ElevatorSubsystem(){

        // Slot0Configs slot0 = cfg.Slot0;
        // slot0.kS = 0.25; // Add 0.25 V output to overcome static friction
        // slot0.kG = 0.25;
        // slot0.GravityType = GravityTypeValue.Elevator_Static;
        // slot0.kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
        //slot0.kA = 0.01; // An acceleration of 1 rps/s requires 0.01 V output
        // slot0.kP = 0.000001; // A position error of 0.2 rotations results in 12 V output
        //slot0.kI = 0; // No output for integrated error
        //slot0.kD = 0.5; // A velocity error of 1 rps results in 0.5 V output

    //    elevatorMotorLeft.getConfigurator().apply(slot0);
    //     elevatorMotorRight.getConfigurator().apply(slot0);

        elevatorMotorRight.setControl(new Follower(elevatorMotorLeft.getDeviceID(), true));
        //elevatorMotorLeft.setPosition(0);
        home();
    }

    

    public void run(double distance){
        elevatorMotorLeft.set(elevatorPID.calculate(getPosition()*Constants.INCHES_PER_ROTATION_ELEVATOR, distance));
        System.out.println(getPosition()*Constants.INCHES_PER_ROTATION_ELEVATOR);
    }

    

    public void home(){
        //if(!lock){
        //System.out.println(home.get());
            while(!home.get()){
                elevatorMotorLeft.set(-0.1);
            }
            elevatorMotorLeft.set(0);
            //elevatorMotorLeft.setPosition(0);
        //}
    }

    public boolean getLock(){
        return lock;
    }

    public void setLock(boolean locked){
        lock = locked;
    }

    public double getPosition(){
        return elevatorMotorLeft.getPosition().getValueAsDouble();
    }

    public boolean atHeight() {
        return elevatorPID.atSetpoint();
    }
    
}
