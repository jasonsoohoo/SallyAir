// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Pneumatics.PneumaticType;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CloseLow extends InstantCommand {
  private Shooter _shooter;
  private Pneumatics _pneumatics;
  public CloseLow(Shooter shooter, Pneumatics pneumatics) {
    _shooter = shooter;
    _pneumatics = pneumatics;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _shooter.set_goal_velocity(Constants.shooter_vel_low_close);
    _pneumatics.set_solenoid(PneumaticType.SHOOTERHOOD, true);
  }
}
