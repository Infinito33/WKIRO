package com.wkiro.appLogic;

import org.opencv.core.Mat;

/**
 * Gives access to one of available image transform strategies.
 *
 * Created by Wiktor on 2016-04-04.
 */
public interface ITransformStrategy {

    public Mat PerformTransformation(Mat image);

}
