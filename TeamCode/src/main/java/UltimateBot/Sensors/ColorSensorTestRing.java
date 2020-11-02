package UltimateBot.Sensors;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

@Autonomous (name = "ColorSensorTestRing")
public class ColorSensorTestRing extends LinearOpMode {

    ColorSensor colorSensorforward;
    ColorSensor colorSensortop;

    @Override
    public void runOpMode() {

        // get a reference to the front-facing color sensor.
        colorSensorforward = hardwareMap.get(ColorSensor.class, "colorSensor");

        // get a reference to the color sensor facing down.
        colorSensortop = hardwareMap.get(ColorSensor.class, "colorSensor1");

        // hsvValues is an array that will hold the hue, saturation, and value information of the forward facing color sensor.
        float hsvValuesforward[] = {0F, 0F, 0F};
        // hsvValues1 is an array that will hold the hue, saturation, and value information of the second color sensor.
        float hsvValuestop[] = {0F, 0F, 0F};

        waitForStart();

        // convert the forward facing RGB values to HSV values.
        Color.RGBToHSV((colorSensorforward.red()),
                (colorSensorforward.green()),
                (colorSensorforward.blue()),
                hsvValuesforward);

        // convert the top down RGB values to HSV values.
        Color.RGBToHSV((colorSensortop.red()),
                (colorSensortop.green()),
                (colorSensortop.blue()),
                hsvValuestop);

        // sends the hue back to the driver station to be displayed in a telemetry output (text)
        telemetry.addData("Hue of front: ", hsvValuesforward[0]);
        telemetry.addData("Hue of top-down: ", hsvValuestop[0]);

        sleep(5000);
    }
}