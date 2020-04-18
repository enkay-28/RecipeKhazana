package com.test.recipekhazana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;



public class LoginActivity extends AppCompatActivity {
    MaterialButton letsGo;
    EditText numberInput;
    String phoneNumber;
    public static String EXTRA_PHONE = "EXTRA_PHONE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        letsGo = findViewById(R.id.sendOtpButton);
        numberInput = findViewById(R.id.phoneInput);

        letsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = numberInput.getText().toString();
                if(phoneNumber.length()==10){
                    Intent intent = new Intent(getApplicationContext(),VerifyActivity.class);
                    intent.putExtra(EXTRA_PHONE,phoneNumber);
                    startActivity(intent);
                }else{
                    numberInput.setError("Enter a valid Mobile Number");
                }
            }
        });
    }
}
