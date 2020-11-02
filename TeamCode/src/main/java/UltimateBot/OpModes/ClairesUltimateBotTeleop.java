package UltimateBot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import UltimateBot.Mechanisms.ClairesUltimateBotInfo;

@TeleOp
public class ClairesUltimateBotTeleop extends OpMode {
    ClairesUltimateBotInfo robot = new ClairesUltimateBotInfo();
    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Our Heading", robot.getHeading(AngleUnit.DEGREES));
        robot.getMotorRevolutions();
        //move left motors with left stick y
        if (gamepad1.left_stick_y>0) {
            robot.setLeftSideSpeed(-1);
        }
        else if(gamepad1.left_stick_y<0){
            robot.setLeftSideSpeed(1.0);
        }
        else{
            robot.setLeftSideSpeed(0);
        }


        //move left motors with right stick y
        if (gamepad1.right_stick_y>0) {
            robot.setRightSideSpeed(-1.0);
        }
        else if(gamepad1.right_stick_y<0){
            robot.setRightSideSpeed(1.0);
        }
        else{
            robot.setRightSideSpeed(0);
        }

        //super secret test
    }
}
