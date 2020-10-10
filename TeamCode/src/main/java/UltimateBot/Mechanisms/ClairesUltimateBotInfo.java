package UltimateBot.Mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class ClairesUltimateBotInfo {

    //define motors

    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;

    //imu
    private BNO055IMU imu;

    public void init(HardwareMap hwMap){

        //add add to hwmap and config

        backLeft = hwMap.get(DcMotor.class, "backLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");


        //setting to run using encoder which should prevent wiggle

        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //imu
        hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        imu.initialize(params);

    }


    public void setLeftSideSpeed(double speed){
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
    }

    public void setRightSideSpeed(double speed) {
        backLeft.setPower(speed);
        backRight.setPower(-speed);
    }

    public void setSpeed(double speed){
        setRightSideSpeed(speed);
        setLeftSideSpeed(speed);
    }

    public double getHeading(AngleUnit angleUnit){
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX,
                angleUnit);
        return angles.firstAngle;
    }
}
