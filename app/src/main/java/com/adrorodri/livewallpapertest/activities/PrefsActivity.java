package com.adrorodri.livewallpapertest.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.adrorodri.livewallpapertest.fragments.PrefsFragment;

public class PrefsActivity extends AppCompatActivity {

    PrefsFragment prefsFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefsFragment = new PrefsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(prefsFragment, "PREFS_FRAGMENT").commitAllowingStateLoss();
    }
}
