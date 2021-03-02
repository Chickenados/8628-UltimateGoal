package UltimateBot.Odometry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Measure Encoder Distance")
public class MeasureEncoderDist extends LinearOpMode {

        DcMotor testMotor, testMotor1, testMotor2, testMotor3;
        int avgMotorDistance = 0;

        @Override
        public void runOpMode(){

            testMotor = hardwareMap.dcMotor.get("rf");
            testMotor1= hardwareMap.dcMotor.get("rb");
            testMotor2= hardwareMap.dcMotor.get("lf");
            testMotor3 = hardwareMap.dcMotor.get("lb");

            testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            testMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            testMotor.setDirection(DcMotorSimple.Direction.REVERSE);

            testMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            testMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           // testMotor1.setDirection(DcMotorSimple.Direction.REVERSE);

            testMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            testMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           // testMotor2.setDirection(DcMotorSimple.Direction.REVERSE);

            testMotor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            testMotor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           // testMotor3.setDirection(DcMotorSimple.Direction.REVERSE);

            waitForStart();

            while(opModeIsActive()){
                testMotor.setPower(gamepad1.right_stick_y);
                testMotor1.setPower(gamepad1.right_stick_y);
                testMotor2.setPower(gamepad1.right_stick_y);
                testMotor3.setPower(gamepad1.right_stick_y);
                avgMotorDistance = (testMotor.getCurrentPosition()+testMotor1.getCurrentPosition()+testMotor2.getCurrentPosition()+testMotor3.getCurrentPosition()/4);
                telemetry.addData("Motor Distance: ", avgMotorDistance);
                telemetry.update();

            }

        }


}
