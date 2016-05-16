package com.wkiro.logic.transformStrategies.converFilters;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by tomte on 09.05.2016.
 */
public class ThresholdingStrategy implements ITransformStrategy {

    private Mat result;
    private double threshold = 100;
    private double maxValue = 255;

    @Override
    public Mat performTransformation(Mat image) {
        result = new Mat(image.rows(), image.cols(), image.type());
        Imgproc.threshold(image, result, threshold, maxValue, Imgproc.THRESH_TOZERO);

        return result;
    }
}
