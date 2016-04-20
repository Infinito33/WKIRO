package com.wkiro.appLogic.transformStrategies;

import com.wkiro.appLogic.ITransformStrategy;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * Converts input into a sepia image.
 *
 * Created by Wiktor on 2016-04-04.
 */
public class SepiaStrategy implements ITransformStrategy {

    private Mat _sepiaKernel;

    public SepiaStrategy() {
        _sepiaKernel = new Mat(4, 4, CvType.CV_32F);
        _sepiaKernel.put(0, 0, /* R */0.189f, 0.769f, 0.393f, 0f);
        _sepiaKernel.put(1, 0, /* G */0.168f, 0.686f, 0.349f, 0f);
        _sepiaKernel.put(2, 0, /* B */0.131f, 0.534f, 0.272f, 0f);
        _sepiaKernel.put(3, 0, /* A */0.000f, 0.000f, 0.000f, 1f);
    }

    @Override
    public Mat performTransformation(Mat image) {
        //int top = image.height() / 8;
        //int left = image.width() / 8;
        //int height = image.height() * 3 / 4;
        //int width = image.width() * 3 / 4;
        //rgbaInnerWindow = image.submat(top, top + height, left, left + width);
        //Core.transform(rgbaInnerWindow, rgbaInnerWindow, mSepiaKernel);
        Core.transform(image, image, _sepiaKernel);
        return image;
    }
}
