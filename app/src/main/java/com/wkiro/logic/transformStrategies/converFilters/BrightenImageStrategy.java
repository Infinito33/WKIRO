package com.wkiro.logic.transformStrategies.converFilters;

import com.wkiro.logic.ITransformStrategy;

import org.opencv.core.Mat;

/**
 * Apply brightness enhancement filter on image.
 * <p/>
 * Info source and examples of photos: http://www.tutorialspoint.com/java_dip/enhancing_image_brightness.htm
 * <p/>
 * Created by tomasz.huchro on 2016-04-19.
 */
public class BrightenImageStrategy implements ITransformStrategy {

    private double alpha;
    private double beta;
    private Mat destination;

    public BrightenImageStrategy() {
        this.alpha = 1.0;
        this.beta = 50.0;
    }

    @Override
    public Mat performTransformation(Mat image) {
        if(destination == null) {
            destination = new Mat(image.rows(), image.cols(), image.type());
        }

        image.convertTo(destination, -1, alpha, beta);

        return destination;
    }
}
