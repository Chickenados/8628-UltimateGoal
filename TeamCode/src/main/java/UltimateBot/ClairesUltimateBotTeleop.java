package UltimateBot;

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
        telemetry.addData("Heading", robot.getHeading(AngleUnit.DEGREES));

        //move left motors with left stick y
        if (gamepad1.left_stick_y>0) {
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
    }
}
