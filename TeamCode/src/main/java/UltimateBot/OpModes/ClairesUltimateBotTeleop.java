package UltimateBot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import UltimateBot.Mechanisms.ClairesUltimateBotInfo;
import UltimateBot.Mechanisms.MecanumDriveBase;

@TeleOp
public class ClairesUltimateBotTeleop extends OpMode {
    ClairesUltimateBotInfo robot = new ClairesUltimateBotInfo();

    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Our Heading", robot.getHeading(AngleUnit.DEGREES));
        //robot.getMotorRevolutions();
        //mecanum drive
        robot.mecanumDrive(gamepad1.right_stick_y, -gamepad1.right_stick_x, -gamepad1.left_stick_x);
        if(gamepad1.left_trigger>=0.01){
            robot.mecanumDrive(0.5*gamepad1.right_stick_y,-0.5*gamepad1.right_stick_x, -0.5*gamepad1.left_stick_x);
        }

        //shooter spinner thingy motor
        if (gamepad1.left_bumper) {
            robot.setSpinnerSpeed(0.75);
        } else{ robot.setSpinnerSpeed(0);}

        //servo that moves the rings towards the spinner
        robot.launcherServoPosition(0.5);
        if (gamepad1.a) {
            robot.launcherServoPosition(1.0);
        } else if (gamepad1.b) {
            robot.launcherServoPosition(0);
        }

        //wobble goal motor
        robot.moveWobble(0);
        if (gamepad2.right_bumper) {
            robot.moveWobble(0.7);
        } else if(gamepad2.left_bumper) {
            robot.moveWobble(-0.7);
        } else{
            robot.moveWobble(0);
        }

        //wobble servo
        robot.grabWobbleGoal(0.5);
        if (gamepad2.x) {
            robot.grabWobbleGoal(1.0);
        } else if (gamepad2.y) {
            robot.grabWobbleGoal(0);
        }

        //intake motor
        if (gamepad1.right_bumper) {
            robot.runIntake(0.5);
        } else{robot.runIntake(0);}

        //intake servo
        robot.moveRingFromIntake(0.5);
        if (gamepad2.a) {
            robot.moveRingFromIntake(1.0);
        } else if (gamepad2.b) {
            robot.moveRingFromIntake(0);
        }

    }
}
