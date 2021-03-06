package UltimateBot.Sensors;


import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestSensors2 {
    private DigitalChannel touchSensor;

    public void init (HardwareMap hardwareMap) {
        touchSensor =hardwareMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
    }

    public boolean getTouchSensorState() {
        return touchSensor.getState();
    }
}

