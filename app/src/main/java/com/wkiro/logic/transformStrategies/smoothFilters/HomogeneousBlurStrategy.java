package com.wkiro.logic.transformStrategies.smoothFilters;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Apply homogeneous (blur) filter on image.
 *
 * Info source and examples of photos: http://eric-yuan.me/bilateral-filtering/
 *
 * Created by tomasz.huchro on 2016-04-14.
 */
public class HomogeneousBlurStrategy implements ITransformStrategy {

    private double kernel;

    public HomogeneousBlurStrategy() {
        this.kernel = 9.0;
    }

    @Override
    public Mat performTransformation(Mat image) {
        Mat blurredImage = new Mat();

        Imgproc.blur(image, blurredImage, new Size(kernel, kernel));

        return blurredImage;
    }
}