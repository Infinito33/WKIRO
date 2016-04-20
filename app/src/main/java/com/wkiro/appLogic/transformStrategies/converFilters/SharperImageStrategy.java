package com.wkiro.appLogic.transformStrategies.converFilters;

import com.wkiro.appLogic.ITransformStrategy;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Apply sharphness enhancement filter on image.
 * <p/>
 * Info source and examples of photos: http://www.tutorialspoint.com/java_dip/enhancing_image_sharpness.htm
 * <p/>
 * Created by tomasz.huchro on 2016-04-19.
 */
public class SharperImageStrategy implements ITransformStrategy {

    private double alpha;
    private double beta;
    private double gamma;

    private Mat destination;

    public SharperImageStrategy() {
        this.alpha = 1.5;
        this.beta = -0.5;
        this.gamma = 0.0;
    }

    @Override
    public Mat performTransformation(Mat image) {
        if(destination == null) {
            destination = new Mat(image.rows(), image.cols(), image.type());
        }

        Imgproc.GaussianBlur(image, destination, new Size(0, 0), 10);
        Core.addWeighted(image, this.alpha, destination, this.beta, this.gamma, destination);

        return destination;
    }
}
