package UltimateBot;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous (name = "test2")

public class test2 extends LinearOpMode {

    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    ColorSensor colorSensor;
    Integer number = 1;
    double lastTime;
    @Override
    public void runOpMode() throws InterruptedException {
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        leftFront = hardwareMap.dcMotor.get("frontLeft");
        rightFront = hardwareMap.dcMotor.get("frontRight");
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack = hardwareMap.dcMotor.get("backLeft");
        rightBack = hardwareMap.dcMotor.get("backRight");
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        colorSensor.enableLed(true);
        waitForStart();
        Color.RGBToHSV((int) (colorSensor.red() * SCALE_FACTOR),
                (int) (colorSensor.green() * SCALE_FACTOR),
                (int) (colorSensor.blue() * SCALE_FACTOR),
                hsvValues);

        telemetry.addData("Forward", number);
        leftFront.setPower(1);
        rightFront.setPower(1);
        leftBack.setPower(1);
        rightBack.setPower(1);

        sleep(500);

        telemetry.addData("Stop", " ");

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        sleep(500);
        telemetry.addData("started", number);

        telemetry.addData("Alpha", colorSensor.alpha());
        telemetry.addData("Red  ", colorSensor.red());
        telemetry.addData("Green", colorSensor.green());
        telemetry.addData("Blue ", colorSensor.blue());
        telemetry.addData("Hue", hsvValues[0]);

        leftFront.setPower(1);
        rightFront.setPower(1);
        rightBack.setPower(1);
        leftBack.setPower(1);
        sleep(300);
        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftBack.setPower(0);

        telemetry.addData("Forward", number);
        leftFront.setPower(1);
        rightFront.setPower(1);
        leftBack.setPower(1);
        rightBack.setPower(1);
        sleep(500);

        telemetry.addData("Stop", number);

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        sleep(500);

        telemetry.addData("Right", number);

        leftFront.setPower(1);
        rightFront.setPower(-1);
        leftBack.setPower(1);
        rightBack.setPower(-1);
        sleep(500);

        telemetry.addData("Stop Finally", number);

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

    }

}

