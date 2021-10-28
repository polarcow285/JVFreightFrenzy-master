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

            if(gamepad1.left_bumper == true){
                robot.armMotor.setPower(1);
            }
            else {
                robot.armMotor.setPower(0);
            }

            if(gamepad1.right_bumper == true){
                robot.armMotor.setPower(-1);
            }
            else{
                robot.armMotor.setPower(0);
            }

           // if (robot.armMotor.getCurrentPosition()>= 3000 || robot.armMotor.getCurrentPosition()<=0) {
               // robot.armMotor.setPower(0);
           // }

            if(gamepad1.a){
                if(clawPosition == 0) {
                    robot.clawServo.setPosition(1);
                }
                else {
                    robot.clawServo.setPosition(0);
                }
            }
        }
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
        robot.armMotor.setPower(0);

    }

    //Stop robot motors when game is finished
}