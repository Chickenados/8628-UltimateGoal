package UltimateBot.Mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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

    //modules!
    //launcher stuff
    private Servo launcherServo;
    private DcMotor spinner;

    //wobble goal stuff
    private DcMotor wobbleLift;
    private Servo wobbleServo;

    //intake stuff
    private DcMotor intakeMotor;
    private Servo intakeServo;


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

        backLeft = hwMap.get(DcMotor.class, "lb");
        backRight = hwMap.get(DcMotor.class, "rb");
        frontLeft = hwMap.get(DcMotor.class, "lf");
        frontRight = hwMap.get(DcMotor.class, "rf");

        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

      /*  backLeftTicksPerRev = backLeft.getMotorType().getTicksPerRev();
        backRightTicksPerRev = backRight.getMotorType().getTicksPerRev();
        frontLeftTicksPerRev = frontLeft.getMotorType().getTicksPerRev();
        frontRightTicksPerRev = frontRight.getMotorType().getTicksPerRev();
*/

      //modules
        //launcher module stuff
        launcherServo = hwMap.get(Servo.class, "launcherServo");

        spinner = hwMap.get(DcMotor.class, "spinner");
        spinner.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //wobble goal stuff
        wobbleLift = hwMap.get(DcMotor.class, "wobbleLift");
        wobbleLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wobbleLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        wobbleServo = hwMap.get(Servo.class, "wobbleServo");

        //intake stuff
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeServo = hwMap.get(Servo.class, "intakeServo");

        //imu
        imu = hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        //change to default set of parameters go here
        imu.initialize(params);


    }

    //test mecanum
    public void mecanumDrive(double forward, double sideways, double rotation){

        // Set the power of each motor
        frontLeft.setPower((forward + sideways + rotation) * 1.0);
        frontRight.setPower((forward - sideways) - rotation * 1.0);
        backLeft.setPower((forward - sideways) + rotation * 1.0);
        backRight.setPower(forward + (sideways - rotation) * 1.0);


    }

    public double getHeading(AngleUnit angleUnit) {
        Orientation angles = imu.getAngularOrientation(AxesReference.EXTRINSIC,
                AxesOrder.ZYX,
                angleUnit);
        return angles.firstAngle;
    }

    public void setSpinnerSpeed(double speed){
        spinner.setPower(speed);
    }
    public void runIntake(double speed){
        intakeMotor.setPower(speed);
    }
    public void moveWobble(double speed){
        wobbleLift.setPower(speed);
    }
    public void launcherServoPosition(double position){
        launcherServo.setPosition(position);
    }
    public void grabWobbleGoal(double position){
        wobbleServo.setPosition(position);
    }
    public void moveRingFromIntake(double position){
        intakeServo.setPosition(position);
    }

   /* //for tank drive
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


}
