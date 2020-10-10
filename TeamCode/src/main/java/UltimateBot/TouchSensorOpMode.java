package UltimateBot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import UltimateBot.Sensors.TestSensors;

@TeleOp
public class TouchSensorOpMode extends OpMode {
    TestSensors touch = new TestSensors();
    @Override
    public void init () {
        touch.init (hardwareMap);
    }
    @Override
    public void loop() {
        telemetry.addData("Touch sensor", touch.getTouchSensorState());
    }
}
