package frc.robot.triggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;
import frc.robot.subsystems.Cartridge;

public class queueIdle extends Trigger {
    private Cartridge _cartridge;

    public queueIdle(Cartridge cartridge){
        _cartridge = cartridge;
    }

    @Override
    public boolean get() {
      if(_cartridge.color_proximity_alert(Constants.color_proximity_sensor_threshold)){
        return false;
      } else {
        return true;
      }
    }
}
