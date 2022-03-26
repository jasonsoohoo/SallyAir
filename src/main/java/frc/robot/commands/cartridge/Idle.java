// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cartridge;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Cartridge;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Pneumatics.PneumaticType;

public class Idle extends CommandBase {
  private Cartridge _cartridge;
  private Pneumatics _pneumatics;
  /** Creates a new Empty. */
  public Idle(Cartridge cartridge, Pneumatics pneumatics) {
    _cartridge = cartridge;
    _pneumatics = pneumatics;
    addRequirements(_cartridge);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _pneumatics.set_solenoid(PneumaticType.KICKER, true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _cartridge.set_filter(0.2);
    _cartridge.set_cartridge(0);
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
