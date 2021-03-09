package UltimateBot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import UltimateBot.Mechanisms.ClairesUltimateBotInfo;

@Autonomous
public class AutoMoveForward extends OpMode {
    enum State{
        START,
        STRAFE,
        PAUSE,
        FORWARD,
        TURNAROUND,
        GOTOFOUR,
        STRAFETOONE,
        GOTOONE,
        END
    }
    ClairesUltimateBotInfo robot = new ClairesUltimateBotInfo();
    AutoMoveForward.State state = AutoMoveForward.State.START;
    double lastTime;
//up to here
    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void start(){
        state = AutoMoveForward.State.START;
        resetStartTime();
        lastTime = getRuntime();
    }
//up to here but in runopmode not start
    @Override
    public void loop() {
        telemetry.addData("State ", state);
        telemetry.addData("Runtime", getRuntime());
        telemetry.addData("Time in state", getRuntime()-lastTime);
        switch (state) {
            case START:
                if (getRuntime() >= 1.0) {
                    state = AutoMoveForward.State.STRAFE;
                    lastTime = getRuntime();
                }
                break;
            case STRAFE:
                //DONT CHANGE THESE PERFECT NUMBERS
                robot.mecanumDrive(-0.0,0.45,0);
                if (getRuntime() >= lastTime+1.1) {
                    state = State.PAUSE;
                    lastTime = getRuntime();
                }
                break;
            case PAUSE:
                telemetry.addData("Speed", getRuntime());
                robot.mecanumDrive(0.0,0,0);
                if (getRuntime() >= lastTime+1.0) {
                    state = State.FORWARD;
                    lastTime = getRuntime();
                }
                break;
            case FORWARD:
                //DONT CHANGE THESE PERFECT NUMBERS
                robot.mecanumDrive(-1.0,0.0,-0.1);
                if (getRuntime() >= lastTime+0.85/*&& rings.equals("single")*/) {
                    state = State.GOTOFOUR;//changes based on number of rings, go to TURNAROUND if "none" is true
                    lastTime=getRuntime();
                } /*else if .85 seconds have passed and rings.equals("quad")*/
                /*else if .85 seconds have passed and rings.equals("none")*/
                break;

            case GOTOFOUR:
                //Go to this state when "Quad" is true after FORWARD, before TURNAROUND
                robot.mecanumDrive(-0.9,0.0,0);
                if (getRuntime()>=lastTime+1.0) {
                    state = State.TURNAROUND;
                    lastTime=getRuntime();
                }
                break;
            case TURNAROUND:
                //Go to this state before END every time no matter how many rings
                //DONT CHANGE THESE PERFECT NUMBERS
                robot.mecanumDrive(0.0,0,1.0); //180 TURN
                if (getRuntime() >= lastTime+2.4){
                    state = State.END;
                    lastTime=getRuntime();
                    break;
                }/*
            case STRAFETOONE:
                //Go to this state when "Single"" is true after BACKTOONE before TURNAROUND
                robot.mecanumDrive(0.0,-0.45,0.0); //180 TURN
                if (getRuntime()>=lastTime+1.1){
                    state = State.GOTOONE;
                    break;
                }
            case GOTOONE:
                //Go to this state
                robot.mecanumDrive(-0.8,0,0.0); //180 TURN
                if (getRuntime()>=lastTime+0.4){
                    state = State.END;
                    lastTime=getRuntime();
                    break;
                }
          */  case END:
                robot.mecanumDrive(0,0,0);
                break;


            default:
                telemetry.addData("Auto","Finished");

        }
    }
}
