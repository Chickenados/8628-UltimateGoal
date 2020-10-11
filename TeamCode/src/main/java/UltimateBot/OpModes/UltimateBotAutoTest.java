package UltimateBot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import UltimateBot.Mechanisms.ClairesUltimateBotInfo;

@Autonomous
public class UltimateBotAutoTest extends OpMode{
    enum State {
        START,
        MOVE,
        STOP,
        DONE
    }

    ClairesUltimateBotInfo robot = new ClairesUltimateBotInfo();
    State state = State.START;
    double lastTime;


    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void start(){
        state = State.START;
        resetStartTime();
        lastTime = getRuntime();
    }

    @Override
    public void loop(){
        telemetry.addData("State ", state);
        telemetry.addData("Runtime", getRuntime());
        telemetry.addData("Time in state", getRuntime()-lastTime);
        switch (state) {
            case START:
                if (getRuntime() >= 1.0) {
                    state = State.MOVE;
                    lastTime = getRuntime();
                }
                break;
            case MOVE:
                robot.setSpeed(1.0);
                if (getRuntime() >= lastTime+5.0) {
                    state = State.STOP;
                    lastTime = getRuntime();
                }
                break;
            case STOP:
                telemetry.addData("Speed", getRuntime());
                robot.setSpeed(0.0);
                state = State.DONE;
                break;
            default:
                telemetry.addData("Auto","Finished");
        }
    }
}
