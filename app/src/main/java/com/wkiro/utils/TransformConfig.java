package com.wkiro.utils;

/**
 * Created by tomte on 17.05.2016.
 */
public class TransformConfig {

    private static TransformConfig transformConfigInstance;

    public int AddSubValue;
    public double MulDivValue;
    public double GammaValue;
    public int ThresholdValue;
    public int BlurKernelSize;
    public int MorphOperationSize;

    private TransformConfig()
    {
        AddSubValue = 10;
        MulDivValue = 1.8;
        GammaValue = 0.5;
        ThresholdValue = 100;
        BlurKernelSize = 5;
        MorphOperationSize = 5;
    }

    public static TransformConfig GetInstance()
    {
        if (transformConfigInstance == null)
        {
            transformConfigInstance = new TransformConfig();
        }
        return transformConfigInstance;
    }
}
