/*
 * Copyright (C) SHIFT GmbH
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
package com.android.settings.deviceinfo.legal

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import com.android.settings.R
import com.android.settingslib.metadata.PreferenceAvailabilityProvider
import com.android.settingslib.metadata.PreferenceMetadata
import com.android.settingslib.metadata.PreferenceTitleProvider

class ShiftOsLegalPreference(override val key: String, @StringRes val defaultTitle: Int = 0) :
    PreferenceMetadata, PreferenceTitleProvider, PreferenceAvailabilityProvider {

    override fun getTitle(context: Context): CharSequence? = context.getText(defaultTitle)

    override fun isAvailable(context: Context): Boolean {
        val url = context.getString(R.string.shiftos_license_url)
        if (url.isNullOrBlank()) return false

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        return intent.resolveActivity(context.packageManager) != null
    }

    override fun intent(context: Context): Intent? {
        val url = context.getString(R.string.shiftos_license_url)
        return if (url.isNotBlank()) {
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        } else null
    }
}
