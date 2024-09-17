/*
 * Copyright (C) 2024 SHIFT GmbH
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

package com.android.settings.development;

import android.content.Context;
import android.content.Intent;

import androidx.preference.Preference;

import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

public class ShiftSupportPreferenceController extends DeveloperOptionsPreferenceController
        implements PreferenceControllerMixin {

    private static final String SHIFT_SUPPORT_KEY = "shift_support";

    private final DevelopmentSettingsDashboardFragment mFragment;

    public ShiftSupportPreferenceController(Context context,
            DevelopmentSettingsDashboardFragment fragment) {
        super(context);
        mFragment = fragment;
    }

    @Override
    public String getPreferenceKey() {
        return SHIFT_SUPPORT_KEY;
    }

    @Override
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (SHIFT_SUPPORT_KEY.equals(preference.getKey())) {
            final Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClassName("eco.shift.support", "eco.shift.support.MainActivity");
            mFragment.startActivity(intent);
            return true;
        }
        return false;
    }
}
