package com.wkiro.logic.transformStrategies;

/**
 * Represents image trasformation strategies available in the system.
 *
 * Created by Wiktor on 2016-04-04.
 */
public enum eTransformStrategy {
    NoAction,
    Negative,
    Sepia,
    BilateralBlur,
    GaussianBlur,
    HomogeneousBlur,
    MedianBlur,
/*    GaussianNoise,*/
    BrightenImage,
    SharperImage,
    Dilate,
    Erode,
    CannyEdge
}
