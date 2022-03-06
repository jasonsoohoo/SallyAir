// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Pneumatics extends SubsystemBase {

  private PneumaticHub pcm = new PneumaticHub(pneumatic_control_module);
  DoubleSolenoid m_kicker = pcm.makeDoubleSolenoid(cartridge_jab_forward, cartridge_jab_reverse);
  DoubleSolenoid m_articulator = pcm.makeDoubleSolenoid(intake_articulator_forward, intake_articulator_reverse);
  DoubleSolenoid m_intake_arm = pcm.makeDoubleSolenoid(intake_arm_forward, intake_arm_reverse);
  DoubleSolenoid m_climb_release = pcm.makeDoubleSolenoid(climb_release_forward, climb_release_reverse);
  DoubleSolenoid m_shooter_hood = pcm.makeDoubleSolenoid(shooter_hood_forward, shooter_hood_reverse);

  private boolean f_kicker = false;
  private boolean f_articulator = false;
  private boolean f_intake_arm = false;
  private boolean f_climb_release = false;
  private boolean f_shooter_hood = false;
  /** Creates a new Pneumatics. */
  public Pneumatics() {
    m_kicker.set(DoubleSolenoid.Value.kReverse);
    m_articulator.set(DoubleSolenoid.Value.kReverse);
    m_intake_arm.set(DoubleSolenoid.Value.kReverse);
    m_climb_release.set(DoubleSolenoid.Value.kForward);
    m_shooter_hood.set(DoubleSolenoid.Value.kReverse);
  }
  //STATUS RETRIEVAL
  public boolean get_kicker(){
    return f_kicker;
  }
  public boolean get_articulator(){
    return f_articulator;
  }
  public boolean get_intake_arm(){
    return f_intake_arm;
  }
  public boolean get_climb_release(){
    return f_climb_release;
  }
  public boolean get_shooter_hood(){
    return f_shooter_hood;
  }

  // HARDWARE INTERACTION
  public void kicker_forward(){
    m_kicker.set(DoubleSolenoid.Value.kForward);
    f_kicker = true;
  }

  public void kicker_reverse(){
    m_kicker.set(DoubleSolenoid.Value.kReverse);
    f_kicker = false;
  }

  public void kicker_toggle(){
    if(f_kicker == true){
      kicker_reverse();
    } else {
      kicker_forward();
    }
    f_kicker = !f_kicker;
  }

  public void articulator_forward(){
    m_articulator.set(DoubleSolenoid.Value.kForward);
    f_articulator = true;
  }

  public void articulator_reverse(){
    m_articulator.set(DoubleSolenoid.Value.kReverse);
    f_articulator = false;
  }

  public void articulator_toggle(){
    if(f_articulator == true){
      articulator_reverse();
    } else {
      articulator_forward();
    }
    f_articulator = !f_articulator;
  }

  public void intake_arm_forward(){
    m_intake_arm.set(DoubleSolenoid.Value.kForward);
    f_intake_arm = true;
  }

  public void intake_arm_reverse(){
    m_intake_arm.set(DoubleSolenoid.Value.kReverse);
    f_intake_arm = false;
  }

  public void intake_arm_toggle(){
    if(f_intake_arm == true){
      intake_arm_reverse();
    } else {
      intake_arm_forward();
    }
    f_intake_arm = !f_intake_arm;
  }

  public void climb_release_forward(){
    m_climb_release.set(DoubleSolenoid.Value.kForward);
    f_climb_release = true;
  }

  public void climb_release_reverse(){
    m_climb_release.set(DoubleSolenoid.Value.kReverse);
    f_climb_release = false;
  }

  public void climb_release_toggle(){
    if(f_climb_release == true){
      climb_release_reverse();
    } else {
      climb_release_forward();
    }
    f_climb_release = !f_climb_release;
  }

  public void shooter_hood_forward(){
    m_shooter_hood.set(DoubleSolenoid.Value.kForward);
    f_shooter_hood = true;
  }

  public void shooter_hood_reverse(){
    m_shooter_hood.set(DoubleSolenoid.Value.kReverse);
    f_shooter_hood = false;
  }

  public void shooter_hood_toggle(){
    if(f_shooter_hood == true){
      shooter_hood_reverse();
    } else {
      shooter_hood_forward();
    }
    f_shooter_hood = !f_shooter_hood;
  }

  @Override
  public void periodic() {
    pcm.enableCompressorAnalog(60, 110);
    // This method will be called once per scheduler run
  }
}
