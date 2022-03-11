// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Climb extends SubsystemBase {
  private WPI_TalonFX _left = new WPI_TalonFX(left_climb);
  private WPI_TalonFX _right = new WPI_TalonFX(right_climb);
  /** Creates a new Climb. */
  public Climb() {
    _left.configFactoryDefault();
    _right.configFactoryDefault();

    _left.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));
    _right.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));

    _left.configVoltageCompSaturation(12);
    _right.configVoltageCompSaturation(12);

    _left.enableVoltageCompensation(true);
    _right.enableVoltageCompensation(true);

    _left.set(ControlMode.PercentOutput, 0);
    _right.set(ControlMode.PercentOutput, 0);

    _left.setInverted(left_climb_inverted);
    _right.setInverted(right_climb_inverted);

    _right.follow(_left);

    System.out.println("- Climb Initialized -");
  }

  public void set_climb(double percent){
    _left.set(ControlMode.PercentOutput, percent);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
