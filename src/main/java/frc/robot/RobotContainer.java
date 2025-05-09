// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
// import frc.robot.Commands.AlgaeElevatorCmd;
// import frc.robot.Commands.AlgaeRoutineCmd;
import frc.robot.Commands.DefaultElevatorCmd;
import frc.robot.Commands.DefaultLiberatorCmd;
import frc.robot.Commands.ElevatorCmd;
import frc.robot.Commands.HomeElevatorCmd;
import frc.robot.Commands.LiberateCommand;
// import frc.robot.Commands.LiberateStop;
import frc.robot.Commands.ManualAlgaeDown;
import frc.robot.Commands.ManualAlgaeUp;
import frc.robot.Commands.ManualElevatorDown;
import frc.robot.Commands.ManualElevatorUp;
// import frc.robot.Commands.ManualLiberate;
import frc.robot.Commands.ManualLiberatorCmd;
import frc.robot.Commands.RemoveAlgae;
import frc.robot.Commands.ReverseIndexCmd;
// import frc.robot.Commands.RobotOriented;
import frc.robot.Commands.ScoreCmd;
import frc.robot.Commands.l4ElevatorCmd;
import frc.robot.Commands.IndexCmd;
import frc.robot.Commands.UpdateCoordinatesCommand;
//import frc.robot.Commands.LimelightCmd;
import frc.robot.Subsystems.CommandSwerveDrivetrain;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.LiberatorSubsystem;
import frc.robot.Subsystems.CoralIndexer;
import frc.robot.Subsystems.vision.LimelightSubsystem;
import frc.robot.Subsystems.vision.LimelightHelpers;
import frc.robot.generated.TunerConstants;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;




public class RobotContainer {
  
  private final LiberatorSubsystem liberatorSubsystem = new LiberatorSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  private final CoralIndexer coralIndexer = new CoralIndexer();
  private final LimelightSubsystem limelightSubsystem = new LimelightSubsystem();

  public static double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    public static double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

    /* Setting up bindings for necessary control of the swerve drive platform */
    public static final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.05).withRotationalDeadband(MaxAngularRate * 0.05) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    private static final SwerveRequest.RobotCentric driveRobotOriented = new SwerveRequest.RobotCentric()
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage);
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
    private final SwerveRequest.FieldCentric swerveRequest = new SwerveRequest.FieldCentric();

    private final Telemetry logger = new Telemetry(MaxSpeed);

    //CHANGE
    private final CommandXboxController controller = new CommandXboxController(0);
    private final CommandXboxController manualController = new CommandXboxController(1);

    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
    //private final LimelightCmd limelightCmd = new LimelightCmd(drivetrain);
    private final Trigger a = controller.a();
    private final Trigger b = controller.b();
    private final Trigger x = controller.x();
    private final Trigger y = controller.y();

    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private final NetworkTableEntry ta = table.getEntry("ta");
    private final NetworkTableEntry tx = table.getEntry("tx");

    //private final SendableChooser<Command> autoChooser;

  public RobotContainer() {
    //autoChooser = AutoBuilder.buildAutoChooser("Test");

    NamedCommands.registerCommand("l1", new ScoreCmd(elevatorSubsystem, liberatorSubsystem, 1));
    NamedCommands.registerCommand("index", new IndexCmd(coralIndexer));
    coralIndexer.setDefaultCommand(new IndexCmd(coralIndexer));
    limelightSubsystem.setDefaultCommand(new UpdateCoordinatesCommand(limelightSubsystem));
    liberatorSubsystem.setDefaultCommand(new DefaultLiberatorCmd(liberatorSubsystem));
    elevatorSubsystem.setDefaultCommand(new DefaultElevatorCmd(elevatorSubsystem));
    //liberatorSubsystem.setDefaultCommand(new LiberateStop(liberatorSubsystem));
    configureBindings();
    SlewRateLimiter filter = new SlewRateLimiter(0.5);


  }

  private void configureBindings() {
     drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                  drive.withVelocityX((-1 * Math.abs(controller.getLeftY())* controller.getLeftY() * MaxSpeed) * 0.5) // Drive forward with negative Y (forward)
                    .withVelocityY((-1 * Math.abs(controller.getLeftX())* controller.getLeftX() * MaxSpeed) * 0.5) // Drive left with negative X (left)
                    .withRotationalRate((-controller.getRightX() * MaxAngularRate) * 0.5) // Drive counterclockwise with negative X (left)
            )
        );


        // reset the field-centric heading on left bumper press
        //controller.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));


        //controller.rightTrigger().onTrue(new AlgaeRoutineCmd(elevatorSubsystem, liberatorSubsystem, 2));
        //controller.leftTrigger().onTrue(new AlgaeRoutineCmd(elevatorSubsystem, liberatorSubsystem, 1));
       a.onTrue(new ScoreCmd(elevatorSubsystem, liberatorSubsystem, 1));
       b.onTrue(new ScoreCmd(elevatorSubsystem, liberatorSubsystem, 2));
       x.onTrue(new ScoreCmd(elevatorSubsystem, liberatorSubsystem, 3));
       y.onTrue(new ScoreCmd(elevatorSubsystem, liberatorSubsystem, 4));
       
      /*  controller.rightBumper().whileTrue(drivetrain.applyRequest(() -> 
       driveRobotOriented.withVelocityX(0.0)
       .withVelocityY(-0.3)
       .withRotationalRate(0.0)
       ));

       controller.leftBumper().whileTrue(drivetrain.applyRequest(() ->
       driveRobotOriented.withVelocityX(0.0)
       .withVelocityY(0.3)
       .withRotationalRate(0.0)
       )); */

       controller.leftTrigger().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));
       controller.rightTrigger().whileTrue(new ReverseIndexCmd(coralIndexer));


       // controller.rightBumper().onTrue(new InstantCommand(liberatorSubsystem::resetToggle));
        //controller.rightBumper().whileTrue(new RobotOriented(drivetrain, () -> controller.getLeftX(), () -> controller.getRightX(), () -> controller.getLeftY()));
        // controller.rightBumper().onTrue(new InstantCommand(() -> drivetrain.setLastRotation(drivetrain.getOperatorForwardDirection())));


        // manual controls
        controller.povUp().whileTrue(new ManualAlgaeUp(liberatorSubsystem));
        controller.povDown().whileTrue(new ManualAlgaeDown(liberatorSubsystem));

        // manualController.a().onTrue(liberatorSubsystem.runOnce(() -> liberatorSubsystem.increaseLeft()));
        // manualController.b().onTrue(liberatorSubsystem.runOnce(() -> liberatorSubsystem.increaseRight()));
        // manualController.x().onTrue(liberatorSubsystem.runOnce(() -> liberatorSubsystem.decreaseLeft()));
        // manualController.y().onTrue(liberatorSubsystem.runOnce(() -> liberatorSubsystem.decreaseRight()));

        // manualController.x().whileTrue(new ManualLiberate(liberatorSubsystem));
        // manualController.b().whileTrue(new LiberateStop(liberatorSubsystem));
  
        //  manualController.a().whileTrue(drivetrain.applyRequest(() -> 
        //       drive.withVelocityX((1 / Math.max(2, Math.min(ta.getDouble(0.0), 6))) * 2.75) // 2.75
        //             .withVelocityY((Math.max(-2.4, Math.min(2.4, -tx.getDouble(0.0))))* 0.15)
        //             .withRotationalRate((-tx.getDouble(0.0) * 0.1) / Math.max(1, ta.getDouble(0.0)))
        // )); 
        //.withVelocityY((Math.max(-2.4, Math.min(2.4, -tx.getDouble(0.0) * Math.sqrt(Math.max(0.1, ta.getDouble(0.0))))) * 0.1))

        // .withRotationalRate(-tx.getDouble(0.0) * 0.05)
        // .withVelocityY((Math.max(-2.4, Math.min(2.4, -tx.getDouble(0.0))))* 0.1)

    //manualController.a().whileTrue(new InstantCommand(() -> System.out.println(ta.getDouble(0.0))));

    // manualController.rightTrigger().whileTrue(drivetrain.applyRequest(() -> 
    //     drive.withVelocityX(0.0) //(-ta.getDouble(0.0)) * 1.4
    //     .withVelocityY(1)
    //     .withRotationalRate(0.0)
    // ));

    // manualController.leftTrigger().whileTrue(drivetrain.applyRequest(() -> 
    //     drive.withVelocityX(0.0) //(-ta.getDouble(0.0)) * 1.4
    //     .withVelocityY(-1)
    //     .withRotationalRate(0.0)
    // ));

      //   manualController.a().whileTrue(Commands.run(() -> {
      //     System.out.println("A button pressed!");
      // }));


        //manualController.y().onTrue(new AlgaeElevatorCmd(elevatorSubsystem, 1.5, liberatorSubsystem));


        
        // manualController.rightTrigger().whileTrue(new ManualElevatorUp(elevatorSubsystem));
        // manualController.leftTrigger().whileTrue(new ManualElevatorDown(elevatorSubsystem));


        drivetrain.registerTelemetry(logger::telemeterize);

  }

  public Command getAutonomousCommand(){
    return new PathPlannerAuto("Forward");
  }
}
