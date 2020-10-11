package UltimateBot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Test1 extends OpMode {

    @Override
    public void init() {
        String name = "Maggie";

        telemetry.addData("Hello", name );
    }
    @Override
    public void loop() {

    }

}
