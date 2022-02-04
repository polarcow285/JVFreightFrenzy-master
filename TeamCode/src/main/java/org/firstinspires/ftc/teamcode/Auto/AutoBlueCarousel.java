package org.firstinspires.ftc.teamcode.Auto;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Projects.ProjectTank;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoBlueCarousel")
//"tag" that is displayed on driver hub
public class AutoBlueCarousel extends LinearOpMode {
    //creating robot object
    public ProjectTank robot = new ProjectTank();

    @Override
    public void runOpMode() throws InterruptedException {
        //initialize hardware map
        robot.init(hardwareMap);
        robot.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //wait for start button to be pressed
        waitForStart();

        //write autonomous code here

        //3250 encoder counts = 1 tile


        //encoderDrive(1, 3340, 3340);
        //robot.armMotor.setPower(-0.55);
        //sleep(2000);
        //robot.armMotor.setPower(-0.3);
        //arm motor 0.4, 0.2 power for holding
        //robot.clawServo.setPosition(1);

        //encoderDrive(1,-1600,-1600);

        //original code
        encoderDrive(1,-1600,-1600);
        encoderDrive(1,-500,500);
        encoderDrive(1,-250, -250);
        robot.spinMotor.setPower(1);
        sleep(3500);
        encoderDrive(1,600,-600);
        encoderDrive(1,500,500);
        encoderDrive(1,1400,-1400);
        encoderDrive(1,3200, 3200);
        encoderArm(0.6, 200);
        encoderArm(0.2, 200);


        telemetry.addData("armMotorCount", robot.armMotor.getCurrentPosition());
        telemetry.update();


    }

    public void encoderDrive(double speed,
                             double leftCounts, double rightCounts) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.leftMotor.getCurrentPosition() + (int) (leftCounts);
            newRightTarget = robot.rightMotor.getCurrentPosition() + (int) (rightCounts);
            robot.leftMotor.setTargetPosition(newLeftTarget);
            robot.rightMotor.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.leftMotor.setPower(Math.abs(speed));
            robot.rightMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (robot.leftMotor.isBusy() && robot.rightMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        robot.leftMotor.getCurrentPosition(),
                        robot.rightMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftMotor.setPower(0);
            robot.rightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void encoderArm(double speed,
                             double armCounts) {
        int newTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newTarget = robot.armMotor.getCurrentPosition() + (int)(armCounts);
            robot.armMotor.setTargetPosition(newTarget);

            // Turn On RUN_TO_POSITION
            robot.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.armMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            /*while (opModeIsActive() &&
                    (robot.armMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path2", robot.armMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.armMotor.setPower(0);
               */
            // Turn off RUN_TO_POSITION
            robot.armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
}
