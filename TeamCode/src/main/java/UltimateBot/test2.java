package UltimateBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import java.lang.annotation.Annotation;

@Autonomous (name = "test2")

public class test2 extends LinearOpMode {
    enum State {
        START,
        MOVE,
        STOP,
        DONE
    }
    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    ColorSensor colorSensor;
    String ex = "!";
    double lastTime;

    @Override
    public void runOpMode() throws InterruptedException {
        lastTime = getRuntime();
        colorSensor = hardwareMap.colorSensor.get("colorSensor");


        leftFront = hardwareMap.dcMotor.get("frontLeft");
        rightFront = hardwareMap.dcMotor.get("frontRight");
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack = hardwareMap.dcMotor.get("backLeft");
        rightBack = hardwareMap.dcMotor.get("backRight");
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        colorSensor.enableLed(true);
        waitForStart();
        int x = 0;
        int colorSensed = colorSensor.argb();
        telemetry.addData("Forward", ex);
        leftFront.setPower(1);
        rightFront.setPower(1);
        leftBack.setPower(1);
        rightBack.setPower(1);
        if (getRuntime() >= 5.0 + lastTime){

        }
        sleep(500);

        telemetry.addData("Stop", ex);

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        sleep(500);
        telemetry.addData("started", ex);
        if (colorSensed >= 330 && colorSensed <= 360)
        {
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
                x++;
        }


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

