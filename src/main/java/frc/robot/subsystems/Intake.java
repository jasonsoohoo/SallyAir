// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Intake extends SubsystemBase {
  private WPI_TalonFX m_intake = new WPI_TalonFX(intake);
  /** Creates a new Intake. */
  public Intake() {
    m_intake.configFactoryDefault();
    m_intake.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));
    m_intake.set(ControlMode.PercentOutput, 0);
    m_intake.setInverted(intake_isInverted);
    m_intake.setNeutralMode(NeutralMode.Coast);
    System.out.println("- Intake Initialized -");
  }

  public void set_intake_wheels(double percent) {
    m_intake.set(ControlMode.PercentOutput, percent);
  }

  public void stop_intake_wheels() {
    m_intake.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
