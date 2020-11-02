package UltimateBot.Mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
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

    //mecanum?
    public MecanumDriveBase mecanumDriveBase;

    public void init(HardwareMap hwMap) {

        mecanumDriveBase = new MecanumDriveBase(frontLeft, frontRight, backLeft, backRight);
        //drivetrain systems

        backLeft = hwMap.get(DcMotor.class, "backLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");

        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

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
        imu = hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        //change to default set of parameters go here
        imu.initialize(params);


    }


    //for tank drive
    public void setLeftSideSpeed(double speed) {
        frontLeft.setPower(speed);
        backLeft.setPower(speed);
    }

    public void setRightSideSpeed(double speed) {
        frontRight.setPower(speed);
        backRight.setPower(speed);
    }

    //for auto? we only go forward and back now I guess...
    public void setSpeed(double speed) {
        setRightSideSpeed(speed);
        setLeftSideSpeed(speed);
    }
/*
    public double getBackLeftMotorRevolutions() {
        return backLeft.getCurrentPosition() / backLeftTicksPerRev;
    }

    public double getBackRightMotorRevolutions() {
        return backRight.getCurrentPosition() / backRightTicksPerRev;
    }

    public double getFrontLeftMotorRevolutions() {
        return frontLeft.getCurrentPosition() / frontLeftTicksPerRev;
    }

    public double getFrontRightMotorRevolutions() {
        return frontRight.getCurrentPosition() / frontRightTicksPerRev;
    }

    public void getMotorRevolutions() {
        getBackLeftMotorRevolutions();
        getBackRightMotorRevolutions();
        getFrontLeftMotorRevolutions();
        getFrontRightMotorRevolutions();
    }
*/
    public double getHeading(AngleUnit angleUnit) {
        Orientation angles = imu.getAngularOrientation(AxesReference.EXTRINSIC,
                AxesOrder.ZYX,
                angleUnit);
        return angles.firstAngle;
    }


}
