
//AH Studios
//ActiveHouseV2 Project

package io.github.activehouse;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;


/**
 * Created by Ryan Antolin on 2017-12-12
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
    //TODO: Implement SettingsFragment once xml is completed.

}
