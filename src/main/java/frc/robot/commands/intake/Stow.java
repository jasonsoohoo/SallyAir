// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;

public class Stow extends CommandBase {
  private Intake _intake;
  private Pneumatics _pneumatics;
  /** Creates a new Stow. */
  public Stow(Intake intake, Pneumatics pneumatics) {
    _intake = intake;
    _pneumatics = pneumatics;
    addRequirements(_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _pneumatics.intake_arm_reverse();
    _intake.stop_intake_wheels();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}