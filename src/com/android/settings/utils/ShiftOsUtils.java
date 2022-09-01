/*
 * Copyright (C) 2022 SHIFT GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.utils;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;

import com.android.settings.R;

public class ShiftOsUtils {

    private static final String KEY_SHIFTOS_VERSION_EXTRA_PROP = "ro.shift.sos.version.extra";
    private static final String KEY_SHIFTOS_VERSION_DATE_PROP = "ro.shift.version.date";
    private static final String KEY_SHIFTOS_VERSION_DISPLAY = "ro.shift.version.display";
    private static final String KEY_SHIFTOS_VERSION_NUMBER_PROP = "ro.shift.sos.version.number";

    public static String getShiftOsDisplayVersion(final Context context) {
        final String displayVersion = SystemProperties.get(KEY_SHIFTOS_VERSION_DISPLAY, "");
        if (displayVersion.isEmpty()) {
            return context.getString(R.string.unknown);
        }
        return displayVersion;
    }

    public static String getShiftOsVersion(final Context context) {
        final String versionNumber = SystemProperties.get(KEY_SHIFTOS_VERSION_NUMBER_PROP, "");
        final String versionExtra = SystemProperties.get(KEY_SHIFTOS_VERSION_EXTRA_PROP, "");
        final String versionDate = SystemProperties.get(KEY_SHIFTOS_VERSION_DATE_PROP, "");
        final String summary;
        if (versionNumber.isEmpty() || versionExtra.isEmpty()) {
            summary = context.getString(R.string.unknown);
        } else {
            final String dateAndProduct;
            if (versionDate.isEmpty()) {
                dateAndProduct = String.format("(%s)", Build.PRODUCT);
            } else {
                dateAndProduct = String.format("(%s.%s)", versionDate, Build.PRODUCT);
            }

            summary = String.format("%s %s %s", versionNumber, versionExtra, dateAndProduct);
        }
        return summary;
    }
}
