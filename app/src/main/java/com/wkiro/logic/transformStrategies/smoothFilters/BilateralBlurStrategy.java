package com.wkiro.logic.transformStrategies.smoothFilters;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.CvType;
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

    private double kernel;
    private int diameter;

    public BilateralBlurStrategy() {
        this.kernel = 9.0;
        this.diameter = 9;
    }

    @Override
    public Mat performTransformation(Mat image) {
        Mat blurredImage = new Mat();

        Imgproc.cvtColor(image, image, Imgproc.COLOR_RGBA2RGB);
        Imgproc.bilateralFilter(image, blurredImage, 15, 80, 80);

        return blurredImage;
    }
}
