// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Shooter extends SubsystemBase {
  private final WPI_TalonFX _left = new WPI_TalonFX(left_shooter);
  private final WPI_TalonFX _right = new WPI_TalonFX(right_shooter);
  private PowerDistribution pdp = new PowerDistribution(21, ModuleType.kRev);
  private double goalVelocity = shooter_vel_high_close;
  /** Creates a new Shooter. */
  public Shooter() {
    _left.configFactoryDefault();
    _right.configFactoryDefault();

    _left.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));
    _right.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));

    _left.set(ControlMode.PercentOutput, 0);
    _right.set(ControlMode.PercentOutput, 0);

    _left.setInverted(left_shooter_isInverted);
    _right.setInverted(right_shooter_isInverted);

    _left.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
                                       k_shooter_slotIDX, k_shooter_timeoutMS);

    _left.configNominalOutputForward(0, k_shooter_timeoutMS);
    _left.configNominalOutputReverse(0, k_shooter_timeoutMS);

    _left.configAllowableClosedloopError(k_shooter_slotIDX, 0, k_shooter_timeoutMS);
    _left.setStatusFramePeriod(k_shooter_slotIDX, 100);

    _left.config_kF(k_shooter_slotIDX, k_left_shooter_gains.kF, k_shooter_timeoutMS);
    _left.config_kP(k_shooter_slotIDX, k_left_shooter_gains.kP, k_shooter_timeoutMS);
    _left.config_kI(k_shooter_slotIDX, k_left_shooter_gains.kI, k_shooter_timeoutMS);
    _left.config_kD(k_shooter_slotIDX, k_left_shooter_gains.kD, k_shooter_timeoutMS);

    _right.follow(_left);

    _left.configMotionAcceleration(350, 30);

    _left.setNeutralMode(NeutralMode.Coast);
    _left.setNeutralMode(NeutralMode.Coast);

    pdp.setSwitchableChannel(false);

    System.out.println("- Shooter Initialized -");
  }

  public void rev() {
    _left.set(TalonFXControlMode.Velocity, goalVelocity);
  }

  public void set_goal_velocity(double velocity){
    goalVelocity = velocity;
  }

  public boolean get_is_sped(){
    if(_left.getSelectedSensorVelocity() >= goalVelocity * 31.4159){
      return true;
    }
    return false;
  }

  public void set_shooter_manual(double percent){
    _left.set(TalonFXControlMode.PercentOutput, percent);
  }

  public void set_flash_light(boolean power){
    pdp.setSwitchableChannel(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
