package UltimateBot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import UltimateBot.Mechanisms.ClairesUltimateBotInfo;

@Autonomous

public class AutoMoveForward extends OpMode {
    enum State{
        START,
        MOVEFORWARD,
        PAUSE,
        TURN,
        MOVEAGAIN,
        END
    }
    ClairesUltimateBotInfo robot = new ClairesUltimateBotInfo();
    AutoMoveForward.State state = AutoMoveForward.State.START;
    double lastTime;

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

    @Override
    public void loop() {
        telemetry.addData("State ", state);
        telemetry.addData("Runtime", getRuntime());
        telemetry.addData("Time in state", getRuntime()-lastTime);
        switch (state) {
            case START:
                if (getRuntime() >= 1.0) {
                    state = AutoMoveForward.State.MOVEFORWARD;
                    lastTime = getRuntime();
                }
                break;
            case MOVEFORWARD:
                robot.setSpeed(2.0);
                if (getRuntime() >= lastTime+3.0) {
                    state = State.PAUSE;
                    lastTime = getRuntime();
                }
                break;
            case PAUSE:
                telemetry.addData("Speed", getRuntime());
                robot.setSpeed(0.0);
                    state = State.TURN;
                break;
            case TURN:
                robot.setLeftSideSpeed(1.0);
                if (getRuntime()>=lastTime+3.0) {
                    state = State.MOVEAGAIN;
                }
                break;
            case MOVEAGAIN:
                robot.setSpeed(1.0);
                if (getRuntime()>=lastTime+3.0){
                    state = State.END;
                break;
                }
            case END:
                robot.setSpeed(0);
                break;


            default:
                telemetry.addData("Auto","Finished");

        }
    }
}
