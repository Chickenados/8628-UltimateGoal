package UltimateBot.Mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous
public class TelemetryTest extends OpMode {
    @Override
    public void init(){

    }
    @Override
    public void loop(){
        for(int i =1; i<=3; i++){
            telemetry.addData("value:",i);
        }
    }
}
