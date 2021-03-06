// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.OI.*;
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
    subsys.curvatureDrive(controller_1.getLeftY()*0.7, controller_1.getRightX()*0.3, true); // change to true later
    SmartDashboard.putNumber("Controller 1 Get Right X", controller_1.getRightX());
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
