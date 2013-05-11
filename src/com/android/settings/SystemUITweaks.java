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
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.text.Spannable;
import android.widget.EditText;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;
import com.android.settings.util.colorpicker.ColorPickerPreference;

public class SystemUITweaks extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {
	
    private static final String PREF_RECENT_APP_SWITCHER ="recent_app_switcher";
    private static final String HIDE_ALARM = "hide_alarm";
    private static final String PREF_CLOCK_DISPLAY_STYLE = "clock_am_pm";
    private static final String PREF_CLOCK_STYLE = "clock_style";
    private static final String CLOCK_COLOR = "clock_color";

    private ListPreference mRecentAppSwitcher;
    private CheckBoxPreference mHideAlarm;
    private ListPreference mAmPmStyle;
    private ListPreference mClockStyle;
    private ColorPickerPreference mClockColor;
	
	private PreferenceCategory mCategoryClock;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.recents_tweaks);
  
        PreferenceScreen prefSet = getPreferenceScreen();
        
        mCategoryClock = (PreferenceCategory) prefSet.findPreference("clock_category");	 
	    
        mHideAlarm = (CheckBoxPreference) prefSet.findPreference(HIDE_ALARM);
        mHideAlarm.setChecked(Settings.System.getInt(getContentResolver(),
                Settings.System.HIDE_ALARM, 0) == 1);
 			
        mClockColor = (ColorPickerPreference) prefSet.findPreference(CLOCK_COLOR);
        mClockColor.setOnPreferenceChangeListener(this);
	 	
        mClockStyle = (ListPreference) prefSet.findPreference(PREF_CLOCK_STYLE);
        mAmPmStyle = (ListPreference) prefSet.findPreference(PREF_CLOCK_DISPLAY_STYLE);

        int styleValue = Settings.System.getInt(getContentResolver(),
                Settings.System.STATUS_BAR_AM_PM, 2);
        mAmPmStyle.setValueIndex(styleValue);
        mAmPmStyle.setOnPreferenceChangeListener(this);
	    
        int clockVal = Settings.System.getInt(getContentResolver(),
                Settings.System.STATUS_BAR_CLOCK, 1);
        mClockStyle.setValueIndex(clockVal);
        mClockStyle.setOnPreferenceChangeListener(this);	   
	    
        mRecentAppSwitcher = (ListPreference) findPreference(PREF_RECENT_APP_SWITCHER);
        mRecentAppSwitcher.setOnPreferenceChangeListener(this);
        mRecentAppSwitcher.setValue(Integer.toString(Settings.System.getInt(getActivity()
                .getContentResolver(), Settings.System.RECENT_APP_SWITCHER,
                0)));
    }  

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean value;
        if (preference == mHideAlarm) {
            value = mHideAlarm.isChecked();
            Settings.System.putInt(getContentResolver(),
                    Settings.System.HIDE_ALARM, value ? 1 : 0);
           return true;
       }
       return false;
    }
	
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mRecentAppSwitcher) {
 
            int val = Integer.parseInt((String) newValue);
            Settings.System.putInt(getActivity().getContentResolver(),
                Settings.System.RECENT_APP_SWITCHER, val);
            return true;
        } else if (preference == mAmPmStyle) {
            int statusBarAmPm = Integer.valueOf((String) newValue);
            Settings.System.putInt(getContentResolver(),
                    Settings.System.STATUS_BAR_AM_PM, statusBarAmPm);
            return true;
        } else if (preference == mClockStyle) {
            int val = Integer.valueOf((String) newValue);
            Settings.System.putInt(getContentResolver(),
                    Settings.System.STATUS_BAR_CLOCK, val);
            return true;
        } else if (preference == mClockColor) {
            String hexColor = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                    .valueOf(newValue)));
            preference.setSummary(hexColor);
            int color = ColorPickerPreference.convertToColorInt(hexColor);
            Settings.System.putInt(getContentResolver(),
                    Settings.System.CLOCK_COLOR, color);
            return true;
        }        
        return false;
     }
}
