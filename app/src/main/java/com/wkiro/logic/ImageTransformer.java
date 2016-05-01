package com.wkiro.logic;

import org.opencv.core.Mat;

/**
 * Performs transformations on given image.
 *
 * Created by Wiktor on 2016-04-04.
 */
public class ImageTransformer {

    private ITransformStrategy transformStrategy;

    public ImageTransformer(ITransformStrategy transformStrategy) {
        this.transformStrategy = transformStrategy;
    }

    public Mat transform(Mat image) {
        return transformStrategy.performTransformation(image);
    }
}
