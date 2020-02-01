/*
 * Copyright 2020 Dylan Roussel
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

package com.evo.common.view

import android.content.Context

/**
 * Created by Dylan Roussel on 14/10/2019
 */

/**
 * Converts a given value from dp (or dip) to px
 *
 * @param context The context to get resources
 * @param value Original value to be converted in dp
 * @return value, but in Pixel
 */
fun Float.toPx(context: Context): Float = this * context.resources.displayMetrics.density

/**
 * Converts a given value from px to dp (or dip)
 *
 * @param context The context to get resources
 * @return value, but in dp
 */
fun Float.toDp(context: Context): Float = this / context.resources.displayMetrics.density