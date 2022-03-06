package frc.robot.triggers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class xboxRightTrigger extends Trigger {
    XboxController _controller;

    public xboxRightTrigger(XboxController controller){
        _controller = controller;
    }

    @Override
    public boolean get() {
      if(Math.abs(_controller.getRightTriggerAxis()) >= 0.1){
        return true;
      } else {
        return false;
      }
    }
}
