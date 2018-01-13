
//AH Studios
//ActiveHouseV2 Project

package io.github.activehouse;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/*
*   Created by Ryan Antolin on 2017-11-12
*
 */
public class SettingsActivity extends AppCompatActivity {

    public static final String KEY_PREF_EXAMPLE_SWITCH = "example_switch";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
        //TODO: Figure out how I will implement the logic for the settings that will be featured.
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);



        /* This switches to false and also test to see if it switches to all false
        Boolean switchPref = sharedPref.getBoolean
                (SettingsActivity.KEY_PREF_EXAMPLE_SWITCH, false);
        Toast.makeText(this, switchPref.toString(), Toast.LENGTH_SHORT).show();
        */


        //setContentView(R.layout.activity_settings);
    }
}
