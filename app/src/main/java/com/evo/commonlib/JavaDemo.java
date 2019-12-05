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

package com.evo.commonlib;

import android.content.Context;
import android.view.Window;
import com.evo.common.view.DimensUtils;
import com.evo.common.view.NavigationBar;
import com.evo.common.view.StatusBar;

/**
 * Created by Dylan Roussel on 14/10/2019
 */
public class JavaDemo {

    public static String getToastText(Context context, Window window) {
        float sBarSize = StatusBar.getHeight(context, null);
        float nBarSize = NavigationBar.getHeight(context, null);
        float sBarSizePx = DimensUtils.dp2px(context, StatusBar.getHeight(context, window));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("StatusBar = ").append(sBarSize).append("dp");
        stringBuilder.append("\nNavigationBar = ").append(nBarSize).append("dp");
        stringBuilder.append("\nStatusBar in Px = ").append(sBarSizePx).append("px");

        return stringBuilder.toString();
    }

}
