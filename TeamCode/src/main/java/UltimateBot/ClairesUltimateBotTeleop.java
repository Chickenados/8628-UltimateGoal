package UltimateBot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import UltimateBot.Mechanism.ClairesUltimateBotInfo;

@TeleOp
public class ClairesUltimateBotTeleop extends OpMode {
    ClairesUltimateBotInfo robot = new ClairesUltimateBotInfo();
    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void loop(){
        robot.setDrivetrainSpeed(.5);
    }
}
