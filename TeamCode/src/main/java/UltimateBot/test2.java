package UltimateBot;

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
    String ex = "!";
    double lastTime;
    @Override
    public void runOpMode() throws InterruptedException {
        colorSensor = hardwareMap.colorSensor.get("colorSensor");


        leftFront = hardwareMap.dcMotor.get("frontLeft");
        rightFront = hardwareMap.dcMotor.get("frontRight");
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack = hardwareMap.dcMotor.get("backLeft");
        rightBack = hardwareMap.dcMotor.get("backRight");
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        colorSensor.enableLed(true);
        waitForStart();
        int colorSensed = colorSensor.argb();
        telemetry.addData("Forward", ex);
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
        telemetry.addData("started", ex);

        while (colorSensed <= 330 || colorSensed >= 360)
        {
        }

        String color = "red";
        telemetry.addData("the color is ", color);
        leftFront.setPower(1);
        rightFront.setPower(1);
        rightBack.setPower(1);
        leftBack.setPower(1);
        sleep(300);
        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftBack.setPower(0);

        telemetry.addData("Forward", ex);
        leftFront.setPower(1);
        rightFront.setPower(1);
        leftBack.setPower(1);
        rightBack.setPower(1);
        sleep(500);

        telemetry.addData("Stop", ex);

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        sleep(500);

        telemetry.addData("Right", ex);

        leftFront.setPower(1);
        rightFront.setPower(-1);
        leftBack.setPower(1);
        rightBack.setPower(-1);
        sleep(500);

        telemetry.addData("Stop Finally", ex);

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }
}

