package com.android.settings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

import java.util.List;

public class SpaceSettings extends SettingsPreferenceFragment {
	
    private static final String LOCKSCREEN_SETTINGS = "lockscreen_settings";
    private static final String LOCKSCREEN_STYLES = "lockscreen_styles";
    private static final String LOCKSCREEN_WEATHER = "lockscreen_weather";	

    PreferenceScreen mLockscreenSettings;
    PreferenceScreen mLockscreenStyles;
    PreferenceScreen mLockscreenWeather;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.space_settings);

        PreferenceScreen prefs = getPreferenceScreen();
        
        mLockscreenSettings = (PreferenceScreen) findPreference(LOCKSCREEN_SETTINGS);
        mLockscreenStyles = (PreferenceScreen) findPreference(LOCKSCREEN_STYLES);
        mLockscreenWeather = (PreferenceScreen) findPreference(LOCKSCREEN_WEATHER);
        }
    }
}
