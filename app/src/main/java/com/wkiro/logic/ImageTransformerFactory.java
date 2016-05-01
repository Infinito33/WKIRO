package com.wkiro.logic;

import com.wkiro.logic.transformStrategies.basicFilters.NegativeStrategy;
import com.wkiro.logic.transformStrategies.NoActionStrategy;
import com.wkiro.logic.transformStrategies.SepiaStrategy;
import com.wkiro.logic.transformStrategies.converFilters.BrightenImageStrategy;
import com.wkiro.logic.transformStrategies.converFilters.SharperImageStrategy;
import com.wkiro.logic.transformStrategies.eTransformStrategy;
import com.wkiro.logic.transformStrategies.edgeDetection.CannyEdgeDetectionStrategy;
import com.wkiro.logic.transformStrategies.morphologyFilters.DilateStrategy;
import com.wkiro.logic.transformStrategies.morphologyFilters.ErodeStrategy;
import com.wkiro.logic.transformStrategies.noiseFilters.GaussianNoiseStrategy;
import com.wkiro.logic.transformStrategies.smoothFilters.BilateralBlurStrategy;
import com.wkiro.logic.transformStrategies.smoothFilters.GaussianBlurStrategy;
import com.wkiro.logic.transformStrategies.smoothFilters.HomogeneousBlurStrategy;
import com.wkiro.logic.transformStrategies.smoothFilters.MedianBlurStrategy;

/**
 * Class ImageTransformerFactory creates image transformation strategies defined in eTransformStrategy
 * enum. Enables creating objects according to passed enum variable as well as through factory methods.
 * <p/>
 * Created by Wiktor on 2016-04-04.
 */
public class ImageTransformerFactory {

    public static ImageTransformer CreateStrategyFromEnum(eTransformStrategy strategy) {
        switch (strategy) {
            case NoAction:
                return CreateNoActionTransformer();
            case Negative:
                return CreateNegativeTransformer();
            case Sepia:
                return CreateSepiaTransformer();
            case HomogeneousBlur:
                return CreateHomogeneousBlurTransformer();
            case BilateralBlur:
                return CreateBilaterialBlurTransformer();
            case GaussianBlur:
                return CreateGaussianBlurTransformer();
            case MedianBlur:
                return CreateMedianBlurTransformer();
/*            case GaussianNoise:
                return CreateGaussianNoiseTransformer();*/
            case BrightenImage:
                return CreateBrightenImageTransformer();
            case SharperImage:
                return CreateSharperImageTransformer();
            case Dilate:
                return CreateDilateTransformer();
            case Erode:
                return CreateErodeTransformer();
            case CannyEdge:
                return CreateCannyEdgeDetectionTransformer();
            default:
                return CreateNoActionTransformer();
        }
    }

    private static ImageTransformer CreateSepiaTransformer() {
        return new ImageTransformer(new SepiaStrategy());
    }

    private static ImageTransformer CreateNegativeTransformer() {
        return new ImageTransformer(new NegativeStrategy());
    }

    public static ImageTransformer CreateNoActionTransformer() {
        return new ImageTransformer(new NoActionStrategy());
    }

    public static ImageTransformer CreateBilaterialBlurTransformer() {
        return new ImageTransformer(new BilateralBlurStrategy());
    }

    public static ImageTransformer CreateGaussianBlurTransformer() {
        return new ImageTransformer(new GaussianBlurStrategy());
    }

    public static ImageTransformer CreateHomogeneousBlurTransformer() {
        return new ImageTransformer(new HomogeneousBlurStrategy());
    }

    public static ImageTransformer CreateMedianBlurTransformer() {
        return new ImageTransformer(new MedianBlurStrategy());
    }

    public static ImageTransformer CreateGaussianNoiseTransformer() {
        return new ImageTransformer(new GaussianNoiseStrategy());
    }

    public static ImageTransformer CreateBrightenImageTransformer() {
        return new ImageTransformer(new BrightenImageStrategy());
    }

    public static ImageTransformer CreateSharperImageTransformer() {
        return new ImageTransformer(new SharperImageStrategy());
    }

    public static ImageTransformer CreateDilateTransformer() {
        return new ImageTransformer(new DilateStrategy());
    }

    public static ImageTransformer CreateErodeTransformer() {
        return new ImageTransformer(new ErodeStrategy());
    }

    public static ImageTransformer CreateCannyEdgeDetectionTransformer() {
        return new ImageTransformer(new CannyEdgeDetectionStrategy());
    }

}
