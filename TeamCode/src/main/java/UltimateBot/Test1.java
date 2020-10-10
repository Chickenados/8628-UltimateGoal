package UltimateBot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Test1 extends OpMode {

    @Override
    public void init() {
        String name = "Claire";
        telemetry.addData("Hey", name );
    }
    @Override
    public void loop() {

    }

}
