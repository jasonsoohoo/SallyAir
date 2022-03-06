// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.cartridge.Feed;
import frc.robot.commands.drive.DigitalDrive;
import frc.robot.commands.intake.Articulate;
import frc.robot.commands.intake.Extend;
import frc.robot.commands.shooter.CloseHigh;
import frc.robot.commands.shooter.Far;
import frc.robot.commands.shooter.Spinup;
import frc.robot.subsystems.Cartridge;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootAndPickup extends SequentialCommandGroup {
  /** Creates a new ShootAndPickup. */
  public ShootAndPickup(Shooter shooter, Drivetrain drivetrain, Pneumatics pneumatics, Cartridge cartridge, Intake intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new CloseHigh(shooter, pneumatics), 
                new Spinup(shooter).alongWith(new Feed(cartridge, shooter)).withTimeout(4),
                new DigitalDrive(drivetrain, -0.3, 0.0).alongWith(new Extend(intake, pneumatics)).alongWith(new Articulate(pneumatics)).withTimeout(1.5),
                new Far(shooter, pneumatics).alongWith(new Spinup(shooter)).alongWith(new Feed(cartridge, shooter)).alongWith(new Extend(intake, pneumatics)).withTimeout(2),
                new Articulate(pneumatics));
  }
}
