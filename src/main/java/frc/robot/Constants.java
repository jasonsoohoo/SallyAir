// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Cartridge Constants
    public static final int cartridge_motor_units = 500;
    public static final double cartrige_motor_output = 0.3;

    public static final int color_sensor_acceptance_proximity = 120;
    public static final int units_to_purge = 30000;
    public static final int filter_unit_increment = 4800;

    // Drive Constants
    public static final int left_drive1 = 0; //CAN ID
    public static final boolean left_drive1_isInverted = false;

    public static final int left_drive2 = 1; //CAN ID
    public static final boolean left_drive2_isInverted = false;

    public static final int right_drive1 = 2; //CAN ID
    public static final boolean right_drive1_isInverted = true;
    
    public static final int right_drive2 = 3; //CAN ID
    public static final boolean right_drive2_isInverted = true;

    //Shooter IDs
    public static final int left_shooter = 4; //CAN ID
    public static final boolean k_left_shooter_sensorPhase = true;
    public static final boolean left_shooter_isInverted = false;

    public static final int right_shooter = 5; //CAN ID
    public static final boolean k_right_shooter_sensorPhase = false;
    public static final boolean right_shooter_isInverted = true;

    //Shooter Gains,Timeout and Velocities
    public static final int k_shooter_slotIDX = 0;
    public static final int k_shooter_pidLoopIDX = 0;
    public static final int k_shooter_timeoutMS = 30;
    public static final Gains k_left_shooter_gains = new Gains(0.25, 0 , 0, 2048/200, 300, 1);

    public static final int shooter_vel_high_far = 350;
    public static final int shooter_vel_high_close = 300;
    public static final int shooter_vel_low_close = 150;

    //Filter Wheel Constants
    public static final int k_filter_wheels_slotIDX = 0;
    public static final int k_filter_wheels_pidLoopIDX = 0;
    public static final int k_filter_wheels_timeoutMS = 30;
    public static final boolean k_filter_wheels_sensorPhase = false;
    public static final Gains k_filter_wheels_gains = new Gains(0.7, 0, 0, 2048/25, 300, 1);

    //Cartridge Motors Constants
    public static final int bottom_cartridge = 6; //CAN ID
    public static final boolean bottom_cartridge_isInverted = false;

    public static final int top_cartridge = 7; //CAN ID
    public static final boolean top_cartridge_isInverted = false;

    //Cartridge Sensor
    public static final int sharp_sensor_port = 0;
    public static final int sharp_sensor_threshold = 0;
    public static final int color_proximity_sensor_threshold = 0;

    //Cartridge Constants
    public static final int k_cartridge_slotIDX = 0;
    public static final int k_cartridge_pidLoopIDX = 0;
    public static final int k_cartrdige_timeoutMS = 30;
    public static final boolean k_cartridge_sensorPhase = false;
    public static final Gains k_cartridge_gains = new Gains(1, 0, 0, 2048/100, 300, 1);

    public static final int cartridge_increment = 800;

    //Intake Constants
    public static final int intake = 16; //CAN ID
    public static final boolean intake_isInverted = false;
    public static final double intake_speed = 0.65;

    //Climb Constants
    public static final int left_climb = 11; //CAN ID
    public static final int right_climb = 10; //CAN ID
    public static final boolean left_climb_inverted = false;
    public static final boolean right_climb_inverted = false;

    //Pneumatics
    public static final int pneumatic_control_module = 20; //CAN ID
    public static final int shooter_hood_forward = 0;
    public static final int shooter_hood_reverse = 15;
    public static final int climb_release_forward = 5;
    public static final int climb_release_reverse = 10;
    public static final int intake_arm_forward = 2;
    public static final int intake_arm_reverse = 13;
    public static final int intake_articulator_forward = 3;
    public static final int intake_articulator_reverse = 12;
    public static final int cartridge_jab_forward = 1;
    public static final int cartridge_jab_reverse = 14;
}
