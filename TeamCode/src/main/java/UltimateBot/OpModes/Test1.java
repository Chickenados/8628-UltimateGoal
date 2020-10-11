package UltimateBot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Test1 extends OpMode {

    @Override
    public void init() {
<<<<<<< HEAD:TeamCode/src/main/java/UltimateBot/Test1.java
        String name = "Simon";
=======
        String name = "Maggie";
>>>>>>> 767fc7ecc46348e7d7fc670c0a18f123a700d9ef:TeamCode/src/main/java/UltimateBot/OpModes/Test1.java
        telemetry.addData("Hello", name );
    }
    @Override
    public void loop() {

    }

}
