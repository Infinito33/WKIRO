package com.wkiro.logic.transformStrategies.smoothFilters;

import com.wkiro.logic.ITransformStrategy;
import com.wkiro.utils.TransformConfig;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Apply gaussian blur on image.
 * <p/>
 * Info source and examples of photos: http://eric-yuan.me/bilateral-filtering/
 * <p/>
 * Created by tomasz.huchro on 2016-04-14.
 */
public class GaussianBlurStrategy implements ITransformStrategy {

    private double kernel;

    public GaussianBlurStrategy() {
        TransformConfig config = TransformConfig.GetInstance();
        this.kernel = config.BlurKernelSize;
    }

    @Override
    public Mat performTransformation(Mat image) {
        Mat blurredImage = new Mat();

        Imgproc.GaussianBlur(image, blurredImage, new Size(kernel, kernel), 0, 0);

        return blurredImage;
    }
}
