// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.RobotContainer.*;
import frc.robot.subsystems.Drivetrain;

public class JoystickDrive extends CommandBase {
  private final Drivetrain subsys;
  /** Creates a new JoystickDrive. */
  public JoystickDrive(Drivetrain sub) {
    subsys = sub;
    addRequirements(subsys);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsys.curvatureDrive(controller_1.getLeftY(), controller_1.getRightX(), false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
