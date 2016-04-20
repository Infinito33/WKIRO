package com.wkiro.appLogic.transformStrategies.basicFilters;

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

    Mat kernel;

    @Override
    public Mat performTransformation(Mat image) {

        if(kernel == null) {
            kernel = new Mat(image.rows(),image.cols(), image.type(), new Scalar(255,255,255));
        }

        Core.subtract(kernel, image, image);

        return image;
    }
}
