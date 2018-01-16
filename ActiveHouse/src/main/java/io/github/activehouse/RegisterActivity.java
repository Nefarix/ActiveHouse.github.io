//AH Studios
//ActiveHouseV2 Project

package io.github.activehouse;


import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText etUsername, etPassword, etFName, etLName, etEmail, etHouseID;
    String Username, Password, FName, LName, Email, HouseID;
    Button regButton;
    public Map<String, Boolean> DBObj = new HashMap<>();
    private String TAG = RegisterActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // Start Initializing Auth
        mAuth = FirebaseAuth.getInstance();
        // End Initializing Auth

        etUsername = (EditText) findViewById(R.id.input_username);
        etPassword = (EditText) findViewById(R.id.input_password);
        etFName = (EditText) findViewById(R.id.input_fname);
        etLName = (EditText) findViewById(R.id.input_lname);
        etEmail = (EditText) findViewById(R.id.input_email);
        etHouseID = (EditText) findViewById(R.id.input_houseid);
        regButton = (Button) findViewById(R.id.btn_register);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (etPassword.getText().length() > 0 && etUsername.getText().length() > 0 && etFName.getText().length() > 0
                        && etLName.getText().length() > 0 && etEmail.getText().length() > 0 && etHouseID.getText().length() > 0) {

                    //attempt registration
                    Username = etUsername.getText().toString();
                    Password = md5(etPassword.getText().toString());
                    FName = etFName.getText().toString();
                    LName = etLName.getText().toString();
                    Email = etEmail.getText().toString();
                    HouseID = etHouseID.getText().toString();
                    Toast.makeText(RegisterActivity.this, "Button Pressed", Toast.LENGTH_SHORT).show();
                    new Registration().execute();

                } else {
                    Toast.makeText(RegisterActivity.this, R.string.errorRegistering, Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
//
//    private void createAccount(String email, String password) {
//        Log.d(TAG, "createAccount:" + email);
//        if (!validateForm()) {
//            return;
//        }
//
//
//
//        // [START create_user_with_email]
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//                });
//        // [END create_user_with_email]
//    }
//
//    private boolean validateForm() {
//        boolean valid = true;
//
//        String email = etEmail.getText().toString();
//        if (TextUtils.isEmpty(email)) {
//            etEmail.setError("Required.");
//            valid = false;
//        } else {
//            etEmail.setError(null);
//        }
//
//        String password = etPassword.getText().toString();
//        if (TextUtils.isEmpty(password)) {
//            etPassword.setError("Required.");
//            valid = false;
//        } else {
//            etPassword.setError(null);
//        }
//
//        return valid;
//    }


//public RegisterActivity()
//{}
//
//public RegisterActivity(String username, String password, String FName, String LName, String Email, String HouseID)
//{
//    this.Username = username;
//    this.Password = password;
//    this.FName = FName;
//    this.LName = LName;
//    this.Email = Email;
//    this.HouseID = HouseID;
//}
//
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("Username", Username);
//        result.put("Password", Password);
//        result.put("FName", FName);
//        result.put("LName", LName);
//        result.put("Email", Email);
//        result.put("Lname", HouseID);
//
//        return result;
//    }


    private class Registration extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(HomeActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }


        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String url = "https://activehousegithubio.firebaseio.com" + Username + "&password=" + Password
                    + "&fname=" + FName + "&lname=" + LName + "&email=" + Email + "&houseid=" + HouseID;
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    int success = jsonObj.getInt("success");
                    if (success == 1) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), R.string.regSuccess, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });


                    } else if (success == 2) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), R.string.invalid_houseid, Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), R.string.errorRegistering, Toast.LENGTH_LONG).show();
                            }
                        });
                    }


                } catch (final JSONException e) {
                    Log.e(TAG, R.string.jsonError + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    R.string.jsonError + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                R.string.jsonError3,
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);


        }
    }


    public static String md5(String s) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(Charset.forName("US-ASCII")), 0, s.length());
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            String hash = String.format("%0" + (magnitude.length << 1) + "x", bi);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}

