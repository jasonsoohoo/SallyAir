// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class Pneumatics extends SubsystemBase {

  public enum PneumaticType {
    KICKER, ARTICULATOR, INTAKEARM, CLIMBRELEASE, SHOOTERHOOD
  }

  private PneumaticHub pcm = new PneumaticHub(pneumatic_control_module);
  DoubleSolenoid m_kicker = pcm.makeDoubleSolenoid(cartridge_jab_forward, cartridge_jab_reverse);
  DoubleSolenoid m_articulator = pcm.makeDoubleSolenoid(intake_articulator_forward, intake_articulator_reverse);
  DoubleSolenoid m_intake_arm = pcm.makeDoubleSolenoid(intake_arm_forward, intake_arm_reverse);
  DoubleSolenoid m_climb_release = pcm.makeDoubleSolenoid(climb_release_forward, climb_release_reverse);
  DoubleSolenoid m_shooter_hood = pcm.makeDoubleSolenoid(shooter_hood_forward, shooter_hood_reverse);

  /** Creates a new Pneumatics. */
  public Pneumatics() {
    
    //SETTING INITIAL CONFIGURATION
    m_kicker.set(Value.kForward);
    m_articulator.set(Value.kReverse);
    m_intake_arm.set(Value.kReverse);
    m_climb_release.set(Value.kForward);
    m_shooter_hood.set(Value.kReverse);

    System.out.println("- Pneumatics Initialized -");
  }
  //STATUS RETRIEVAL
  public boolean get_status(PneumaticType type){ // RETURNS TRUE IF PNEUMATIC IS FORWARD
    switch(type){
      case KICKER:
        return retrieve_status(m_kicker);
      case ARTICULATOR:
        return retrieve_status(m_articulator);
      case INTAKEARM:
        return retrieve_status(m_intake_arm);
      case CLIMBRELEASE:
        return retrieve_status(m_climb_release);
      case SHOOTERHOOD:
        return retrieve_status(m_shooter_hood);
      default:
        System.out.println("[Pneumatics.java] This line should never be printed. Something is wrong.");
        return false;
    }
  }

  private boolean retrieve_status(DoubleSolenoid which){
    if(which.get() == Value.kForward){
      return true;
    }
    return false;
  }

  // HARDWARE INTERACTION
  public void set_solenoid(PneumaticType name, boolean value){
    switch(name){
      case KICKER:
        dsolenoid_set(m_kicker, value);
        break;
      case ARTICULATOR:
        dsolenoid_set(m_articulator, value);
        break;
      case INTAKEARM:
        dsolenoid_set(m_intake_arm, value);
        break;
      case CLIMBRELEASE:
        dsolenoid_set(m_climb_release, value);
        break;
      case SHOOTERHOOD:
        dsolenoid_set(m_shooter_hood, value);
        break;
    }
  }

  public void toggle_solenoid(PneumaticType name){
    switch(name){
      case KICKER:
        dsolenoid_toggle(m_kicker);
      case ARTICULATOR:
        dsolenoid_toggle(m_articulator);
      case INTAKEARM:
        dsolenoid_toggle(m_intake_arm);
      case CLIMBRELEASE:
        dsolenoid_toggle(m_climb_release);
      case SHOOTERHOOD:
        dsolenoid_toggle(m_shooter_hood);
    }
  }

  private void dsolenoid_set(DoubleSolenoid which, boolean value){ //TRUE = kForward and FALSE = kREVERSE
    if(value){
      which.set(DoubleSolenoid.Value.kForward);
    } else {
      which.set(DoubleSolenoid.Value.kReverse);
    }
  }

  private void dsolenoid_toggle(DoubleSolenoid which){
    if(retrieve_status(which)){
      dsolenoid_set(which, false);
    } else {
      dsolenoid_set(which, true);
    }
  }

  @Override
  public void periodic() {
    pcm.enableCompressorAnalog(60, 110);
    // This method will be called once per scheduler run
  }
}
