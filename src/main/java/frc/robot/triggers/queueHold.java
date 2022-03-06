package frc.robot.triggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;
import frc.robot.subsystems.Cartridge;

public class queueHold extends Trigger {
    private Cartridge _cartridge;

    public queueHold(Cartridge cartridge){
        _cartridge = cartridge;
    }

    @Override
    public boolean get() {
      if(_cartridge.color_proximity_alert(Constants.color_proximity_sensor_threshold) &&
         _cartridge.good_ball() &&
         _cartridge.sharp_proximity_alert(Constants.sharp_sensor_threshold)){
        return true;
      }
      return false;
    }
}
