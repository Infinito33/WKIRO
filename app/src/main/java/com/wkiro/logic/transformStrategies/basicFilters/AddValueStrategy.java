package com.wkiro.logic.transformStrategies.basicFilters;

import com.wkiro.logic.ITransformStrategy;
import com.wkiro.utils.TransformConfig;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

/**
 * Created by tomte on 03.05.2016.
 */
public class AddValueStrategy implements ITransformStrategy {

    private Mat lookupTable, result;
    private int valueToAdd;

    public AddValueStrategy()
    {
        TransformConfig config = TransformConfig.GetInstance();
        valueToAdd = config.AddSubValue;
    }

    @Override
    public Mat performTransformation(Mat image) {
        if(lookupTable == null) {
            lookupTable = new Mat(256, 1, image.type());
            for (int i = 0; i < 256; i++) {
                double value = i + valueToAdd;
                lookupTable.put(i, 0, value, value, value, i);
            }
        }
        if(result == null) {
            result = new Mat(image.rows(), image.cols(), image.type());
        }

        Core.LUT(image, lookupTable, result);

        return result;
    }
}
