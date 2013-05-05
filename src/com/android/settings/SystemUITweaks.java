
package com.android.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;
import android.util.Log;
import android.text.Spannable;
import android.widget.EditText;

import com.android.settings.R;
import com.android.settings.util.SeekBarPreference;
import com.android.settings.util.Helpers;
import com.android.settings.util.colorpicker.ColorPickerPreference;

public class SystemUITweaks extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {
	
	private boolean isTablet;

    private static final String PREF_RECENT_APP_SWITCHER = "recent_app_switcher";

    private ListPreference mRecentAppSwitcher;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.recents_tweaks);
        PreferenceScreen prefSet = getPreferenceScreen();
        
        mRecentAppSwitcher = (ListPreference) findPreference(PREF_RECENT_APP_SWITCHER);
        mRecentAppSwitcher.setOnPreferenceChangeListener(this);
        mRecentAppSwitcher.setValue(Integer.toString(Settings.System.getInt(getActivity()
                .getContentResolver(), Settings.System.RECENT_APP_SWITCHER,
                0)));
        }
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mRecentAppSwitcher) {
		
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(getActivity().getContentResolver(),
                Settings.System.RECENT_APP_SWITCHER, val);
            Helpers.restartSystemUI();
            return true;
        }
        return false;
    }
