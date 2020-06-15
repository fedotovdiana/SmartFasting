package com.itis.group11801.fedotova.smartfasting.app.features.settings

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.itis.group11801.fedotova.smartfasting.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preference, rootKey)

        val editPref: EditTextPreference? = findPreference(PREF_VOLUME)
        editPref?.setOnPreferenceChangeListener { _, newValue ->
            return@setOnPreferenceChangeListener validate(newValue)
        }
    }

    private fun validate(newValue: Any?): Boolean {
        try {
            if (newValue is String && newValue.toInt() in 500..5000) return true
        } catch (e: Exception) {
            return false
        }
        return false
    }

    companion object {
        const val PREF_VOLUME = "smartfasting.volume"
    }
}
