package org.firstinspires.ftc.teamcode.Projects;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ProjectTank extends Project{
    //Project file for JV Robot
    //push test

    //Setup motors
    public DcMotor rightMotor = null;
    public DcMotor leftMotor = null;
    public DcMotor armMotor = null;
    public Servo clawServo = null;
    @Override
    public void init(HardwareMap ahwMap) {
        //Save reference to Hardware map
        hwMap = ahwMap;
        //Define and Initialize Motors
        rightMotor = hwMap.dcMotor.get("rightMotor");
        leftMotor = hwMap.dcMotor.get("leftMotor");
        armMotor = hwMap.dcMotor.get("armMotor");
        clawServo = hwMap.servo.get("clawServo");

        //Setup Motor directions and Encoder settings
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Set all motors to zero power

        Stop();
    }

    public void Stop(){
        rightMotor.setPower(0);
        leftMotor.setPower(0);

        //armMotor.setPower(0);
    }
}
