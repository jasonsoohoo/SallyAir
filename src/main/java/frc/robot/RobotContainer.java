// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.auto.ShootAndPickup;
import frc.robot.commands.cartridge.Empty;
import frc.robot.commands.cartridge.Feed;
import frc.robot.commands.cartridge.Hold;
import frc.robot.commands.cartridge.Idle;
import frc.robot.commands.cartridge.Reject;
import frc.robot.commands.cartridge.Stack;
import frc.robot.commands.climb.Deploy;
import frc.robot.commands.climb.Elevate;
import frc.robot.commands.drive.JoystickDrive;
import frc.robot.commands.intake.Articulate;
import frc.robot.commands.intake.Extend;
import frc.robot.commands.intake.Stow;
import frc.robot.commands.shooter.CloseHigh;
import frc.robot.commands.shooter.CloseLow;
import frc.robot.commands.shooter.Dormant;
import frc.robot.commands.shooter.Far;
import frc.robot.commands.shooter.Spinup;
import frc.robot.commands.turret.HomeTurret;
import frc.robot.commands.turret.ManualTurretControl;
import frc.robot.commands.turret.MoveHood;
import frc.robot.commands.turret.TurnOnLimelight;
import frc.robot.subsystems.Cartridge;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.triggers.queueHold;
import frc.robot.triggers.queueIdle;
import frc.robot.triggers.queueReject;
import frc.robot.triggers.queueStack;
import static frc.robot.OI.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain _drive = new Drivetrain();
  private final Shooter _shooter = new Shooter();
  private final Intake _intake = new Intake();
  private final Pneumatics _pneumatics = new Pneumatics();
  private final Cartridge _cartridge = new Cartridge();
  private final Climb _climb = new Climb();
  private final Turret _turret = new Turret();

  private final JoystickDrive _JoystickDrive = new JoystickDrive(_drive);
  private final Stow _Stow = new Stow(_intake, _pneumatics);
  private final Dormant _Dormant = new Dormant(_shooter);
  private final HomeTurret _HomeTurret = new HomeTurret(_turret);

  private queueHold hold_trigger = new queueHold(_cartridge);
  private queueIdle idle_trigger = new queueIdle(_cartridge);
  private queueReject reject_trigger = new queueReject(_cartridge);
  private queueStack stack_trigger = new queueStack(_cartridge);

  private ShootAndPickup auto1 = new ShootAndPickup(_shooter, _drive, _pneumatics, _cartridge, _intake);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    _drive.setDefaultCommand(_JoystickDrive);
    _intake.setDefaultCommand(_Stow);
    _shooter.setDefaultCommand(_Dormant);
    _turret.setDefaultCommand(_HomeTurret);
    // Configure the button bindings
    configureButtonBindings();
    SmartDashboard.putData(CommandScheduler.getInstance());
    SmartDashboard.putData(_drive);
    SmartDashboard.putData(_shooter);
    SmartDashboard.putData(_intake);
    SmartDashboard.putData(_pneumatics);
    SmartDashboard.putData(_cartridge);
    SmartDashboard.putData(_climb);
    SmartDashboard.putData(_turret);

    SmartDashboard.putData("Move Hood", new MoveHood(_turret));
    SmartDashboard.putData("Move Turret", new ManualTurretControl(_turret));
    SmartDashboard.putData("Power Limelight", new TurnOnLimelight(_turret));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Intake Bindings
    x1_right_trigger.whileActiveOnce(new Extend(_intake, _pneumatics)); // Extends intake arm and powers the motors
    xbox_1_rb.whileActiveOnce(new Articulate(_pneumatics));  // Powers the intake wrist
    //Climb Bindings
    xbox_2_x.whileActiveContinuous(new Elevate(_climb, controller_2));

    xbox_2_lb.whenPressed(new Deploy(_pneumatics)); // When I tested this it didn't toggle. It would only release but not clamp back down.
                                                    // Can you check this to see why. You shouldn't have to reset robot code in order to clamp
                                                    // The shooter back down. You should be able to spam this button to clamp/reclamp during
                                                    // teleop. Can you verify my subsytem logic. The other commands use the forward/reverse
                                                    // commands. This is the only command I have used with my toggle method in the pneumatic
                                                    // subsystem so I want to know why that method style is broken.
    
    // - Shooter Bindings -
    x1_left_trigger.whileActiveContinuous(new Spinup(_shooter))
    .whileActiveContinuous(new Feed(_cartridge, _shooter), false);
   
    /*In the above line I place a false in the second parameter spot. This is an optional parameter 
    that makes the command passed into the 1st slot uninterruptible. The code for the cartridge works 
    purely off of environmental variables meaning there is no known robot state. There is no ball 
    count or process recognition. The robot only acts upon the data seen by the sharp sensor and 
    color/proximity sensor. This means that during the shooting process the balls will be passing 
    through the sensors and activating them in several combinations through their trip in the robot 
    and to the shooter. This means that the shoot command sent to the cartridge may be forcefully
    interrupted by a sensed combination of booleans that will trigger another command. To prevent this 
    we make the shoot commad uninterruptible and it will run until the user releases the trigger.*/

    /**Eventually I will condense the shot selection command into one where you pass the shot profile as a parameter
     * instead. The amount of button bindings will stay the same but will cut down on extra commands.
     */

    xbox_2_a.whenPressed(new CloseLow(_shooter, _pneumatics)); // Sets shoot velocity and hood pneumatic
    xbox_2_b.whenPressed(new CloseHigh(_shooter, _pneumatics)); // Sets shoot velocity and hood pneumatic
    xbox_2_y.whenPressed(new Far(_shooter, _pneumatics)); // Sets shoot velocity and hood pneumatic
   
    //Cartridge Bindings
    xbox_2_rb.whileActiveContinuous(new Empty(_cartridge), false); 

    /*The above binding also passes the uninterruptible false statement to prevent it from being
    interrupted while the user is purging the cartridge */

    /**The triggers below call methods in the cartridge that return data. Each trigger looks for a different
     * robot sensor state combination. This is where the meat and potatoes of the logic is. The subsystem should
     * handle minimal business logic. Any cases where I got the boolean logic wrong the below triggers should be tuned
     * unless I messed up one of my data retrieval methods. I'm pretty sure these four triggers are enough to handle all
     * known states of the cartridge. However, I'm pretty sure there are edge cases or weird interactions that I didn't account
     * for. There is no default command for the subsytem because one of these commands should be running at any given time.
     * The cartridge has 6 commands that REQUIRE it (if a command requires a subsystem that means that while it is running
     * another command that also requires it cannot run at the same time, if the currently running command is interruptible the
     * next scheduled command that requies the same subsystem will interrupt the current command and replace it). The four below
     * which are triggered by robot data and 2 more that are uninterruptible and are triggerd by user input.
     */

    hold_trigger.whileActiveContinuous(new Hold(_cartridge));
    idle_trigger.whileActiveContinuous(new Idle(_cartridge, _pneumatics));
    stack_trigger.whileActiveContinuous(new Stack(_cartridge), false);
    reject_trigger.whileActiveContinuous(new Reject(_cartridge, _pneumatics));
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return m_autoCommand;
    return auto1;
  }
}
