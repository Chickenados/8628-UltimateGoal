/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package UltimateBot.Sensors;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.robotcore.hardware.DcMotor;

import UltimateBot.Mechanisms.ClairesUltimateBotInfo;
import UltimateBot.OpModes.AutoMoveForward;

/**
 * This 2020-2021 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the Ultimate Goal game elements.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
//change
@Autonomous(name = "WebcamTensorFlow", group = "Concept")
public class WebcamTensorFlow extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";

    String space = " ";
    double lastTime;
    ClairesUltimateBotInfo ultimateBot = new ClairesUltimateBotInfo();
    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            "AV2hPmr/////AAABmQLD9hUunkK4tSZiwFAlrpZPoN76Ej8hCf1AdzRK5+dWdO6VF0iKY/cqgZLxkQ4RCD0KXMvXtiUx87IkUWaghhJYq446Zx2MDU12MXtsE9hq8p3alcdmCCvCun+veOD/mwKlEXDnZYl8jMzxcCOpEqr3Uc2MzsjpFbrdr+m5tYXmNAKQrN9Bq4VALSSl/pUhk1/swPiJenMa938xu0pN4C+xuOCyAmNX44yln0q8GnoGmtmdMCg3NTOiEDm6K/fFTLI1nWN2LOWzVQZ88Ul0EIjgdTfA+DYgz5O8AS/leZcUn7WTbPbhy/5NaqorhI+6u1YMYYFaPq41j3lenoUU+6DdfK133dZ8+M57EvFVXJSv";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;


    @Override
    public void runOpMode() {
        lastTime = getRuntime();
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        initTfod();
        //Check if this is right!!!!! -claire
        ultimateBot.init(hardwareMap);
        /*lf = hardwareMap.dcMotor.get("lf");

        rf = hardwareMap.dcMotor.get("rf");

        lb = hardwareMap.dcMotor.get("lb");

        rb = hardwareMap.dcMotor.get("rb");

        lb.setDirection(DcMotor.Direction.REVERSE);
*/
        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 1.78 or 16/9).

            // Uncomment the following line if you want to adjust the magnification and/or the aspect ratio of the input images.
            tfod.setZoom(2.5, 2.15);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();
        String rings = "none";
        if (opModeIsActive()) {
            lastTime = getRuntime();
            while (opModeIsActive() && rings.equals("none") || getRuntime()-lastTime<=5.0) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        // step through the list of recognitions and display boundary info.
                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            rings = recognition.getLabel();


                        }
                        telemetry.update();
                        lastTime = getRuntime();
                        break;
                    }
                }
            }
        }
        telemetry.addData("before", space);
        sleep(3000);
        if (opModeIsActive()) {
            telemetry.addData("after", space);
            sleep(3000);
            if (rings.equals("Quad")) {
                //strafe to wall, move forward to square between 0 and 4, turn 180 degrees, drop wobble goal
                telemetry.addData("There are 4 rings there. MOVE TO C", space);
                telemetry.update();

                //strafe to wall
                ultimateBot.mecanumDrive(0.0, 0.45, 0.0);
                sleep(1100);

                ultimateBot.mecanumDrive(0.0, 0.0, 0.0);
                sleep(1000);

                //move forward to 0
                ultimateBot.mecanumDrive(-1.0, 0.0, -0.1);
                sleep(8500);

                //move forward to 4
                ultimateBot.mecanumDrive(-0.9, 0.0, 0.0);
                sleep(1000);

                ultimateBot.mecanumDrive(0.0, 0.0, 0.0);
                sleep(1000);

                //turn around
                ultimateBot.mecanumDrive(0.0,0.0,1.0);
                sleep(2400);

                ultimateBot.mecanumDrive(0.0, 0.0, 0.0);

                //move wobble goal down
                ultimateBot.moveWobble(0.7);
                sleep(3000);

                ultimateBot.moveWobble(0.0);

                //CHECK THIS ONE!!!!!!!!!!
                //open servo
                ultimateBot.grabWobbleGoal(0.6);
            }
            else if (rings.equals("Single")) {
            //strafe to wall, move forward to space between 0 and 4, turn 90 degrees, drop wobble goal
            telemetry.addData("There is 1 ring there. MOVE TO B", space);
            telemetry.update();

                //strafe to wall
                ultimateBot.mecanumDrive(0.0, 0.45, 0.0);
                sleep(1100);

                ultimateBot.mecanumDrive(0.0, 0.0, 0.0);
                sleep(1000);

                //move forward to 0
                ultimateBot.mecanumDrive(-1.0, 0.0, -0.1);
                sleep(8500);

                //move forward to 4
                ultimateBot.mecanumDrive(-0.9, 0.0, 0.0);
                sleep(1000);

                ultimateBot.mecanumDrive(0.0, 0.0, 0.0);
                sleep(1000);

                //turn 90 degrees (??????)
                ultimateBot.mecanumDrive(0.0,0.0,1.0);
                sleep(1200);

                ultimateBot.mecanumDrive(0.0, 0.0, 0.0);

                //move wobble goal down
                ultimateBot.moveWobble(0.7);
                sleep(3000);

                ultimateBot.moveWobble(0.0);

                //CHECK THIS ONE!!!!!!!!!!
                //open servo
                ultimateBot.grabWobbleGoal(0.6);

            } else {
                //strafe, move to 0, turn 180 degrees, drop wobble goal
                telemetry.addData("There are 0 rings there. MOVE TO A", space);
                telemetry.update();

                //strafe to wall
                ultimateBot.mecanumDrive(0.0, 0.45, 0.0);
                sleep(1100);

                ultimateBot.mecanumDrive(0.0, 0.0, 0.0);
                sleep(1000);

                //move forward to 0
                ultimateBot.mecanumDrive(-1.0, 0.0, -0.1);
                sleep(8500);

                ultimateBot.mecanumDrive(0.0, 0.0, 0.0);

                //turn 180 degrees
                ultimateBot.mecanumDrive(0.0,0.0,1.0);
                sleep(2400);

                //move wobble goal down
                ultimateBot.moveWobble(0.7);
                sleep(3000);

                ultimateBot.moveWobble(0.0);

                //CHECK THIS ONE!!!!!!!!!!
                //open servo
                ultimateBot.grabWobbleGoal(0.6);

            }
        }
        if (tfod != null) {
            tfod.shutdown();
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
}
