package com.evo.common.view;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Dylan Roussel on 17/09/2019
 */
public class DimensUtils {

    /**
     * Converts a given value from dp (or dip) to px
     *
     * @param context The context to get resources
     * @param value Original value to be converted in dp
     * @return value, but in Pixel
     */
    public static float dp2px(Context context, float value) {
        return value * ((float) context.getResources()
            .getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * Converts a given value from px to dp (or dip)
     *
     * @param context The context to get resources
     * @param value Original value to be converted in px
     * @return value, but in dp
     */
    public static float px2dp(Context context, float value) {
        return value / ((float) context.getResources()
            .getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
