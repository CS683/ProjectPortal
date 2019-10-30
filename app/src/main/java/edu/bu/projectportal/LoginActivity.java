package edu.bu.projectportal;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText emailEditText, passwdEditText;
    private Button loginBtn, signupBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Initialize Email and Password Login inputs and buttons

        emailEditText = findViewById(R.id.emailEditText);
        passwdEditText = findViewById(R.id.passwdEditText);

        loginBtn =  findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);

        // login_progress = (ProgressBar)findViewById(R.id.login_progress);

        FirebaseApp.initializeApp (this);
        mAuth = FirebaseAuth.getInstance();

        signupBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int viewID = view.getId();


        switch (viewID) {
            case R.id.signupBtn:
                createUserAccount(emailEditText.getText().toString(), passwdEditText.getText().toString(), emailEditText.getText().toString());
                break;
            case R.id.loginBtn:
                login(emailEditText.getText().toString(), passwdEditText.getText().toString());
                break;
            // case R.id.googleLoginBtn:
            // googleLogin();
            default:
                break;

        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null)
        {
            // User is signed in
            updateUI(user);
        } else {
            // No user is signed in
            Toast.makeText (this, "No user signed in", Toast.LENGTH_LONG).show();

        }
    }

    private void updateUI(FirebaseUser user) {
        if(user != null) {
            Intent mainIntent = new Intent( LoginActivity.this, ProjectsListActivity.class);
            startActivity(mainIntent);
            //finish();

        } else {
            Log.d("updateUI", "The user is not logged in.");
        }
    }



    public void login(final String email, final String passwd) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(passwd)) {
            //   login_progress.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener(
                    new OnCompleteListener<AuthResult> () {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("signInExisitingUser", "Signed in exisiting user with email: " + email);
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user.getDisplayName () != null)
                                    Toast.makeText(LoginActivity.this, "Welcome back, " +
                                        user.getDisplayName() + "!", Toast.LENGTH_SHORT).show();
                                updateUI(user);
                            } else {
                                String errormessage = task.getException().getMessage();
                                if (errormessage != null)
                                    Toast.makeText(LoginActivity.this, "Error: "
                                        + errormessage, Toast.LENGTH_LONG).show();
                            }
                            //  login_progress.setVisibility(View.INVISIBLE);

                        }
                    });
        }
    }


    protected void createUserAccount(final String email, String password, final String displayName) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //Sign in was a success, create intent to go to the LinkRideShareAccountsActivity
                            Log.d("createUserAccount", "Created user with email: " + email);
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                updateUserProfile (user, displayName);
                                updateUI (user);
                                addUserInfotoFirebase (displayName, email);
                            }

                        } else {
                            //Sign in failed display error message
                            Log.w("createUserAccount", "Failed to create user with email: " + email);
                            Toast.makeText(LoginActivity.this, "Authentication failed. Error message: "
                                    + task.getException(), Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void addUserInfotoFirebase(String userName, String email){
        String curUid = mAuth.getUid();

        Map<String, String> userData = new HashMap<> ();
        userData.put("name", userName);
        userData.put("email", email);

        FirebaseFirestore.getInstance().document ("users/" + curUid ).set(userData);

    }



    public void updateUserProfile(FirebaseUser user, String displayName) {
        //Update the user's profile with their display name entered in the form
        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().
                setDisplayName(displayName).build();
        user.updateProfile(userProfileChangeRequest);
    }

    public void onBackPressed() {
        //do nothing
    }
}


