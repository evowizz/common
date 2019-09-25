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
