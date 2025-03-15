package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.Subsystems.CommandSwerveDrivetrain;

public class RobotOriented extends Command {

    private static CommandSwerveDrivetrain drivetrain;
    private Supplier<Double> leftx, rightx, lefty;

    public RobotOriented(CommandSwerveDrivetrain drivetrain, Supplier<Double> leftx, Supplier<Double> rightx, Supplier<Double> lefty){
        RobotOriented.drivetrain = drivetrain;
        this.leftx = leftx;
        this.lefty = lefty;
        this.rightx = rightx;

        addRequirements(drivetrain);
    }
    
    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        drivetrain.seedFieldCentric();
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        drivetrain.applyRequest(() ->
                RobotContainer.drive.withVelocityX(-1 * Math.abs(lefty.get())* lefty.get() * RobotContainer.MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-1 * Math.abs(leftx.get())* leftx.get() * RobotContainer.MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(-rightx.get() * RobotContainer.MaxAngularRate) // Drive counterclockwise with negative X (left)
            );
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        drivetrain.resetRotation(drivetrain.getLastRotation());
    }
}
