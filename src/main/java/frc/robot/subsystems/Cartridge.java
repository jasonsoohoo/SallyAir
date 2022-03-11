// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

public class Cartridge extends SubsystemBase {

  public enum SensorType {
    SHARP, COLORPROX, COLOR
  }
  
  private WPI_TalonFX _top = new WPI_TalonFX(top_cartridge);
  private WPI_TalonFX _bottom = new WPI_TalonFX(bottom_cartridge);
  private AnalogInput _sharpSensor;

  private final I2C.Port i2cPort = I2C.Port.kMXP;
  private ColorSensorV3 _colorSensor = new ColorSensorV3(i2cPort);
  private ColorMatch _colorMatcher = new ColorMatch();
  private final Color kBlueTarget = new Color(0.181, 0.423, 0.395);
  private final Color kRedTarget = new Color(0.561, 0.232, 0.114);
  SendableChooser<DriverStation.Alliance> alliance_chooser = new SendableChooser<>();
  /** Creates a new Cartridge. */
  public Cartridge() {
    alliance_chooser.setDefaultOption("Blue Alliance", Alliance.Blue);
    alliance_chooser.addOption("Red Alliance", Alliance.Red);

    _sharpSensor = new AnalogInput(sharp_sensor_port);
    
    _top.configFactoryDefault();
    _bottom.configFactoryDefault();

    _top.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));
    _bottom.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));

    _top.setInverted(top_cartridge_isInverted);
    _bottom.setInverted(bottom_cartridge_isInverted);

    _top.setNeutralMode(NeutralMode.Brake);
    _bottom.setNeutralMode(NeutralMode.Brake);

    _colorMatcher.addColorMatch(kBlueTarget);
    _colorMatcher.addColorMatch(kRedTarget);

    System.out.println("- Cartridge Initialized -");
  }

  public void set_filter(double percent){
    _bottom.set(ControlMode.PercentOutput, percent);
  }

  public void set_cartridge(double percent){
    _top.set(ControlMode.PercentOutput, percent);
  }

  public boolean proximity_alert(SensorType type, int goal_threshold){
    switch(type){
      case COLORPROX:
        return color_proximity_alert(goal_threshold);
      case SHARP:
        return sharp_proximity_alert(goal_threshold);
      default:
        return false;
    }
  }

  public boolean good_ball(){
    if(alliance_chooser.getSelected() == Alliance.Blue){
      return detect_color(kBlueTarget);
    } else {
      return detect_color(kRedTarget);
    }
  }

  // Private Subsytem Methods -- Unexposed to the rest of the robot
  private boolean sharp_proximity_alert(int goal_value){
    if(_sharpSensor.getValue() >= goal_value){
      return true;
    }
    return false;
  } 

  private boolean color_proximity_alert(int goal_value){
    if(_colorSensor.getProximity() >= goal_value){
      return true;
    }
    return false;
  }

  private boolean detect_color(Color targetColor) {
    Color detectedColor = _colorSensor.getColor();
    ColorMatchResult matcher = _colorMatcher.matchClosestColor(detectedColor);
    if(matcher.color == targetColor) {
      return true;
    }
    return false;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Sharp Value", _sharpSensor.getValue());
    SmartDashboard.putNumber("Color Proximity", _colorSensor.getProximity());
    SmartDashboard.putBoolean("isBlue", detect_color(kBlueTarget));
    SmartDashboard.putBoolean("isRed", detect_color(kRedTarget));
    // This method will be called once per scheduler run
  }
}
