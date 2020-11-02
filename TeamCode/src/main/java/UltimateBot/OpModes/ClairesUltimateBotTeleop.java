package UltimateBot.OpModes;
/*
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import UltimateBot.Mechanisms.ClairesUltimateBotInfo;
import UltimateBot.Mechanisms.MecanumDriveTest;

@TeleOp
public class ClairesUltimateBotTeleop extends OpMode {
    ClairesUltimateBotInfo robot = new ClairesUltimateBotInfo();
    MecanumDriveTest test = new MecanumDriveTest();

    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Our Heading", robot.getHeading(AngleUnit.DEGREES));
        robot.getMotorRevolutions();
        test.mecanumDrive(gamepad1.right_stick_y, -gamepad1.right_stick_y,-gamepad1.left_stick_x);
        */
        //move left motors with left stick y
       /* if (gamepad1.left_stick_y>0) {
            robot.setLeftSideSpeed(-0.5);
        }
        else if(gamepad1.left_stick_y<0){
            robot.setLeftSideSpeed(0.5);
        }
        else{
            robot.setLeftSideSpeed(0);
        }

        //move left motors with right stick y
        if (gamepad1.right_stick_y>0) {
            robot.setRightSideSpeed(-0.5);
        }
        else if(gamepad1.right_stick_y<0){
            robot.setRightSideSpeed(0.5);
        }
        else{
            robot.setRightSideSpeed(0);
        }
*/
/*
        //super secret test
    }
}
*/