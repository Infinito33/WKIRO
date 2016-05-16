package com.wkiro.logic.transformStrategies.noiseFilters;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Apply gaussian noise filter on image.
 * <p/>
 * Info source and examples of photos: http://eric-yuan.me/bilateral-filtering/
 * <p/>
 * Created by tomasz.huchro on 2016-04-14.
 */
public class GaussianNoiseStrategy implements ITransformStrategy {

    private double mean;
    private double stddev;

    //NIE DZIALA
    @Override
    public Mat performTransformation(Mat image) {
        //Mat noisedImage = new Mat();
        Mat grayImage = new Mat(image.size(), image.type());
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        //Core.randn(noisedImage, 0, 0.05);

        Mat noise = new Mat(grayImage.size(), CvType.CV_64F);
        Mat result = new Mat(grayImage.size(), CvType.CV_64F);

        Core.normalize(grayImage, result, 0.0, 1.0, Core.NORM_MINMAX, CvType.CV_64F);
        Core.randn(noise, 0, 0.05);

        Core.add(result, noise, result);
        Core.normalize(result, result, 0.0, 1.0, Core.NORM_MINMAX, CvType.CV_64F);

        result.convertTo(result, CvType.CV_8UC4);

        //Imgproc.cvtColor(result, result, Imgproc.COLOR_GRAY2BGRA, 4);
        return result;
    }
}
