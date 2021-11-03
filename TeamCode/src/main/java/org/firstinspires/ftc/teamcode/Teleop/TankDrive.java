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
        robot.armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        float speedMultiplier = 1;
        int clawPosition = 0;

        //wait for start button to be pressed
        waitForStart();

        //write teleop code here

        while(opModeIsActive()) {
            telemetry.addData("armmotor", robot.armMotor.getCurrentPosition());
            telemetry.update();
            if(gamepad2.y){
                if(speedMultiplier == 1) {
                    speedMultiplier = 0.5f;
                }
                else{
                    speedMultiplier = 1;
                }

            }

            //robot.leftMotor.setPower(speedMultiplier * -gamepad1.left_stick_y);
            //robot.rightMotor.setPower(speedMultiplier * -gamepad1.right_stick_y);


            if(gamepad2.dpad_left) {
                robot.spinMotor.setPower(-0.7);
            }
            if(gamepad2.dpad_right) {
                robot.spinMotor.setPower(0);
            }

           if (robot.armMotor.getCurrentPosition()>= -8000) {
               telemetry.addData("hello", "hello");
               if(gamepad2.dpad_up){
                   robot.armMotor.setPower(-1);
                   //encoderDrive(1,-10000);
               }
               else {
                   robot.armMotor.setPower(0);
               }
               if(gamepad2.dpad_down){
                   robot.armMotor.setPower(1);
                   //encoderDrive(0.5,10000);
               } else {
                   robot.armMotor.setPower(0);
               }
           }

            if(gamepad2.a){
                if(clawPosition == 0) {
                    clawPosition = 1;
                }
                else {
                    clawPosition = 0;
                }
            }
            //robot.clawServo.setPosition(clawPosition);
        }
        //robot.leftMotor.setPower(0);
        //robot.rightMotor.setPower(0);
        robot.armMotor.setPower(0);
        robot.spinMotor.setPower(0);

    }
    public void encoderDrive(double speed,
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
    //Stop robot motors when game is finished
}