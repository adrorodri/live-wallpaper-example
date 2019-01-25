package com.adrorodri.livewallpapertest.fragments;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import com.adrorodri.livewallpapertest.R;

public class PrefsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.prefs);
        Preference circlePreference = getPreferenceScreen().findPreference("numberOfCircles");
        circlePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue != null && newValue.toString().length() > 0
                        && newValue.toString().matches("\\d*")) {
                    return true;
                }
                Toast.makeText(getContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
