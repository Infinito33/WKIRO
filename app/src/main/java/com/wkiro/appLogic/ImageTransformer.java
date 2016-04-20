package com.wkiro.appLogic;

import org.opencv.core.Mat;

/**
 * Performs transformations on given image.
 *
 * Created by Wiktor on 2016-04-04.
 */
public class ImageTransformer {

    private ITransformStrategy _transformStrategy;

    public ImageTransformer(ITransformStrategy transformStrategy) {
        _transformStrategy = transformStrategy;
    }

    public Mat Transform(Mat image) {
        return _transformStrategy.performTransformation(image);
    }
}
