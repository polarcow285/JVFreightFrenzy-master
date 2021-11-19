package org.firstinspires.ftc.teamcode.Auto;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Projects.ProjectPushbotTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;

/*
@Autonomous(name = "TestVuforiaAuto")
public class TestVuforiaAuto extends LinearOpMode{
    public ProjectPushbotTest robot = new ProjectPushbotTest();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.camera = hardwareMap.get(WebcamName.class, "webcam");

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AXWZxrb/////AAABmV1b6nGOGE8vkm2XisMdt8A7XFbo/8CaNRpnqMV1OjTd9o4q2mJEZx+9qgNSNcyzX/Buk/gfG4syevzdWqJ79hApY/tpJS/It5f64LRXlMjjcsdqFSdc9Im/jfiNbb7P6f5vXXCzvvabLyfi9ZMhDUp71mXnzS0CEnNjpe8ttMAW9kysjNCLYNucNimxeAjbt7wJhg8bkvUZevmnKH4xYbY6TyAeMIX5HEe0YGfCVh88J0XLUoKMT6jWT4I8O2ltVL3DH0VJlgnFAS3+LMfYIbDHE+rsH7eo6ZsKQLPbsRr+wjSQHyAUJzA/5KerUeHQdnOpsDz4t34KpMvP4EbIu32MRi7XwGBpZoiMJkSM8fvu";
        parameters.cameraName = robot.camera;

        VuforiaLocalizer vuforia = ClassFactory.getInstance().createVuforia(parameters);
        VuforiaTrackables trackables = vuforia.loadTrackablesFromAsset("Skystone");

        //An image of a redpanda is the first image in the list
        //gets first trackable (image)
        VuforiaTrackable shippingTrackable = trackables.get(0);
       shippingTrackable.setName("shippingTrackable");


        trackables.activate();

        //create listener based on trackable
        VuforiaTrackableDefaultListener shippingListener;
        shippingListener = (VuforiaTrackableDefaultListener) shippingTrackable.getListener();

        OpenGLMatrix shippingLocation = null;

        waitForStart();

        while(opModeIsActive()){
            shippingLocation = shippingListener.getUpdatedRobotLocation();



        }




    }
}*/
