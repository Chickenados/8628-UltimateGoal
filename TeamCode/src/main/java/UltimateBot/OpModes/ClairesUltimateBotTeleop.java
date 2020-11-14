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
        robot.getMotorRevolutions();

        //tank drive
        //robot.setLeftSideSpeed(gamepad1.left_stick_y);
        //robot.setRightSideSpeed(gamepad1.right_stick_y);
        //mecanum drive
        robot.mecanumDrive(gamepad1.right_stick_y, -gamepad1.right_stick_x, -gamepad1.left_stick_x);

    }
}
