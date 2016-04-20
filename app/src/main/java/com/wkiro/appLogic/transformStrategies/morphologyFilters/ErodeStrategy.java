package com.wkiro.appLogic.transformStrategies.morphologyFilters;

import com.wkiro.appLogic.ITransformStrategy;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Apply erode operation on image.
 * <p/>
 * Info source and examples of photos: http://www.tutorialspoint.com/java_dip/eroding_dilating.htm
 * <p/>
 * Created by tomasz.huchro on 2016-04-19.
 */
public class ErodeStrategy implements ITransformStrategy {

    private int erosionSize;
    private Mat element;
    private Size ksize;
    private Mat destination;

    public ErodeStrategy() {
        this.erosionSize = 5;
        this.ksize = new Size(2 * erosionSize + 1, 2 * erosionSize + 1);
        this.element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, ksize);
    }

    @Override
    public Mat performTransformation(Mat image) {
        if(destination == null) {
            destination = new Mat(image.rows(), image.cols(), image.type());
        }

        Imgproc.erode(image, destination, element);

        return destination;
    }
}
