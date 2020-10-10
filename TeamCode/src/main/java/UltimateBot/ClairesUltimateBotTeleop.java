package UltimateBot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
        if (gamepad1.left_stick_y>0) {
            robot.setDrivetrainSpeed(0.5);
        }
        else if(gamepad1.left_stick_y<0){
            robot.setDrivetrainSpeed(-0.5);
        }
        else{
            robot.setDrivetrainSpeed(0);
        }
    }
}
