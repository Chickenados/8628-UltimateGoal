package UltimateBot.Mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

    //encoders
    private double backLeftTicksPerRev;
    private double backRightTicksPerRev;
    private double frontLeftTicksPerRev;
    private double frontRightTicksPerRev;

    //imu
    private BNO055IMU imu;

    public void init(HardwareMap hwMap){

        //drivetrain systems

        backLeft = hwMap.get(DcMotor.class, "backLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");

        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        backLeftTicksPerRev = backLeft.getMotorType().getTicksPerRev();
        backRightTicksPerRev = backRight.getMotorType().getTicksPerRev();
        frontLeftTicksPerRev = frontLeft.getMotorType().getTicksPerRev();
        frontRightTicksPerRev = frontRight.getMotorType().getTicksPerRev();

        //imu
        hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        imu.initialize(params);

    }


    public void setLeftSideSpeed(double speed){
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
    }

    public void setRightSideSpeed(double speed) {
        backLeft.setPower(speed);
        backRight.setPower(speed);
    }

    public void setSpeed(double speed){
        setRightSideSpeed(speed);
        setLeftSideSpeed(speed);
    }

    public double getBackLeftMotorRevolutions(){
        return backLeft.getCurrentPosition() / backLeftTicksPerRev;
    }
    public double getBackRightMotorRevolutions(){
        return backRight.getCurrentPosition() / backRightTicksPerRev;
    }
    public double getFrontLeftMotorRevolutions(){
        return frontLeft.getCurrentPosition() / frontLeftTicksPerRev;
    }
    public double getFrontRightMotorRevolutions(){
        return frontRight.getCurrentPosition() / frontRightTicksPerRev;
    }

    public void getMotorRevolutions(){
        getBackLeftMotorRevolutions();
        getBackRightMotorRevolutions();
        getFrontLeftMotorRevolutions();
        getFrontRightMotorRevolutions();
    }
    public double getHeading(AngleUnit angleUnit){
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX,
                angleUnit);
        return angles.firstAngle;
    }
}
