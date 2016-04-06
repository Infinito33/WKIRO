package com.wkiro.appLogic;

import com.wkiro.appLogic.transformStrategies.NegativeStrategy;
import com.wkiro.appLogic.transformStrategies.NoActionStrategy;
import com.wkiro.appLogic.transformStrategies.SepiaStrategy;
import com.wkiro.appLogic.transformStrategies.eTransformStrategy;

/**
 * Class ImageTransformerFactory creates image transformation strategies defined in eTransformStrategy
 * enum. Enables creating objects according to passed enum variable as well as through factory methods.
 *
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

}
