package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Projects.ProjectTank;

@TeleOp(name="TankDrive")
public class TankDrive extends LinearOpMode{
    public ProjectTank robot = new ProjectTank();

    @Override
    public void runOpMode() throws InterruptedException {
        //initialize hardware map
        robot.init(hardwareMap);
        //robot.armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        float speedMultiplier = 1;
        int clawPosition = 0;

        //wait for start button to be pressed
        waitForStart();

        //write teleop code here

        while(opModeIsActive()) {
            if(gamepad1.y){
                if(speedMultiplier == 1) {
                    speedMultiplier = 0.5f;
                }
                else{
                    speedMultiplier = 1;
                }

            }

            robot.leftMotor.setPower(speedMultiplier * -gamepad1.left_stick_y);
            robot.rightMotor.setPower(speedMultiplier * -gamepad1.right_stick_y);

            if(gamepad1.x == true){
                robot.armMotor.setPower(0.5);
            }
            else {
                robot.armMotor.setPower(0);
            }
            if(gamepad1.b == true){
                robot.armMotor.setPower(-0.7);
            } else {
                robot.armMotor.setPower(0);
            }

            if(gamepad1.dpad_left) {
                robot.spinMotor.setPower(-0.7);
            }
            if(gamepad1.dpad_right) {
                robot.spinMotor.setPower(0);
            }

           // if (robot.armMotor.getCurrentPosition()>= 3000 || robot.armMotor.getCurrentPosition()<=0) {
               // robot.armMotor.setPower(0);
           // }

            if(gamepad1.a){
                if(clawPosition == 0) {
                    clawPosition = 1;
                }
                else {
                    clawPosition = 0;
                }
            }
            robot.clawServo.setPosition(clawPosition);
        }
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
        robot.armMotor.setPower(0);
        robot.spinMotor.setPower(0);

    }
    public void encoderDrive(double speed,
                             double leftCounts, double rightCounts) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.leftMotor.getCurrentPosition() + (int)(leftCounts);
            newRightTarget = robot.rightMotor.getCurrentPosition() + (int)(rightCounts);
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
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
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

            //  sleep(250);   // optional pause after each move
        }
    }
    //Stop robot motors when game is finished
}