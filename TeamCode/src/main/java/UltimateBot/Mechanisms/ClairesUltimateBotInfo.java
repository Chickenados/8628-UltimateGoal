package UltimateBot.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

//first attempt at moving the robot using the LearnJavaForFTC book :)
public class ClairesUltimateBotInfo {

    //define motors

    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;

    public void init(HardwareMap hwMap){

        //add add to hwmap and config

        backLeft = hwMap.get(DcMotor.class, "backLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");

        /*
        *setting to run using encoder which should prevent wiggle
        *because encoder runs the motor at a velocity not to a position
        */

        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void setDrivetrainSpeed(double speed){
        backLeft.setPower(speed);
        backRight.setPower(-speed);
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
    }
}
