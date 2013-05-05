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
    private static final String ABOUT_SETTINGS = "about_settings";	
	private static final String SYSTEMUI_TWEAKS = "systemui_tweaks";

    PreferenceScreen mLockscreenSettings;
    PreferenceScreen mLockscreenStyles;
    PreferenceScreen mLockscreenWeather;
	PreferenceScreen mSystemUITweaks;
	PreferenceScreen mAbout;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.space_settings);

        PreferenceScreen prefs = getPreferenceScreen();
        
		mAbout = (PreferenceScreen) findPreference(ABOUT_SETTINGS);
		mSystemUITweaks = (PreferenceScreen) findPreference(SYSTEMUI_TWEAKS);
        mLockscreenSettings = (PreferenceScreen) findPreference(LOCKSCREEN_SETTINGS);
        mLockscreenStyles = (PreferenceScreen) findPreference(LOCKSCREEN_STYLES);
        mLockscreenWeather = (PreferenceScreen) findPreference(LOCKSCREEN_WEATHER);
    }
}
