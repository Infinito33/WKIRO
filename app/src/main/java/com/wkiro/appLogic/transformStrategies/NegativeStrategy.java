package com.wkiro.appLogic.transformStrategies;

import com.wkiro.appLogic.ITransformStrategy;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

/**
 * Converts input into a negative image.
 *
 * Created by Wiktor on 2016-04-04.
 */
public class NegativeStrategy implements ITransformStrategy {
    @Override
    public Mat PerformTransformation(Mat image) {

        Mat matToSubstractFrom = new Mat(image.rows(),image.cols(), image.type(), new Scalar(255,255,255));

        Core.subtract(matToSubstractFrom, image, image);

        return image;
    }
}
