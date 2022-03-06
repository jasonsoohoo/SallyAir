package frc.robot.triggers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class xboxLeftTrigger extends Trigger {
    XboxController _controller;

    public xboxLeftTrigger(XboxController controller){
        _controller = controller;
    }

    @Override
    public boolean get() {
      if(Math.abs(_controller.getLeftTriggerAxis()) >= 0.1){
        return true;
      } else {
        return false;
      }
    }
}