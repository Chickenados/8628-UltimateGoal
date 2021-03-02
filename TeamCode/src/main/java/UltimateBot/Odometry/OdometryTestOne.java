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
        odom.goToPosition(20,10,0.7,0.0,2.0);
        robot.setSpinnerSpeed(1.0);
        if(getRuntime()>=15.0){
            robot.setSpinnerSpeed(0.0);
        }
    }
}