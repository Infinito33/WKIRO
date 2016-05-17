package com.wkiro.logic.transformStrategies.basicFilters;

import com.wkiro.logic.ITransformStrategy;
import com.wkiro.utils.TransformConfig;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import java.lang.Math;

/**
 * Created by tomte on 09.05.2016.
 */
public class GammaCorrectionStrategy implements ITransformStrategy {

    private Mat result;
    Mat lookupTable;
    private double gamma;

    public GammaCorrectionStrategy()
    {
        TransformConfig config = TransformConfig.GetInstance();
        gamma = config.GammaValue;
    }

    @Override
    public Mat performTransformation(Mat image) {
        if (result == null) {
            result = new Mat(image.rows(), image.cols(), image.type());
        }

        if (lookupTable == null)
        {
            lookupTable = new Mat(256, 1, image.type());
            for (int i = 0; i < 256; i++) {
                double value = 255  * Math.pow(i / 255.0, 1 / gamma);
                lookupTable.put(i, 0, value, value, value, i);
            }
        }


        Core.LUT(image, lookupTable, result);
        return result;
    }
}
