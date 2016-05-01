package com.wkiro.logic.transformStrategies.edgeDetection;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Apply canny edge detection on image.
 * <p/>
 * Info source and examples of photos: http://www.tutorialspoint.com/java_dip/enhancing_image_sharpness.htm
 * <p/>
 * Created by tomasz.huchro on 2016-04-19.
 */
public class CannyEdgeDetectionStrategy implements ITransformStrategy {

    private int threshold;
    private int maxThreshold;

    private Mat destination;
    private Mat cannyImage;
    private Mat hierarchy;
    private Point point;
    private Size blurSize;
    private Scalar color;

    List<MatOfPoint> contours;

    public CannyEdgeDetectionStrategy() {
        this.threshold = 100;
        this.maxThreshold = 255;

        cannyImage = new Mat();
        contours = new ArrayList<>();
        hierarchy = new Mat();

        point = new Point(0, 0);
        blurSize = new Size(3, 3);

        color = new Scalar(0, 255, 68);
    }

    @Override
    public Mat performTransformation(Mat image) {
        if (destination == null) {
            destination = new Mat(image.rows(), image.cols(), image.type());
        }

        Imgproc.cvtColor(image, destination, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(destination, destination, blurSize, 0);

        Imgproc.Canny(destination, cannyImage, this.threshold * 2, this.maxThreshold);

        // CV_RETR_TREE == 3
        Imgproc.findContours(cannyImage, contours, hierarchy, 3, Imgproc.CHAIN_APPROX_SIMPLE, point);

        for (int i = 0; i < contours.size(); i++) {
            Imgproc.drawContours(image, contours, i, color, 2);
        }

        contours.clear();

        return image;
    }
}
