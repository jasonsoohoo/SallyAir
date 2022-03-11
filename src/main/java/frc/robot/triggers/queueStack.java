package frc.robot.triggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;
import frc.robot.subsystems.Cartridge.SensorType;
import frc.robot.subsystems.Cartridge;

public class queueStack extends Trigger {
    private Cartridge _cartridge;

    public queueStack(Cartridge cartridge){
        _cartridge = cartridge;
    }

    @Override
    public boolean get() {
      if(_cartridge.proximity_alert(SensorType.COLORPROX, Constants.color_proximity_sensor_threshold)
         && _cartridge.good_ball() && !_cartridge.proximity_alert(SensorType.SHARP, Constants.sharp_sensor_threshold)){
        return true;
      }
      return false;
    }
}