
package com.android.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

import com.android.settings.R;

public class About extends SettingsPreferenceFragment {
	
	Preference mSpaceSite;
	Preference mSpaceSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.about_settings);
        
        mSpaceSite = findPreference("space_site");
        mSpaceSource = findPreference("space_source");
        
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mSpaceSite) {
            gotoUrl("http://forum.xda-developers.com/member.php?u=3862405");
        } else if (preference == mSpaceSource) {
        	gotoUrl("http://github.com/CyanoSpaceMod");
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
    
    private void gotoUrl(String uri) {
    	Uri page = Uri.parse(uri);
        Intent i = new Intent(Intent.ACTION_VIEW, page);
        getActivity().startActivity(i);
    }
}