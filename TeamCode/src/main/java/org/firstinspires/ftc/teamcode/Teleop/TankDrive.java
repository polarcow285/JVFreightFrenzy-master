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

        //wait for start button to be pressed
        waitForStart();

        //write teleop code here


    }

    //Stop robot motors when game is finished
}
