package com.evo.common.view;

import android.content.Context;
import android.view.View;
import android.view.Window;
import androidx.annotation.Nullable;

/**
 * Created by Dylan Roussel on 17/09/2019
 */
public class StatusBar {

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
