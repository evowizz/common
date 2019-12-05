/*
 * Copyright 2019 Dylan Roussel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        return value * context.getResources().getDisplayMetrics().density;
    }

    /**
     * Converts a given value from px to dp (or dip)
     *
     * @param context The context to get resources
     * @param value Original value to be converted in px
     * @return value, but in dp
     */
    public static float px2dp(Context context, float value) {
        return value / context.getResources().getDisplayMetrics().density;
    }

}
