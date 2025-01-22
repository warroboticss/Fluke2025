// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.DefaultLiberatorCmd;
import frc.robot.Subsystems.LiberatorSubsystem;

public class RobotContainer {

  private final LiberatorSubsystem liberatorSubsystem = new LiberatorSubsystem();

  private final CommandXboxController controller = new CommandXboxController(0);
  private final Trigger a = controller.a();

  public RobotContainer() {
    configureBindings();

    liberatorSubsystem.setDefaultCommand(new DefaultLiberatorCmd(liberatorSubsystem, () -> a.getAsBoolean()));
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
