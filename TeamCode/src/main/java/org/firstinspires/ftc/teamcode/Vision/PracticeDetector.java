package org.firstinspires.ftc.teamcode.Vision;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PracticeDetector extends OpenCvPipeline{
    Mat mat = new Mat();
    //

        static final Rect rightROI = new Rect(
            new Point(180, 0),
            new Point(280, 180)
        );
        static final Rect middleROI = new Rect(
                new Point( 100, 0),
                new Point(180, 180)
        );
        static final Rect leftROI = new Rect(
                new Point( 0, 0),
                new Point(100, 180)
        );

        @Override
        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

            //define HSV range to identify the color yellow
            Scalar lowHSV = new Scalar (15, 100, 100);
            Scalar highHSV = new Scalar(30, 255, 255);

            //applies a threshold (everything that is yellow will be white, everything else will be black)
            //returns a new mat with this threshold
            Core.inRange(mat,lowHSV, highHSV, mat);

            Mat left = mat.submat(leftROI);
            Mat right = mat.submat(rightROI);
            Mat middle = mat.submat(middleROI);

            double leftPercentage = Core.sumElems(left).val[0] /*finds white area in left ROI*/ / leftROI.area() / 255;
            double rightPercentage = Core. sumElems(right).val[0] / rightROI.area() / 255;
            double middlePercentage = Core.sumElems(middle).val[0] / middleROI.area() / 255;

            left.release();
            right.release();
            middle.release();//takes in frame and deletes it after reading

            telemetry.addData("left percentage", Math.round(leftPercentage));
            telemetry.addData("right percentage", Math.round(rightPercentage));
            telemetry.addData("middle percentage", Math.round(middlePercentage));

            telemetry.update();

            return left;
        }
}
