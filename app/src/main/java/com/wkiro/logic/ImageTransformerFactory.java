package com.wkiro.logic;

import com.wkiro.logic.transformStrategies.basicFilters.AddValueStrategy;
import com.wkiro.logic.transformStrategies.basicFilters.DivideValueStrategy;
import com.wkiro.logic.transformStrategies.basicFilters.GammaCorrectionStrategy;
import com.wkiro.logic.transformStrategies.basicFilters.MultiplyValueStrategy;
import com.wkiro.logic.transformStrategies.basicFilters.NegativeStrategy;
import com.wkiro.logic.transformStrategies.NoActionStrategy;
import com.wkiro.logic.transformStrategies.SepiaStrategy;
import com.wkiro.logic.transformStrategies.basicFilters.SubtractValueStrategy;
import com.wkiro.logic.transformStrategies.converFilters.BrightenImageStrategy;
import com.wkiro.logic.transformStrategies.converFilters.SharperImageStrategy;
import com.wkiro.logic.transformStrategies.converFilters.ThresholdingStrategy;
import com.wkiro.logic.transformStrategies.eTransformStrategy;
import com.wkiro.logic.transformStrategies.edgeDetection.CannyEdgeDetectionStrategy;
import com.wkiro.logic.transformStrategies.edgeDetection.LaplacianEdgeDetectionStrategy;
import com.wkiro.logic.transformStrategies.edgeDetection.SobelEdgeDetectionStrategy;
import com.wkiro.logic.transformStrategies.morphologyFilters.ClosingStrategy;
import com.wkiro.logic.transformStrategies.morphologyFilters.DilateStrategy;
import com.wkiro.logic.transformStrategies.morphologyFilters.ErodeStrategy;
import com.wkiro.logic.transformStrategies.morphologyFilters.OpeningStrategy;
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
            /*case Sepia:
                return CreateSepiaTransformer();*/
            case AddValue:
                return CreateAddValueTransformer();
            case SubtractValue:
                return CreateSubtractValueTransformer();
            case DivideValue:
                return CreateDivideValueTransformer();
            case MultiplyValue:
                return CreateMultiplyValueTransformer();
            case GammaCorrection:
                return CreateGammaCorrectionTransformer();
            case Thresholding:
                return CreateThresholdingTransformer();
            /*case HomogeneousBlur:
                return CreateHomogeneousBlurTransformer();
            case BilateralBlur:
                return CreateBilaterialBlurTransformer();*/
            case GaussianBlur:
                return CreateGaussianBlurTransformer();
            case MedianBlur:
                return CreateMedianBlurTransformer();
           case GaussianNoise:
                return CreateGaussianNoiseTransformer();
            /*case BrightenImage:
                return CreateBrightenImageTransformer();
            case SharperImage:
                return CreateSharperImageTransformer();*/
            case Dilate:
                return CreateDilateTransformer();
            case Erode:
                return CreateErodeTransformer();
            case Opening:
                return CreateOpeningTransformer();
            case Closing:
                return CreateClosingTransformer();
            case CannyEdge:
                return CreateCannyEdgeDetectionTransformer();
            case SobelEdge:
                return CreateSobelEdgeDetectionTransformer();
            case LaplacianEdge:
                return CreateLaplacianEdgeDetectionTransformer();
            default:
                return CreateNoActionTransformer();
        }
    }

    private static ImageTransformer CreateLaplacianEdgeDetectionTransformer() {
        return new ImageTransformer(new LaplacianEdgeDetectionStrategy());
    }

    private static ImageTransformer CreateSobelEdgeDetectionTransformer() {
        return new ImageTransformer(new SobelEdgeDetectionStrategy());
    }

    private static ImageTransformer CreateThresholdingTransformer() {
        return new ImageTransformer(new ThresholdingStrategy());
    }

    private static ImageTransformer CreateClosingTransformer() {
        return new ImageTransformer(new ClosingStrategy());
    }

    private static ImageTransformer CreateOpeningTransformer() {
        return new ImageTransformer(new OpeningStrategy());
    }

    private static ImageTransformer CreateGammaCorrectionTransformer() {
        return new ImageTransformer(new GammaCorrectionStrategy());
    }

    private static ImageTransformer CreateDivideValueTransformer() {
        return new ImageTransformer(new DivideValueStrategy());
    }

    public static ImageTransformer CreateMultiplyValueTransformer() {
        return new ImageTransformer(new MultiplyValueStrategy());
    }

    public static ImageTransformer CreateSubtractValueTransformer() {
        return new ImageTransformer(new SubtractValueStrategy());
    }

    public static ImageTransformer CreateAddValueTransformer() {
        return new ImageTransformer(new AddValueStrategy());
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
