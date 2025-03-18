package frc.robot.Subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralIndexer extends SubsystemBase {
    private final TalonFX IndexMotor = new TalonFX(00, "rio"); // Replace 00 with the actual CAN ID

    public CoralIndexer() {
        // constructor
    }

    public void index() {
        IndexMotor.setControl(new DutyCycleOut(0.2)); // Set motor to run at 20% power
    }
}

