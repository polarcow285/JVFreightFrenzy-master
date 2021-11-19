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


        float speedMultiplier = 1;
        double spinSpeed = 0;
        boolean rightBumperPressed = false;
        boolean isSpeedMultiplierOn = false;
        boolean dPadLeftPressed = false;
        boolean isCarouselSpinningLeft = false;
        boolean dPadRightPressed = false;
        boolean isCarouselSpinningRight = false;
        boolean aPressed = false;
        boolean isClawClosed = false;

        //wait for start button to be pressed
        waitForStart();
        //robot.armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //write teleop code here

        //top level: -107
        //middle level: -45
        //bottom level: -15
        while(opModeIsActive()) {
            //new speed multiplier
            /*if(gamepad1.right_bumper){
                if(rightBumperPressed == false) {
                    rightBumperPressed = true;
                    isSpeedMultiplierOn = !isSpeedMultiplierOn;
                    if(isSpeedMultiplierOn == true) {
                        robot.leftMotor.setPower(0.4);
                        robot.rightMotor.setPower(0.4);
                    }
                    else {
                        robot.leftMotor.setPower(0.8);
                        robot.rightMotor.setPower(0.8);
                    }
                }
            }
            else {
                rightBumperPressed = false;
            }*/
            //original speed multiplier code
            if(gamepad1.right_bumper){
                if(speedMultiplier == 0.8f) {
                    speedMultiplier = 0.5f;
                }
                else{
                    speedMultiplier = 0.8f;
                }
            }
            robot.leftMotor.setPower(speedMultiplier * -gamepad1.left_stick_y);
            robot.rightMotor.setPower(speedMultiplier * -gamepad1.right_stick_y);

            //Controls spin motor
            //future: only use one button
            /*if(gamepad1.dpad_left){
                if(dPadLeftPressed == false) {
                    dPadLeftPressed = true;
                    isCarouselSpinningLeft = !isCarouselSpinningLeft;
                    if(isCarouselSpinningLeft == true) {
                        robot.spinMotor.setPower(1);
                    }
                    else {
                        robot.spinMotor.setPower(0);
                    }
                }
            }
            else {
                dPadLeftPressed = false;
            }

            if(gamepad1.dpad_right){
                if(dPadRightPressed == false) {
                    dPadRightPressed = true;
                    isCarouselSpinningRight = !isCarouselSpinningRight;
                    if(isCarouselSpinningRight == true) {
                        robot.spinMotor.setPower(-1);
                    }
                    else {
                        robot.spinMotor.setPower(0);
                    }
                }
            }
            else {
                dPadRightPressed = false;
            }*/

            //original carousel code
            /*if(gamepad2.dpad_left) {
                if (spinSpeed == 0) {
                    spinSpeed = 1;
                }
                else{
                    spinSpeed = 0;
                }
            }
            if(gamepad2.dpad_right) {
                if (spinSpeed == 0) {
                    spinSpeed = -1;
                }
                else{
                    spinSpeed = 0;
                }
            }
            robot.spinMotor.setPower(spinSpeed);*/

            //claw
            if(gamepad2.a){
                if(aPressed == false) {
                     aPressed = true;
                     isClawClosed = !isClawClosed;
                     if(isClawClosed == true) {
                         robot.clawServo.setPosition(0);
                     }
                     else {
                         robot.clawServo.setPosition(1);
                    }
                }
            }
            else {
                aPressed = false;
            }



            //move the armMotor up
            if(gamepad2.left_bumper){

                if (robot.armMotor.getCurrentPosition() > 50){
                    robot.armMotor.setPower(0);
                }


                robot.armMotor.setPower(0.35);

                telemetry.addData("armMotorCount", robot.armMotor.getCurrentPosition());
                telemetry.update();

            }
            //hold the arm at the current position
            else if(gamepad2.right_bumper) {
                robot.armMotor.setPower(0.2);
            }
            else if(gamepad2.left_bumper && gamepad2.right_bumper){
                robot.armMotor.setPower(0.5);
            }
            else if (gamepad2.x){
                robot.armMotor.setPower(-.75);

            }
            else {
                robot.armMotor.setPower(0);
            }



            telemetry.addData("armMotorCount", robot.armMotor.getCurrentPosition());
            telemetry.update();


                /*robot.armMotor.setTargetPosition(-90);
                robot.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.armMotor.setPower(1);
                robot.armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                */





           /*if (robot.armMotor.getCurrentPosition()>= -100) {
               telemetry.addData("hello", "hello");

               if(gamepad2.dpad_up){
                   robot.armMotor.setPower(-0.55);
               }
               else{
                   robot.armMotor.setPower(0);
               }
           }*/


        }
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
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
