package com.itis.group11801.fedotova.smartfasting.feature_settings.presentation

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.itis.group11801.fedotova.smartfasting.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preference, rootKey)
//        val switch_gr: SwitchPreferenceCompat? = findPreference("preference_gr")
//
//        switch_gr?.setOnPreferenceChangeListener { _, newValue ->
//            if (newValue == true) {
//                Toast.makeText(activity, "enabled", Toast.LENGTH_LONG).show()
//            } else {
//                Toast.makeText(activity, "disabled", Toast.LENGTH_LONG).show()
//            }
//
//            true
//        }
    }

}
