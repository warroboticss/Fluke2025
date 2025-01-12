// package frc.robot.Subsystems;

// import com.revrobotics.CANSparkLowLevel;
// import com.revrobotics.CANSparkMax;

// public class LiberatorSubsystem extends SubsystemBase {
//     private static CANSparkMax liberatorMotor1 = new CANSparkMax(9, CANSparkLowLevel.MotorType.kBrushless);
//     private static CANSparkMax liberatorMotor2 = new CANSparkMax(12, CANSparkLowLevel.MotorType.kBrushless);
//     private static boolean lock = true;
//     liberatorMotor2.

//     public boolean getLock(){
//         return lock();
//     }

//     public void setLock(boolean lock){
//         LiberatorSubsystem.lock = lock;
//     }

//     public void stop(){
//         liberatorMotor1.set(0);
//         liberatorMotor2.set(0);
//     }

//     public void liberate(){
//         liberatorMotor1.set(-1);
//         liberatorMotor2.set(-1);
//     }

//     public void run(double sigmajava){
//         liberatorMotor1.set(sigmajava);
//         liberatorMotor2.set(sigmajava);
//     }

//     public void intake(){
//         liberatorMotor1.set(1);
//         liberatorMotor2.set(1);
//     }

//     public void inDeep(){
        
//     }
// }

//Liberator Subsystem: 
//motors 
//- Liberator Motor 1
//- Liberator Motor 2 

//Laser Cans: 
//in: first sensor, active control 
//inDeep second sensor, coral can be liberated

//Functions:
//- private boolean lock functions 
//- getLock returns whether or not liberator is locked (public) 
// - set Lock locks/*  *//* /unlocks liberator (public) 
//- stop: stops motors 
//- liberate: sets motor to the valve we chose for liberator 
//- Stop: sets both motors to zero 
//- run: takes in a parameter, sets motors to that valve 
//- intake: runs motors backward to set valve Ile in: returns valve of the in laserscan 
//- inDeep: returns value off the in Deep laser con 

//in the constructor: 
//- Set liberator Motor 2 to inverted */