package com.wkiro.logic.transformStrategies;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.Mat;

/**
 * Does not perform any transformations, leaves the image as is.
 *
 * Created by Wiktor on 2016-04-04.
 */
public class NoActionStrategy implements ITransformStrategy {

    @Override
    public Mat performTransformation(Mat image) {
        return image;
    }

}
