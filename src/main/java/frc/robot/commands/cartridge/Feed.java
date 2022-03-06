// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cartridge;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Cartridge;
import frc.robot.subsystems.Shooter;

public class Feed extends CommandBase {
  private Cartridge _cartridge;
  private Shooter _shooter;
  /** Creates a new Empty. */
  public Feed(Cartridge cartridge, Shooter shooter) {
    _cartridge = cartridge;
    _shooter = shooter;
    addRequirements(_cartridge);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(_shooter.get_is_sped()){
      _cartridge.set_cartridge(0.1);
      _cartridge.set_filter(0.1);
    } else {
      _cartridge.set_cartridge(0);
      _cartridge.set_filter(0);
    }
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
