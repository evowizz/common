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
import android.view.View;
import android.view.Window;
import androidx.annotation.Nullable;

/**
 * Created by Dylan Roussel on 17/09/2019
 */
public class StatusBar {


    /**
     * Get the height in dp of the status bar.
     *
     * If window is null, the default size of the status bar will be returned using context.
     *
     * @param context The context to get resources
     * @param window The window of the current activity
     * @return The height in dp of the status bar
     */
    public static int getHeight(Context context, @Nullable Window window) {
        if (window != null) {
            View view = window.getDecorView().getRootView();
            if (view.getRootWindowInsets() != null) {
                return view.getRootWindowInsets().getSystemWindowInsetTop();
            }
        }

        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }

}
