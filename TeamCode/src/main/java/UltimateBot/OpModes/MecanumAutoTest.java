package UltimateBot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import UltimateBot.Mechanisms.ClairesUltimateBotInfo;

@Disabled //THIS PROGRAM IS HAZARDOUS TO PEOPLE AND ROBOTS BECAUSE THE ROBOT STOPS AND STARTS SPORADICALLY!!!!!!!!
@Autonomous
public class MecanumAutoTest extends OpMode {
    ClairesUltimateBotInfo ultimateBot = new ClairesUltimateBotInfo();
    double lastTime;
    @Override
    public void init() {
        ultimateBot.init(hardwareMap);
    }

    @Override
    public void loop() {
        lastTime=getRuntime();
        while(getRuntime()-lastTime<=2.0){
            ultimateBot.mecanumDrive(-.7,-.2,0.0);
        }

        ultimateBot.mecanumDrive(0,0,0);
        telemetry.addData("Moved!!",lastTime);
    }
}
