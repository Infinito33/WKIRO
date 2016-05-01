package com.wkiro.logic.transformStrategies.noiseFilters;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * Apply gaussian noise filter on image.
 * <p/>
 * Info source and examples of photos: http://eric-yuan.me/bilateral-filtering/
 * <p/>
 * Created by tomasz.huchro on 2016-04-14.
 */
public class SaltAndPepperNoiseFilter implements ITransformStrategy {

    private double mean;
    private double stddev;

    private double low;
    private double high;

    public SaltAndPepperNoiseFilter() {
        this.mean = 128.0;
        this.stddev = 30.0;

        this.low = 0.0;
        this.high = 255.0;
    }

    @Override
    public Mat performTransformation(Mat image) {
        Mat noisedImage = Mat.zeros(image.rows(), image.cols(), CvType.CV_8U);

        Core.randu(noisedImage, this.low, this.high);

        //Mat black = saltpepper_noise < 30;
        //Mat white = saltpepper_noise > 225;

       // Mat saltpepper_img = img.clone();
        //saltpepper_img.setTo(255,white);
       // saltpepper_img.setTo(0, black);

        return noisedImage;
    }
}
