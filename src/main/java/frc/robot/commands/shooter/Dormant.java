// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Dormant extends CommandBase {
  private Shooter _shooter;
  /** Creates a new Dormant. */
  public Dormant(Shooter shooter) {
    _shooter = shooter;
    addRequirements(_shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _shooter.set_shooter_manual(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _shooter.set_goal_velocity(SmartDashboard.getNumber("Shot Velocity", 350));
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
