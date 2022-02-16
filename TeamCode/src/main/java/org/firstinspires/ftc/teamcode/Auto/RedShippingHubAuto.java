package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Projects.ProjectTank;

@TeleOp(name="RedShippingHubAuto")
public class RedShippingHubAuto extends LinearOpMode{
    public ProjectTank robot = new ProjectTank();

    @Override
    public void runOpMode() throws InterruptedException {
        //initialize hardware map
        robot.init(hardwareMap);
        
        /*
        turn 90 
        move forward
        turn left 90
        move forward to shipping hub
        lift arm
        release block
        arm down
        
        park??
        */
        
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
