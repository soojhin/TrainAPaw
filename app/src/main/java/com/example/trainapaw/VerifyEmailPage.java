package com.example.trainapaw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyEmailPage extends AppCompatActivity {

    Button verifyEmailButton;
    TextView signIn;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email_page);

        signIn = findViewById(R.id.sign_in);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifyEmailPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        verifyEmailButton = findViewById(R.id.verifyEmailButton);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        verifyEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user != null) {
                    // Check if the user is already verified
                    if (user.isEmailVerified()) {
                        // User is already verified
                        Toast.makeText(view.getContext(), R.string.already_verified, Toast.LENGTH_SHORT).show();
                    } else {
                        // User is not verified, send verification email
                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(view.getContext(), R.string.verification_email_sent, Toast.LENGTH_SHORT).show();
                                disableButtonForTimeout();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("VerifyEmailPage", "onFailure: Email not sent" + e.getMessage());
                            }
                        });
                    }
                } else {
                    // Handle null user case
                    Log.d("VerifyEmailPage", "User is null");
                }
            }
        });

    }

    private void disableButtonForTimeout() {
        verifyEmailButton.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                verifyEmailButton.setEnabled(true);
            }
        }, 10000); // 10 seconds in milliseconds
    }
}
