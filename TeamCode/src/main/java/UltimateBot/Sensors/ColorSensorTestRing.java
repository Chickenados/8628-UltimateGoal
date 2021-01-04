package UltimateBot.Sensors;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@Autonomous (name = "ColorSensorTestRing")
public class ColorSensorTestRing extends LinearOpMode {

    ColorSensor colorSensorport1;
    ColorSensor colorSensorport2;
    DistanceSensor distanceSensorport1;
    DistanceSensor distanceSensorport2;
    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;

    @Override
    public void runOpMode() {
        //so I don't have to add a second value to telemetry when not needed
        String space = " ";
        //is there 4 rings???
        boolean fourRings = false;
        //true if at least one ring detected
        boolean anyring = false;
        // get a reference to the front-facing color sensor.
        colorSensorport1 = hardwareMap.get(ColorSensor.class, "colorSensor");

        // get a reference to the same front facing color sensor but for distance.
        distanceSensorport1 = hardwareMap.get(DistanceSensor.class, "colorSensor");

        // get a reference to the color sensor facing down.
        colorSensorport2 = hardwareMap.get(ColorSensor.class, "colorSensor1");

        // get a reference to the same down facing color sensor but for distance.
        distanceSensorport2 = hardwareMap.get(DistanceSensor.class, "colorSensor1");

        leftFront = hardwareMap.dcMotor.get("frontLeft");

        rightFront = hardwareMap.dcMotor.get("frontRight");

        leftFront.setDirection(DcMotor.Direction.REVERSE);

        leftBack = hardwareMap.dcMotor.get("backLeft");

        rightBack = hardwareMap.dcMotor.get("backRight");

        leftBack.setDirection(DcMotor.Direction.REVERSE);

        // hsvValues is an array that will hold the hue, saturation, and value information of the forward facing color sensor.
        float hsvValuesport1[] = {0F, 0F, 0F};
        // hsvValues1 is an array that will hold the hue, saturation, and value information of the second color sensor.
        float hsvValuesport2[] = {0F, 0F, 0F};
        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;
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
        int distanceFromSensor = (int) distanceSensorport1.getDistance(DistanceUnit.CM);
        int distanceFromSensor1 = (int) distanceSensorport2.getDistance(DistanceUnit.CM);
        // sends the hue back to the driver station to be displayed in a telemetry output (text)
        telemetry.addData("Hue of v3: ", hsvValuesport1[0]);
        telemetry.addData("Hue of v2: ", hsvValuesport2[0]);

        telemetry.update();

        sleep(2000);
        telemetry.clearAll();

        //color sensor positioned at the bottom of the robot detects if there is 0 or any rings next to the robot.
        if (hsvValuesport2[0] > 13 && hsvValuesport2[0] < 30) {
            telemetry.addData("1 ring or 4 rings.", space);
        }
        telemetry.update();
        sleep(1000);


        if (hsvValuesport1[0] > 47 && hsvValuesport1[0] < 75) {
            telemetry.addData("There are 4 rings there. MOVE TO C", space);
            telemetry.update();
            fourRings = true;
            anyring = true;
            leftFront.setPower(-1);
            rightFront.setPower(1);
            leftBack.setPower(-1);
            rightBack.setPower(1);
            sleep(500);
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(0);
        }
        telemetry.update();
        sleep(1000);


        if (hsvValuesport2[0] > 10 && hsvValuesport2[0] < 31 && fourRings != true) {
            telemetry.addData("There is 1 ring there. MOVE TO B", space);
            telemetry.update();
            anyring = true;
            leftFront.setPower(1);
            rightFront.setPower(-1);
            leftBack.setPower(1);
            rightBack.setPower(-1);
            sleep(500);
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(0);

        }
        telemetry.update();
        sleep(1000);


        if (anyring != true && fourRings != true) {
            telemetry.addData("I don't see anyring. 0 rings. MOVE TO A", space);
            telemetry.update();
            leftFront.setPower(1);
            rightFront.setPower(1);
            leftBack.setPower(1);
            rightBack.setPower(1);
            sleep(500);
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(0);
        }
        telemetry.update();
        sleep(1000);

        /*
        sleep(3000);
        if (distanceFromSensor < .1){
            telemetry.addData("There are 4 rings there.", space);
        }
        telemetry.update();
        sleep(3000);
        if (distanceFromSensor > .1){
            telemetry.addData("There are 0 or 1 rings there.", space);
        }
        telemetry.update();
        sleep(3000);
        if (distanceFromSensor1 > .1){
            telemetry.addData("There are 0 rings there.", space);
        }
        telemetry.update();
        sleep(3000);
        if (distanceFromSensor1 < .5){
            telemetry.addData("There are 0 rings there.", space);
        }


        telemetry.update();

         */
    }
}