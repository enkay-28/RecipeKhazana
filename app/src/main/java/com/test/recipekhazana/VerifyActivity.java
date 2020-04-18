package com.test.recipekhazana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {

    String phoneNumber;
    TextView message;
    MaterialButton verifyButton;
    PinView pinView;
    ProgressBar progress;
    String otpSent;
    String otp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        phoneNumber = getIntent().getStringExtra(LoginActivity.EXTRA_PHONE);
        verifyButton = findViewById(R.id.verifyOtpButton);
        pinView = findViewById(R.id.pinView);
        message = findViewById(R.id.enterOtpText);
        message.setText(message.getText() + "+91-" + phoneNumber);
        progress = findViewById(R.id.progressBar2);

        sendOtpToUser(phoneNumber);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otp==null){
                    otp = pinView.getText().toString();

                }
                verifyOtp(otp);
            }
        });

    }

    private void sendOtpToUser(String phoneNumber){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phoneNumber,        // Phone number to verify
                30,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallback
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            otpSent = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            otp = phoneAuthCredential.getSmsCode();
            if(otp!=null){
                pinView.setText(otp);
                progress.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyOtp(String otp){

        PhoneAuthCredential phoneAuthCredential =  PhoneAuthProvider.getCredential(otpSent,otp);
        signInUser(phoneAuthCredential);
    }

    private void signInUser(PhoneAuthCredential credential){

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(VerifyActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();

                }else{

                    Toast.makeText(VerifyActivity.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
