// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cartridge;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Cartridge;
import frc.robot.subsystems.Pneumatics;

public class Reject extends CommandBase {
  private Cartridge _cartridge;
  private Pneumatics _pneumatics;
  /** Creates a new Empty. */
  public Reject(Cartridge cartridge, Pneumatics pneumatics) {
    _cartridge = cartridge;
    _pneumatics = pneumatics;
    addRequirements(_cartridge);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _pneumatics.kicker_reverse();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _cartridge.set_cartridge(0);
    _cartridge.set_filter(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _pneumatics.kicker_forward();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
