package UltimateBot.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumDriveBase {
    public MecanumDriveBase(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight){
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        if(frontLeft != null) numMotors++;
        if(frontRight != null) numMotors++;
        if(backLeft != null) numMotors++;
        if(backRight != null) numMotors++;

      //  resetEncoders();

    }
    public enum MotorType{
        FRONT_LEFT(0),
        FRONT_RIGHT(1),
        BACK_LEFT(2),
        BACK_RIGHT(3);

        int value;

        MotorType(int id){
            this.value = id;
        }
    }
    private int numMotors = 0;

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    //Other info
    private double speed = 1.0;
    private double xScale = 1.0;
    private double yScale = 1.0;



    public void mecanumDrive(double forward, double sideways, double rotation){
        if(numMotors != 4){
            throw new IllegalArgumentException("Mecanum drive requires 4 motors!");
        }

        double[] motorPowers = new double[4];
        motorPowers[MotorType.FRONT_LEFT.value] = (forward + sideways + rotation) * speed;
        motorPowers[MotorType.FRONT_RIGHT.value] = (forward - sideways) - rotation * speed;
        motorPowers[MotorType.BACK_LEFT.value] = (forward - sideways) + rotation * speed;
        motorPowers[MotorType.BACK_RIGHT.value] = forward + (sideways - rotation) * speed;

        // Normalize each motor speed so we don't exceed 1.
        motorPowers = normalizeMotorPowers(motorPowers);

        // Set the power of each motor
        frontLeft.setPower(motorPowers[MotorType.FRONT_LEFT.value]);
        frontRight.setPower(motorPowers[MotorType.FRONT_RIGHT.value]);
        backLeft.setPower(motorPowers[MotorType.BACK_LEFT.value]);
        backRight.setPower(motorPowers[MotorType.BACK_RIGHT.value]);
    }

    public double[] normalizeMotorPowers(double[] motorPowers){

        double maxPower = 0.0;

        for(int i = 0; i < motorPowers.length; i++){
            // See which power is the largest.
            if(Math.abs(motorPowers[i]) > maxPower) maxPower = Math.abs(motorPowers[i]);

        }

        // If the maximum power is greater than 1.0, scale all powers down so it equals 1.0
        if(maxPower > 1.0){
            for(int i = 0; i < motorPowers.length; i++){
                motorPowers[i] = motorPowers[i] / maxPower;
            }
        }

        return motorPowers;

    }
   /* public void resetEncoders(){
        if(frontLeft != null) frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if(frontRight != null) frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if(backLeft != null) backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if(backRight != null) backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if(frontLeft != null) frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if(frontRight != null) frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if(backLeft != null) backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if(backRight != null) backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }*/
}
