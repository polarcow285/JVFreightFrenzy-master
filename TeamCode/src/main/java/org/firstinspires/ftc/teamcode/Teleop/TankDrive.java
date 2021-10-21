package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Projects.ProjectTank;

@TeleOp(name="TankDrive")
public class TankDrive extends LinearOpMode{
    public ProjectTank robot = new ProjectTank();

    @Override
    public void runOpMode() throws InterruptedException {
        //initialize hardware map
        robot.init(hardwareMap);

        float speedMultiplier = 1;

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
                robot.armMotor.setPower(1f);
            }
            else{
                robot.armMotor.setPower(0);
            }
            if(gamepad1.right_bumper == true){
                robot.armMotor.setPower(-1f);
            }
            else{
                robot.armMotor.setPower(0);
            }
        }
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);

    }

    //Stop robot motors when game is finished
}