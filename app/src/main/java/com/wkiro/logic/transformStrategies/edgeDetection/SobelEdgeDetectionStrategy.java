package com.wkiro.logic.transformStrategies.edgeDetection;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomte on 09.05.2016.
 */
public class SobelEdgeDetectionStrategy implements ITransformStrategy {

    private Mat grayScaleImage;
    private Mat hierarchy;
    private Size blurSize = new Size(3, 3);


    private List<MatOfPoint> contours;

    @Override
    public Mat performTransformation(Mat image) {

        grayScaleImage = new Mat(image.rows(), image.cols(), image.type());
        Imgproc.GaussianBlur(image, image, blurSize, 0, 0, Core.BORDER_DEFAULT );
        Imgproc.cvtColor(image, grayScaleImage, Imgproc.COLOR_BGR2GRAY);

        Mat grad = new Mat();
        Mat grad_x = new Mat(), grad_y = new Mat();
        Mat abs_grad_x = new Mat(), abs_grad_y = new Mat();
        int dx = 1;
        int dy = 1;
        int scale = 1;
        int delta = 0;
        int ddepth = CvType.CV_16S;

        /// Gradient X
        Imgproc.Sobel( grayScaleImage, grad_x, ddepth, 1, 0, 3, scale, delta, Core.BORDER_DEFAULT );
        /// Gradient Y
        Imgproc.Sobel( grayScaleImage, grad_y, ddepth, 0, 1, 3, scale, delta, Core.BORDER_DEFAULT );

        Core.convertScaleAbs( grad_x, abs_grad_x );
        Core.convertScaleAbs( grad_y, abs_grad_y );

        Core.addWeighted( abs_grad_x, 0.5, abs_grad_y, 0.5, 0, grad );

        return grad;
    }
}
