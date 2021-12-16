package com.example.rb_solchat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText emailView;
    private EditText passwordView;
    private EditText confirmPasswordView;
    EditText usernameView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        usernameView = findViewById(R.id.register_username);
        emailView = findViewById(R.id.register_email);
        passwordView = findViewById(R.id.register_password);
        confirmPasswordView = findViewById(R.id.register_confirm_password);

        // Keyboard sign in action
//        confirmPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
//            if (id == R.integer.register_form_finished || id == EditorInfo.IME_NULL) {
//                attemptRegistration();
//                return true;
//            }
//            return false;
//        });

        Log.d("RB-Chat", "Instance created");
        mAuth = FirebaseAuth.getInstance();

    }

    public void signUp(View v) {
        Log.d("RB-Chat", "Attempt registration called!!!");
        attemptRegistration();
    }

    private void attemptRegistration() {
        Log.d("RB-Chat", "Entered Attempt registration!!!");
        // Reset errors displayed in the form.
//        emailView.setError(null);
//        passwordView.setError(null);

        // Store values at the time of the login attempt.
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        // Check for a valid password, if the user entered one.

        if (TextUtils.isEmpty(usernameView.getText().toString().trim())) {
            usernameView.setError(getString(R.string.user_is_empty));
            return;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailView.getText().toString().trim())) {
            emailView.setError(getString(R.string.error_field_required));
            return;
        }

        if (!isEmailValid(emailView.getText().toString().trim())) {
            emailView.setError(getString(R.string.error_invalid_email));
            return;
        }

        if (TextUtils.isEmpty(passwordView.getText().toString().trim()) || !isPasswordValid(passwordView.getText().toString().trim())) {
            passwordView.setError(getString(R.string.error_invalid_password));
            return;
        }

        createFirebaseUser();
    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return confirmPasswordView.getText().toString().trim().equals(passwordView.getText().toString().trim()) && password.length() > 5;
    }

    private void createFirebaseUser() {
        Log.d("RB-Chat", "Entered create firebase user!!!");
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("RB-Chat", "Registration Successful!!!" + task.isSuccessful());
                if (task.isSuccessful()) {
                    Log.d("RB-Chat", "Registration Successful!!!");
                    showMessageDialoge("Registration attempt Successful", "Hurray");
                } else {
                    Log.d("RB-Chat", "Registration UnSuccessful!!!");
                    showMessageDialoge("Registration attempt Failed", "Oops");
                }
            }
        });
    }

    public void showMessageDialoge(String message, String title) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
