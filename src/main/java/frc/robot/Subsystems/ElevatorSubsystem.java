package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.Timer;

public class ElevatorSubsystem extends SubsystemBase{
    private static TalonFX elevatorMotorLeft = new TalonFX(17);
    private static TalonFXConfigurator elevatorMotorLeftC = elevatorMotorLeft.getConfigurator();
    private static TalonFX elevatorMotorRight = new TalonFX(18);
    private static TalonFXConfigurator elevatorMotorRightC = elevatorMotorRight.getConfigurator();
    

    // CurrentLimitsConfigs config = new CurrentLimitsConfigs();
    // config.SupplyCurrentLimitEnable = true;
    // config.SupplyCurrentLimit = 40;
    
    // elevatorMotorLeftC.apply(config);
    // elevatorMotorRightC.apply(config);
    


    private static ElevatorFeedforward feed = new ElevatorFeedforward(0, 0, 0);
    //private static PowerDistribution pdh = new PowerDistribution(0,ModuleType.kRev);
    PositionDutyCycle control = new PositionDutyCycle(0);
    private final PIDController elevatorPID = new PIDController(1, 0, 0);
    private static boolean lock;
    DigitalInput home = new DigitalInput(0);

    private final Timer time = new Timer();
    
    public ElevatorSubsystem(){
        //elevatorMotorRight.setControl(new Follower(elevatorMotorLeft.getDeviceID(), true));
        home();
    }

    public void run(double distance){
        // setpoint in rotations
        // velocity should be in rotations per seconds
        //pid = new PositionDutyCycle(setpoint, 0.0, false, (feed.calculate(0))/pdh.getVoltage(), 0.0, false, false, false);
        //elevatorMotorLeft.setControl(control.withPosition(distance * Constants.INCHES_TO_ROTATIONS_ELEVATOR));
        elevatorMotorLeft.set(elevatorPID.calculate(getPosition() * Constants.INCHES_TO_ROTATIONS_ELEVATOR, distance));
    }

    public void test(){
        System.out.println("RAN");
        time.start();
        elevatorMotorLeft.set(-0.2);

        if(time.get() > 0.5){
            elevatorMotorLeft.set(0);
            time.stop();
            time.reset();
        }

    }

    

    public void home(){
        if(!lock){
            while(!home.get()){
                elevatorMotorLeft.set(0.2);
            }
            elevatorMotorLeft.set(0);
            elevatorMotorLeft.setPosition(0);
        }
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
