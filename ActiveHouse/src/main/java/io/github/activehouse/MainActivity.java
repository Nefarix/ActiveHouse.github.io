//Active Applications
//Active House Project

package io.github.activehouse;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Process;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class MainActivity extends AppCompatActivity {

    EditText mEmailField;
    EditText mPasswordField;
    CheckBox cbSave;
    Button btnLogin;
    String username, password;


    private static final String TAG = "EmailPassword";

    public static String FirstName, LastName, Username, Email;
    public static int HouseID;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mEmailField = (EditText) findViewById(R.id.input_username);
        mPasswordField = (EditText) findViewById(R.id.input_password);
        cbSave = (CheckBox) findViewById(R.id.input_save);

        mAuth = FirebaseAuth.getInstance();

        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        String savedUsername = sharedPref.getString("mUsername", "");
        if (!savedUsername.equals("")) {
            mEmailField.setText(savedUsername);
            cbSave.setChecked(true);
        }



        btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mPasswordField.getText().length() > 0 && mEmailField.getText().length() > 0) {

                    //attempt login
                    username = mEmailField.getText().toString();
                    password = mPasswordField.getText().toString();
                    new GetLogin().execute();


                    if (cbSave.isChecked()) {
                        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("username", mEmailField.getText().toString());
                        editor.apply();


                    }
                    else {
                        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("username", "");
                        editor.apply();
                    }

                }
                else {
                    Toast.makeText(MainActivity.this, R.string.ERRORLOGGINGIN,Toast.LENGTH_SHORT).show();

                }

            }
        });

        TextView tvRegister = (TextView) findViewById(R.id.textViewRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
// [END on_start_check_user]

    @Override
    public void onBackPressed() {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.closing_activity)
                    .setMessage(R.string.close_body)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finishAffinity();

                        }

                    })
                    .setNegativeButton(R.string.no, null)
                    .show();

    }




}
