package com.wkiro.logic.transformStrategies.morphologyFilters;

import com.wkiro.logic.ITransformStrategy;
import com.wkiro.logic.ImageTransformer;
import com.wkiro.logic.ImageTransformerFactory;

import org.opencv.core.Mat;

/**
 * Created by tomte on 09.05.2016.
 */
public class OpeningStrategy implements ITransformStrategy {

    private ImageTransformer erodeTransformer;
    private ImageTransformer dilateTransformer;

    public OpeningStrategy()
    {
        erodeTransformer = ImageTransformerFactory.CreateErodeTransformer();
        dilateTransformer = ImageTransformerFactory.CreateDilateTransformer();
    }

    @Override
    public Mat performTransformation(Mat image) {
        Mat erodedImage = erodeTransformer.transform(image);
        Mat result = dilateTransformer.transform(erodedImage);

        return result;
    }
}
