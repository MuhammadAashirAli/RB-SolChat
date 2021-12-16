package com.example.rb_solchat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ShowProfile extends AppCompatActivity {
    private TextView name;
    private TextView email;
    private ImageView profilePic;
    private ImageButton logoutButton;
    private Button chatButton;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        name = findViewById(R.id.user_name);
        email = findViewById(R.id.user_email);
        profilePic = findViewById(R.id.profile_pic);
        logoutButton = findViewById(R.id.logout_button);
        chatButton = findViewById(R.id.chat_button);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            String personName = account.getDisplayName();
            String personEmail = account.getEmail();
            Uri personPhoto = account.getPhotoUrl();

            name.setText(personName);
            email.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(profilePic);
        }

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowProfile.this,HomeChat.class);
                startActivity(intent);
            }
        });

    }

    private void logout() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ShowProfile.this,"Logged out",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }


}
