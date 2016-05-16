package com.wkiro.logic.transformStrategies.edgeDetection;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Created by tomte on 16.05.2016.
 */
public class LaplacianEdgeDetectionStrategy implements ITransformStrategy {

    private Mat grayScaleImage = new  Mat(), result = new Mat();
    private int kernelSize = 3, scale = 1, delta = 0, ddepth = CvType.CV_16S;

    @Override
    public Mat performTransformation(Mat image) {

        Imgproc.GaussianBlur(image, image, new Size(3, 3), Core.BORDER_DEFAULT);

        Imgproc.cvtColor(image, grayScaleImage, Imgproc.COLOR_BGR2GRAY);

        Imgproc.Laplacian(grayScaleImage, result, ddepth, kernelSize, scale, delta, Core.BORDER_DEFAULT);

        Mat abs_result = new Mat();
        Core.convertScaleAbs( result, abs_result );

        return abs_result;
    }
}
