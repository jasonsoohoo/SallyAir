package frc.robot.triggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;
import frc.robot.subsystems.Cartridge.SensorType;
import frc.robot.subsystems.Cartridge;

public class queueReject extends Trigger {
    private Cartridge _cartridge;

    public queueReject(Cartridge cartridge){
        _cartridge = cartridge;
    }

    @Override
    public boolean get() {
      if(_cartridge.proximity_alert(SensorType.COLORPROX, Constants.color_proximity_sensor_threshold) &&
         !_cartridge.good_ball()){
        return true;
      }
      return false;
    }
}