package com.wkiro.logic.transformStrategies.converFilters;

import com.wkiro.logic.ITransformStrategy;
import com.wkiro.utils.TransformConfig;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by tomte on 09.05.2016.
 */
public class ThresholdingStrategy implements ITransformStrategy {

    private Mat result;
    private double threshold;
    private double maxValue = 255;

    public ThresholdingStrategy()
    {
        TransformConfig config = TransformConfig.GetInstance();
        threshold = config.ThresholdValue;
    }

    @Override
    public Mat performTransformation(Mat image) {
        result = new Mat(image.rows(), image.cols(), image.type());

        Mat grayScaleImage = new Mat();

        Imgproc.cvtColor(image, grayScaleImage, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(grayScaleImage, result, threshold, maxValue, Imgproc.THRESH_TOZERO);

        return result;
    }
}
