package com.salesdemo.sales;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    EditText loginEmail,loginPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);

        ((TextView) findViewById(R.id.loginSignInButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = loginEmail.getText().toString().trim().toLowerCase();
                final String pass = loginPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please enter email address", Toast.LENGTH_SHORT).show();
                }
                else if( !IsValidEmail(email) ) {
                    Toast.makeText(getApplicationContext(), "Please enter valid email address", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DataBaseHelper mDataBaseHelper = new DataBaseHelper(LoginActivity.this);
                    boolean IsLogin = mDataBaseHelper.CheckForUser(email,pass);
                    if(IsLogin)  {
                        startActivity(new Intent(LoginActivity.this, LandingPageActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid login credential detail", Toast.LENGTH_SHORT).show();
                    }
                    //End Here
                }
            }
        });

        ((TextView) findViewById(R.id.loginSignUpButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                overridePendingTransition(0, 0);
            }
        });

    }
    public boolean IsValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
