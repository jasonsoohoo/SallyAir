package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.triggers.xboxLeftTrigger;
import frc.robot.triggers.xboxRightTrigger;

public class OI {
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
  
    //Left and Right Triggering as a button
    public static xboxRightTrigger x1_right_trigger = new xboxRightTrigger(controller_1);
    public static xboxLeftTrigger x1_left_trigger = new xboxLeftTrigger(controller_1);
}
