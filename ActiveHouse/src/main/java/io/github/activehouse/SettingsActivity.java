
//AH Studios
//ActiveHouseV2 Project

package io.github.activehouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/*
*   Created by Ryan Antolin on 2017-11-12
*
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();


        //setContentView(R.layout.activity_settings);
    }
}
