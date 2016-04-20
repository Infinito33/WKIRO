package com.wkiro.appLogic.transformStrategies.morphologyFilters;

import com.wkiro.appLogic.ITransformStrategy;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Apply dilation operation on image.
 * <p/>
 * Info source and examples of photos: http://www.tutorialspoint.com/java_dip/eroding_dilating.htm
 * <p/>
 * Created by tomasz.huchro on 2016-04-19.
 */
public class DilateStrategy implements ITransformStrategy {

    private int dilateSize;
    private Size ksize;
    private Mat element;

    private Mat destination;

    public DilateStrategy() {
        this.dilateSize = 5;
        this.ksize = new Size(2 * dilateSize + 1, 2 * dilateSize + 1);
        this.element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, ksize);
    }

    @Override
    public Mat performTransformation(Mat image) {
        if (destination == null) {
            destination = new Mat(image.rows(), image.cols(), image.type());
        }

        Imgproc.dilate(image, destination, element);

        return destination;
    }
}
