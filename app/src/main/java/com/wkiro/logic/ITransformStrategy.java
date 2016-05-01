package com.wkiro.logic;

import org.opencv.core.Mat;

/**
 * Gives access to one of available image transform strategies.
 *
 * Created by Wiktor on 2016-04-04.
 */
public interface ITransformStrategy {

    Mat performTransformation(Mat image);

}
