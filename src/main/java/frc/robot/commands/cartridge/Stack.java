// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cartridge;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Cartridge.SensorType;
import frc.robot.subsystems.Cartridge;

public class Stack extends CommandBase {
  private Cartridge _cartridge;
  /** Creates a new Empty. */
  public Stack(Cartridge cartridge) {
    _cartridge = cartridge;
    addRequirements(_cartridge);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _cartridge.set_cartridge(Constants.cartrige_motor_output); // Cartridge indexing speed until sharp sensor activated.
    _cartridge.set_filter(0); // Filter should stop spinning while cartridge is indexing.
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(_cartridge.proximity_alert(SensorType.SHARP, Constants.sharp_sensor_threshold)){
      return true;
    }
    return false;
  }
}