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
        boolean yPressed = false;
        //wait for start button to be pressed
        waitForStart();

        //write teleop code here

        boolean halfPower = false;

        while(opModeIsActive()) {
            if(gamepad1.y){
                if(yPressed == false) {
                    speedMultiplier = -0.5f;
                    yPressed = true;
                }
                else{
                    speedMultiplier = -1f;
                    yPressed = false;
                }

            }
            robot.leftMotor.setPower(speedMultiplier * gamepad1.left_stick_y);
            robot.rightMotor.setPower(speedMultiplier * gamepad1.right_stick_y);
        }
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);

    }

    //Stop robot motors when game is finished
}
