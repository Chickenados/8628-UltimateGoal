package UltimateBot.Sensors;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

@Autonomous (name = "ColorSensorTestRing")
public class ColorSensorTestRing extends LinearOpMode {

    ColorSensor colorSensorport1;
    ColorSensor colorSensorport2;

    @Override
    public void runOpMode() {
        //so I don't have to add a second value to telemetry when not needed
        String space = " ";
        //is there 4 rings???
        boolean fourRings = false;
        //true if at least one ring detected
        boolean anyring;
        // get a reference to the front-facing color sensor.
        colorSensorport1 = hardwareMap.get(ColorSensor.class, "colorSensor");

        // get a reference to the color sensor facing down.
        colorSensorport2 = hardwareMap.get(ColorSensor.class, "colorSensor1");

        // hsvValues is an array that will hold the hue, saturation, and value information of the forward facing color sensor.
        float hsvValuesport1[] = {0F, 0F, 0F};
        // hsvValues1 is an array that will hold the hue, saturation, and value information of the second color sensor.
        float hsvValuesport2[] = {0F, 0F, 0F};

        waitForStart();

        // convert the forward facing RGB values to HSV values.
        Color.RGBToHSV((colorSensorport1.red()),
                (colorSensorport1.green()),
                (colorSensorport1.blue()),
                hsvValuesport1);

        // convert the top down RGB values to HSV values.
        Color.RGBToHSV((colorSensorport2.red()),
                (colorSensorport2.green()),
                (colorSensorport2.blue()),
                hsvValuesport2);

        // sends the hue back to the driver station to be displayed in a telemetry output (text)
        telemetry.addData("Hue of v3: ", hsvValuesport1[0]);
        telemetry.addData("Hue of v2: ", hsvValuesport2[0]);

        telemetry.update();

        sleep(2000);
        telemetry.clearAll();

        //color sensor positioned at the bottom of the robot detects if there is 0 or any rings next to the robot.

        if (hsvValuesport1[0] > 47 && hsvValuesport1[0] < 70) {
            telemetry.addData("There are 1 or 4 rings there.", space);
            anyring = true;
        }

        else {
            telemetry.addData("I couldn't detect anyring. 0 rings there!", space);
            anyring = false;
        }

        telemetry.update();

        sleep(1000);

        //color sensor around halfway up the robot detects if there are any rings there.
        if (hsvValuesport2[0] > 13 && hsvValuesport2[0] < 29) {
            telemetry.addData("There are 4 rings on this stack!", space);
            fourRings = true;
        }

        if (fourRings == false && anyring==true) {
            telemetry.addData("There is 1 ring on this stack!", space);
        }

        telemetry.update();

        sleep(3000);
        telemetry.update();
    }
}