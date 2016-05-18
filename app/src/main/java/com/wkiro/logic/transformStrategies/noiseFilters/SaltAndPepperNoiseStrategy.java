package com.wkiro.logic.transformStrategies.noiseFilters;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.Random;

/**
 * Apply gaussian noise filter on image.
 * <p/>
 * Info source and examples of photos: http://eric-yuan.me/bilateral-filtering/
 * <p/>
 * Created by tomasz.huchro on 2016-04-14.
 */
public class SaltAndPepperNoiseStrategy implements ITransformStrategy {
    
    @Override
    public Mat performTransformation(Mat image) {
        Random generator = new Random();

        int numberOfPixels = image.rows()* image.cols();
        int numberOfNoisedPixels = numberOfPixels / 100;

        for(int k=0; k<numberOfNoisedPixels; k++)
        {
            int i = generator.nextInt(image.cols());
            int j = generator.nextInt(image.rows());
                image.put(j, i, 255, 255, 255, 255);
        }
        for(int k=0; k<numberOfNoisedPixels; k++)
        {
            int i = generator.nextInt(image.cols());
            int j = generator.nextInt(image.rows());
            image.put(j, i, 0, 0, 0, 255);
        }
        return image;
    }
}
