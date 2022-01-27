package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Vision.ShippingElementDetector;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import org.firstinspires.ftc.teamcode.Projects.ProjectTank;

@Autonomous(name = "ComplexAuto")
public class ComplexAuto extends LinearOpMode{
    public ProjectTank robot = new ProjectTank();
    OpenCvWebcam webcam;
    ShippingElementDetector detector = new ShippingElementDetector(telemetry);

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);

        webcam.setPipeline(detector);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                // Usually this is where you'll want to start streaming from the camera (see section 4)

                //change this!
                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
        robot.armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        //get the position of the shipping element
        robot.armMotor.setTargetPosition(200);
        robot.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.armMotor.setPower(0.4);
        while(robot.armMotor.isBusy()) {
            // Let the drive team see that we're waiting on the motor
            telemetry.addData("Status", robot.armMotor.getCurrentPosition());
            telemetry.update();
        }
        sleep(2000);
        robot.armMotor.setTargetPosition(600);
        robot.armMotor.setPower(0.4);
        sleep(2000);
        robot.armMotor.setPower(0);
        /*ShippingElementDetector.ShippingElementLocation elementLocation = detector.getShippingElementLocation();
        telemetry.addData("element location:", elementLocation);
        telemetry.update();

        switch (elementLocation) {
            case LEFT:
                //bottom level - level 1
                break;
            case MIDDLE:
                //middle level - level 2
                break;
            case RIGHT:
                //top level - level 3
                break;
        }     */




    }


}


