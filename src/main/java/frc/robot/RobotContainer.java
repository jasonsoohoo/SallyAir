// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
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
import frc.robot.subsystems.Cartridge;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import frc.robot.triggers.queueHold;
import frc.robot.triggers.queueIdle;
import frc.robot.triggers.queueReject;
import frc.robot.triggers.queueStack;
import frc.robot.triggers.xboxLeftTrigger;
import frc.robot.triggers.xboxRightTrigger;

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

  private final JoystickDrive _JoystickDrive = new JoystickDrive(_drive);
  private final Stow _Stow = new Stow(_intake, _pneumatics);
  private final Dormant _Dormant = new Dormant(_shooter);

  public static XboxController controller_1 = new XboxController(0);
  public static XboxController controller_2 = new XboxController(1);

  //controller 1 binds
  public static JoystickButton xbox_1_y = new JoystickButton(controller_1, 4);
  public static JoystickButton xbox_1_x = new JoystickButton(controller_1, 3);
  public static JoystickButton xbox_1_b = new JoystickButton(controller_1, 2);
  public static JoystickButton xbox_1_a = new JoystickButton(controller_1, 1);

  public static JoystickButton xbox_1_rb = new JoystickButton(controller_1, 6);
  public static JoystickButton xbox_1_lb = new JoystickButton(controller_1, 5);

  public static JoystickButton xbox_1_start = new JoystickButton(controller_1, 8);
  public static JoystickButton xbox_1_select = new JoystickButton(controller_1, 7);

  //controller 2 binds
  public static JoystickButton xbox_2_y = new JoystickButton(controller_2, 4);
  public static JoystickButton xbox_2_x = new JoystickButton(controller_2, 3);
  public static JoystickButton xbox_2_b = new JoystickButton(controller_2, 2);
  public static JoystickButton xbox_2_a = new JoystickButton(controller_2, 1);

  public static JoystickButton xbox_2_rb = new JoystickButton(controller_2, 6);
  public static JoystickButton xbox_2_lb = new JoystickButton(controller_2, 5);

  public static JoystickButton xbox_2_start = new JoystickButton(controller_2, 8);
  public static JoystickButton xbox_2_select = new JoystickButton(controller_2, 7);

  public static xboxRightTrigger x1_right_trigger = new xboxRightTrigger(controller_1);
  public static xboxLeftTrigger x1_left_trigger = new xboxLeftTrigger(controller_1);

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
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Intake Bindings
    x1_right_trigger.whileActiveOnce(new Extend(_intake, _pneumatics));
    xbox_1_rb.whileActiveOnce(new Articulate(_pneumatics));
    //Climb Bindings
    xbox_2_x.whileActiveContinuous(new Elevate(_climb, controller_2));
    xbox_2_lb.whileActiveOnce(new Deploy(_pneumatics));
    //Shooter Bindings
    x1_left_trigger.whileActiveContinuous(new Spinup(_shooter))
                   .whileActiveContinuous(new Feed(_cartridge, _shooter), false);
    xbox_2_a.whenPressed(new CloseLow(_shooter, _pneumatics));
    xbox_2_b.whenPressed(new CloseHigh(_shooter, _pneumatics));
    xbox_2_y.whenPressed(new Far(_shooter, _pneumatics));
    //Cartridge Bindings
    xbox_2_rb.whileActiveContinuous(new Empty(_cartridge), false);
    hold_trigger.whileActiveContinuous(new Hold(_cartridge));
    idle_trigger.whileActiveContinuous(new Idle(_cartridge));
    stack_trigger.whileActiveContinuous(new Stack(_cartridge));
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
