
//AH Studios
//ActiveHouseV2 Project

package io.github.activehouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/*
*   Created by Ryan Antolin on 2017-11-12
*
 */
public class SettingsActivity extends AppCompatActivity {
    /* private boolean isLight;
    private boolean isChecked;
    private int currentTheme;
    private int oldTheme; */
    public static final String KEY_PREF_EXAMPLE_SWITCH = "example_switch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
        //TODO: Figure out how I will implement the logic for the settings that will be featured.
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        /* isChecked = sharedPref.getBoolean("caps_pref", false);
        String lister = sharedPref.getString("list_preference", "0");
        oldTheme = Integer.parseInt(lister);
        toggleTheme(); */


    }

    private void toggleTheme() {
        /* SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        isChecked = sharedPref.getBoolean("caps_pref", false);
        String lister = sharedPref.getString("list_preference", "1");
        String myName = sharedPref.getString("edittext_preference", "0");
        currentTheme = Integer.parseInt(lister);
        if (currentTheme == 2) {
            isLight = false;
        } else {
            isLight = true;
        }

        if (isLight) {
            setTheme(R.style.AppTheme_redwhite);
        } else {
            setTheme(R.style.AppTheme);
        }
        if(oldTheme != currentTheme) {

            oldTheme = currentTheme;

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } */
    }
    //setContentView(R.layout.activity_settings);
}

