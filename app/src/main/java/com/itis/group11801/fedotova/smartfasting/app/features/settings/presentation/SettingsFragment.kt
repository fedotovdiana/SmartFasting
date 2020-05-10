package com.itis.group11801.fedotova.smartfasting.app.features.settings.presentation

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.itis.group11801.fedotova.smartfasting.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preference, rootKey)
    }
}
