package com.wkiro.appLogic.transformStrategies.smoothFilters;

import com.wkiro.appLogic.ITransformStrategy;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Apply bilateral (blur) filter on image.
 * <p/>
 * Info source and examples of photos: http://eric-yuan.me/bilateral-filtering/
 * <p/>
 * Created by tomasz.huchro on 2016-04-14.
 */
public class BilateralBlurStrategy implements ITransformStrategy {

    private int kernel;

    public BilateralBlurStrategy() {
        this.kernel = 9;
    }

    @Override
    public Mat performTransformation(Mat image) {
        Mat blurredImage = new Mat();

        Imgproc.bilateralFilter(image, blurredImage, kernel, (double) (kernel * 2), (double) (kernel / 2));

        return blurredImage;
    }
}
