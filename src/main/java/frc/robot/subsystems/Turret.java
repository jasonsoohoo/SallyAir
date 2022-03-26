// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.shooter.CloseHigh;

public class Turret extends SubsystemBase {
  WPI_TalonFX _turret = new WPI_TalonFX(Constants.turret_falc, "rio");
  DigitalInput _leftLimit = new DigitalInput(Constants.dio_left_limit);
  DigitalInput _rightLimit = new DigitalInput(Constants.dio_right_limit);
  Servo _leftServo = new Servo(Constants.left_linear_servo_pwm);
  Servo _rightServo = new Servo(Constants.right_linear_servo_pwm);

  NetworkTable _limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
  private boolean limelightHasTarget = false;
  private double hoodPosition;
  /** Creates a new Turret. */
  public Turret() {
    _turret.configFactoryDefault();
    _turret.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 
                                        Constants.k_turret_pidLoopIDX, Constants.k_turret_timeoutMS);
    _turret.setInverted(Constants.k_turret_inverted);
    _turret.configNominalOutputForward(0, Constants.k_turret_timeoutMS);
    _turret.configNominalOutputReverse(0, Constants.k_turret_timeoutMS);
    _turret.configPeakOutputForward(1, Constants.k_turret_timeoutMS);
    _turret.configPeakOutputReverse(-1, Constants.k_turret_timeoutMS);

    _turret.configClosedloopRamp(0);
    _turret.setNeutralMode(NeutralMode.Brake);
    _turret.set(ControlMode.PercentOutput, 0);
    _turret.setSelectedSensorPosition(0);
    SmartDashboard.putNumber("Turret Position", 500);
    SmartDashboard.putNumber("Hood Position", 0);
    _leftServo.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    _rightServo.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    System.out.println("- Turret Initialized -");
  }

  public void power_ll_leds(int power){
    NetworkTableEntry ledMode = _limelightTable.getEntry("ledMode");
    SmartDashboard.putNumber("TX Error", _limelightTable.getEntry("tx").getNumber(101).doubleValue());
    SmartDashboard.putNumber("LimeLight Has Target", _limelightTable.getEntry("tv").getNumber(101).doubleValue());
    ledMode.setNumber(power);
    begin_limelight_tracking();
  }

  public void begin_limelight_tracking(){
    double current_error = _limelightTable.getEntry("tx").getNumber(0).doubleValue();
    if(Math.abs(current_error) > 3){
      if(current_error > 0){
        turret_move(-0.09);
      } else {
        turret_move(0.09);
      }
    } else {
      turret_move(0);
    }
    double ta = _limelightTable.getEntry("ta").getNumber(0).doubleValue();
    SmartDashboard.putNumber("TA", ta);
    hoodPosition = 180 - (3200 * ta);
    if(hoodPosition < 0){
      hoodPosition = 0;
    }
    hood_go_to_pos();
    SmartDashboard.putNumber("Hood Position", hoodPosition);
    // double calcVel =  -6121.59911*ta + 405.08051;
    double calcVel =  -6121.59911*ta + 430;
    if(calcVel > 375){
      calcVel = 375;
    }
    if(calcVel < 200){
      calcVel = 200;
    }
    SmartDashboard.putNumber("Shot Velocity", calcVel);
  }

  public void turret_move(double output){
    if(output < 0){
      if(!_leftLimit.get()){
        _turret.set(ControlMode.PercentOutput, 0);
      } else {
        _turret.set(ControlMode.PercentOutput, output);
      }
    } else {
      if(!_rightLimit.get()){
        _turret.set(ControlMode.PercentOutput, 0);
      } else {
        _turret.set(ControlMode.PercentOutput, output);
      }
    }
  }

  public boolean turret_home(){
    if(Math.abs(_turret.getSelectedSensorPosition()) > 75){
      turret_return();
      return false;
    }
    _turret.set(ControlMode.PercentOutput, 0);
    return true;
  }

  private void turret_return(){
    if(_turret.getSelectedSensorPosition() > 0){
      turret_move(-0.08);
    } else {
      turret_move(0.08);
    }
  }
  
  public void hood_go_to_pos(){
    adjust_hood(hoodPosition);
    System.out.println("Adjusting hood to " + hoodPosition);
  }

  private void adjust_hood(double adjustment){
    _leftServo.setAngle(adjustment);
    _rightServo.setAngle(adjustment);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Left Limit Switch", !_leftLimit.get());
    SmartDashboard.putBoolean("Right Limit Switch", !_rightLimit.get());
    SmartDashboard.putNumber("Turret Current Position", _turret.getSelectedSensorPosition());
    // This method will be called once per scheduler run
  }
}

