//AH Studios
//ActiveHouseV2 Project
package io.github.activehouse;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    CheckBox cbSave;
    Button btnLogin;
    String eemail, password;

    private FirebaseAuth mAuth;

    private String TAG = MainActivity.class.getSimpleName();

    public static String FirstName, LastName, Username, Email;
    public static int HouseID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Start Initializing Auth
        mAuth = FirebaseAuth.getInstance();
        // End Initializing Auth

        etEmail = (EditText) findViewById(R.id.input_username);
        etPassword = (EditText) findViewById(R.id.input_password);
        cbSave = (CheckBox) findViewById(R.id.input_save);

        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        String savedUsername = sharedPref.getString("Email", "");
        if (!savedUsername.equals("")) {
            etEmail.setText(savedUsername);
            cbSave.setChecked(true);
        }


        btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }


        });


//        btnLogin = (Button) findViewById(R.id.btn_login);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (etPassword.getText().length() > 0 && etEmail.getText().length() > 0) {
//
//                    //attempt login
//                    eemail = etEmail.getText().toString();
//                    password = etPassword.getText().toString();
//                    signIn(eemail, password);
//
//
//                    if (cbSave.isChecked()) {
//                        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPref.edit();
//                        editor.putString("Email", etEmail.getText().toString());
//                        editor.apply();
//
//
//                    } else {
//                        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPref.edit();
//                        editor.putString("username", "                        editor.apply(); ");
//                    }
//
//                } else {
//                    Toast.makeText(MainActivity.this, R.string.ERRORLOGGINGIN, Toast.LENGTH_SHORT).show();
//
//                }
//
//            }
//        });

        TextView tvRegister = (TextView) findViewById(R.id.textViewRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
// [END on_start_check_user]

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }


        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);

                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    ;


    // [END sign_in_with_email]


    private boolean validateForm() {
        boolean valid = true;

        eemail = etEmail.getText().toString();
        if (TextUtils.isEmpty(eemail)) {
            etEmail.setError("Required.");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Required.");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {

        } else {

        }
    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.closing_activity)
                .setMessage(R.string.close_body)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finishAffinity();

                    }

                })
                .setNegativeButton(R.string.no, null)
                .show();

    }
}

//                    private class GetLogin extends AsyncTask<Void, Void, Void> {
//                        @Override
//                        protected void onPreExecute() {
//                            super.onPreExecute();
//                            //Toast.makeText(HomeActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
//
//                        }
//
//                        @Override
//                        protected Void doInBackground(Void... arg0) {
//                            HttpHandler sh = new HttpHandler();
//
//                            String hashedPW = md5(password);
//                            // Making a request to url and getting response
//                            String url = "https://activehousegithubio.firebaseio.com/&user" + eemail + "&password=" + hashedPW;
//                            String jsonStr = sh.makeServiceCall(url);
//
//                            Log.e(TAG, "Response from url: " + jsonStr);
//                            if (jsonStr != null) {
//                                try {
//                                    JSONObject jsonObj = new JSONObject(jsonStr);
//                                    int success = jsonObj.getInt("success");
//                                    if (success == 1) {
//                                        // Getting JSON Array node
//                                        JSONArray user = jsonObj.getJSONArray("user");
//
//
//                                        JSONObject u = user.getJSONObject(0);
//                                        HouseID = u.getInt("HOUSE_ID");
//                                        Username = u.getString("USERNAME");
//                                        FirstName = u.getString("FIRST_NAME");
//                                        LastName = u.getString("LAST_NAME");
//                                        Email = u.getString("EMAIL");
//
//                                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                                        startActivity(intent);
//
//
//                                    } else {
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Toast.makeText(getApplicationContext(), R.string.errorLoggingIn, Toast.LENGTH_LONG).show();
//                                            }
//                                        });
//                                    }
//
//
//                                } catch (final JSONException e) {
//                                    Log.e(TAG, R.string.jsonError + e.getMessage());
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Toast.makeText(getApplicationContext(),
//                                                    R.string.jsonError + e.getMessage(),
//                                                    Toast.LENGTH_LONG).show();
//                                        }
//                                    });
//
//                                }
//
//                            } else {
//                                Log.e(TAG, "Couldn't get json from server.");
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(getApplicationContext(),
//                                                R.string.jsonError3,
//                                                Toast.LENGTH_LONG).show();
//                                    }
//                                });
//                            }
//
//                            return null;
//                        }
//
//                        @Override
//                        protected void onPostExecute(Void result) {
//                            super.onPostExecute(result);
//
//
//                        }
//                    }
//
//
//                    public static String md5(String s) {
//                        MessageDigest digest;
//                        try {
//                            digest = MessageDigest.getInstance("MD5");
//                            digest.update(s.getBytes(Charset.forName("US-ASCII")), 0, s.length());
//                            byte[] magnitude = digest.digest();
//                            BigInteger bi = new BigInteger(1, magnitude);
//                            String hash = String.format("%0" + (magnitude.length << 1) + "x", bi);
//                            return hash;
//                        } catch (NoSuchAlgorithmException e) {
//                            e.printStackTrace();
//                        }
//                        return "";
//                }
//}
