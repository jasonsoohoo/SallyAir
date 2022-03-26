// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class TurnOnLimelight extends CommandBase {
  private Turret _turret;
  /** Creates a new TurnOnLimelight. */
  public TurnOnLimelight(Turret turret) {
    _turret = turret;
    addRequirements(turret);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _turret.power_ll_leds(3);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _turret.power_ll_leds(1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
