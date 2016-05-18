package com.wkiro.logic.transformStrategies;

/**
 * Represents image trasformation strategies available in the system.
 *
 * Created by Wiktor on 2016-04-04.
 */
public enum eTransformStrategy {
    NoAction,
    Negative,
    AddValue,
    SubtractValue,
    MultiplyValue,
    DivideValue,
    GammaCorrection,
    Thresholding,
    /*Sepia,
    BilateralBlur,*/
    GaussianBlur,
    /*HomogeneousBlur,*/
    MedianBlur,
    SaltAndPepperNoise,
    /*BrightenImage,
    SharperImage,*/
    Dilate,
    Erode,
    Opening,
    Closing,
    CannyEdge,
    SobelEdge,
    LaplacianEdge
}
