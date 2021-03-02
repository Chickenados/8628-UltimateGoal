package UltimateBot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import UltimateBot.Mechanisms.ClairesUltimateBotInfo;

//
//This program is essentially AutoMoveForward except that it uses a linearopmode, which I'm not sure if it will work with a state machine.
//So it's a test program and an unfinished auto.
//
@Autonomous
public class CombinedAuto extends LinearOpMode {
    enum State {
        START,
        STRAFE,
        PAUSE,
        FORWARD,
        TURNAROUND,
        BACKTOFOUR,
        STRAFETOONE,
        BACKTOONE,
        END
    }

    ClairesUltimateBotInfo robot = new ClairesUltimateBotInfo();
    CombinedAuto.State state = CombinedAuto.State.START;
    double lastTime;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();
        state = CombinedAuto.State.START;
        resetStartTime();
        lastTime = getRuntime();
        if(opModeIsActive()) {
            telemetry.addData("State ", state);
            telemetry.addData("Runtime", getRuntime());
            telemetry.addData("Time in state", getRuntime() - lastTime);
            switch (state) {
                case START:
                    if (getRuntime() >= 1.0) {
                        state = CombinedAuto.State.STRAFE;
                        lastTime = getRuntime();
                    }
                    telemetry.update();
                    break;
                case STRAFE:
                    //DONT CHANGE THESE PERFECT NUMBERS
                    robot.mecanumDrive(-0.0, 0.45, 0);
                    if (getRuntime() >= lastTime + 1.1) {
                        state = CombinedAuto.State.PAUSE;
                        lastTime = getRuntime();
                    }
                    telemetry.update();
                    break;
                case PAUSE:
                    telemetry.addData("Speed", getRuntime());
                    robot.mecanumDrive(0.0, 0, 0);
                    if (getRuntime() >= lastTime + 1.0) {
                        state = CombinedAuto.State.FORWARD;
                        lastTime = getRuntime();
                    }
                    telemetry.update();
                    break;
                case FORWARD:
                    //DONT CHANGE THESE PERFECT NUMBERS
                    robot.mecanumDrive(-1.0, 0.0, 0);
                    if (getRuntime() >= lastTime + 0.85/*&& rings.equals("single")*/) {
                        state = CombinedAuto.State.BACKTOFOUR;
                        lastTime = getRuntime();
                    } /*else if .85 seconds have passed and rings.equals("quad")*/
                    /*else if .85 seconds have passed and rings.equals("none")*/
                    telemetry.update();
                    break;
                case TURNAROUND:
                    //DONT CHANGE THESE PERFECT NUMBERS
                    robot.mecanumDrive(0.0, 0, 1.0); //180 TURN
                    if (getRuntime() >= lastTime + 2.4) {
                        state = CombinedAuto.State.BACKTOFOUR;
                        lastTime = getRuntime();
                    }
                    telemetry.update();
                    break;
                case BACKTOFOUR:
                    robot.mecanumDrive(0.8, 0.0, 0);
                    if (getRuntime() >= lastTime + 1.0) {
                        state = CombinedAuto.State.END;
                        lastTime = getRuntime();
                    }
                    telemetry.update();
                    break;/*
            case STRAFETOONE:
                //
                robot.mecanumDrive(0.0,0.45,0.0); //180 TURN
                if (getRuntime()>=lastTime+1.1){
                    state = State.BACKTOONE;
                    break;
                }
            case BACKTOONE:
                //
                robot.mecanumDrive(0.8,0,0.0); //180 TURN
                if (getRuntime()>=lastTime+0.4){
                    state = State.END;
                    lastTime=getRuntime();
                    break;
                }
          */
                case END:
                    robot.mecanumDrive(0, 0, 0);
                    break;


                default:
                    telemetry.addData("Auto", "Finished");

            }

        }
    }
}