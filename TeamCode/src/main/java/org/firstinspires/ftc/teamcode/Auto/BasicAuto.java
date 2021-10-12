package org.firstinspires.ftc.teamcode.Auto;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Projects.ProjectTank;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="TestMecanumAuto")
//"tag" that is displayed on driver hub
public class BasicAuto extends LinearOpMode{
    //creating robot object
    public ProjectTank robot = new ProjectTank();

    @Override
    public void runOpMode() throws InterruptedException {
        //initialize hardware map

        //wait for start button to be pressed
        waitForStart();

        //write autonomous code here
        commandBothMotors(1, 3);
        commandBothMotors(0, 2);
        commandBothMotors(-0.5, 8);

        robot.rightMotor.setPower(0);
        robot.leftMotor.setPower(0);



    }
    void commandBothMotors(int pwr, int time){
        robot.leftMotor.setPower(pwr);
        robot.rightMotor.setPower(pwr);
        sleep(milliseconds: time*1000);
    }
}
