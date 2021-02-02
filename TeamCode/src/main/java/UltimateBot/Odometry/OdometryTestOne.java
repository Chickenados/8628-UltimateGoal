package UltimateBot.Odometry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import UltimateBot.Mechanisms.ClairesUltimateBotInfo;

@Autonomous(name= "OdometryTestOne", group = "Odometry")
public class OdometryTestOne extends LinearOpMode {

    @Override
    public void runOpMode() {
        ClairesUltimateBotInfo robot = new ClairesUltimateBotInfo();
        MyOdometryOpmode odom = new MyOdometryOpmode();

    }
}