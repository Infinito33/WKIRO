package com.wkiro.logic.transformStrategies.basicFilters;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

/**
 * Converts input into a negative image.
 *
 * Created by Wiktor on 2016-04-04.
 */
public class NegativeStrategy implements ITransformStrategy {

    Mat kernel;
    Mat result;

    @Override
    public Mat performTransformation(Mat image) {

        if(kernel == null) {
            kernel = new Mat(image.rows(),image.cols(), image.type(), new Scalar(255,255,255));
        }
        if(result == null) {
            result = new Mat(image.rows(), image.cols(), image.type());
        }

        Core.subtract(kernel, image, result);

        return result;
    }
}
