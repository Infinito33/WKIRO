package com.wkiro.logic.transformStrategies.morphologyFilters;

import com.wkiro.logic.ITransformStrategy;
import com.wkiro.logic.ImageTransformer;
import com.wkiro.logic.ImageTransformerFactory;

import org.opencv.core.Mat;

/**
 * Created by tomte on 09.05.2016.
 */
public class ClosingStrategy implements ITransformStrategy {

    private ImageTransformer dilateTransformer;
    private ImageTransformer erodeTransformer;

    public ClosingStrategy()
    {
        dilateTransformer = ImageTransformerFactory.CreateDilateTransformer();
        erodeTransformer = ImageTransformerFactory.CreateErodeTransformer();
    }

    @Override
    public Mat performTransformation(Mat image) {
        Mat dilatedImage = dilateTransformer.transform(image);
        Mat result = erodeTransformer.transform(dilatedImage);

        return result;
    }
}
