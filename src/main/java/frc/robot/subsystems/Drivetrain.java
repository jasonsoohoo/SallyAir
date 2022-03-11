// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Drivetrain extends SubsystemBase {
  private WPI_TalonFX _left1 = new WPI_TalonFX(left_drive1, "rio");
  private WPI_TalonFX _left2 = new WPI_TalonFX(left_drive2, "rio");
  private WPI_TalonFX _right1 = new WPI_TalonFX(right_drive1, "rio");
  private WPI_TalonFX _right2 = new WPI_TalonFX(right_drive2, "rio");

  private MotorControllerGroup _left = new MotorControllerGroup(_left1, _left2);
  private MotorControllerGroup _right = new MotorControllerGroup(_right1, _right2);

  private DifferentialDrive _drive = new DifferentialDrive(_left, _right);
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    _left1.configFactoryDefault();
    _left2.configFactoryDefault();
    _right1.configFactoryDefault();
    _right2.configFactoryDefault();

   _left1.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 20, 0, 0));
   _left2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 20, 0, 0));
   _right2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 20, 0, 0));
   _right2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 20, 0, 0));

   _left1.configVoltageCompSaturation(10);
   _left2.configVoltageCompSaturation(10);
   _right1.configVoltageCompSaturation(10);
   _right2.configVoltageCompSaturation(10);

   _left1.enableVoltageCompensation(true);
   _left2.enableVoltageCompensation(true);
   _right1.enableVoltageCompensation(true);
   _right2.enableVoltageCompensation(true);

   _left1.set(ControlMode.PercentOutput, 0);
   _left2.set(ControlMode.PercentOutput, 0);
   _right1.set(ControlMode.PercentOutput, 0);
   _right2.set(ControlMode.PercentOutput, 0);

   _left1.setInverted(left_drive1_isInverted);
   _left2.setInverted(left_drive2_isInverted);
   _right1.setInverted(right_drive1_isInverted);
   _right2.setInverted(right_drive1_isInverted);

   _left1.setNeutralMode(NeutralMode.Coast);
   _left2.setNeutralMode(NeutralMode.Coast);
   _right1.setNeutralMode(NeutralMode.Coast);
   _right2.setNeutralMode(NeutralMode.Coast);

   System.out.println("- Drivetrain Intialized - ");
  }

  public void curvatureDrive(double thrust_axis, double rotation_axis, boolean is_squared){
    _drive.curvatureDrive(thrust_axis, -rotation_axis, is_squared);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
