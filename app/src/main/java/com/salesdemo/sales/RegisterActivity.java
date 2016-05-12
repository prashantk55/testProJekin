package com.salesdemo.sales;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Credencys-06 on 08/04/2016.
 */
public class RegisterActivity extends Activity {

    EditText loginEmail,loginPassword,loginFirstName,loginPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginFirstName = (EditText) findViewById(R.id.loginFirstName);
        loginPhone = (EditText) findViewById(R.id.loginPhone);

        ((TextView) findViewById(R.id.loginSignUpButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = loginEmail.getText().toString().trim().toLowerCase();
                final String pass = loginPassword.getText().toString();
                final String firstname = loginFirstName.getText().toString().trim().toLowerCase();
                final String phoneno = loginPhone.getText().toString();
                if (TextUtils.isEmpty(firstname)) {
                    Toast.makeText(getApplicationContext(), "Please enter first name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(phoneno)) {
                    Toast.makeText(getApplicationContext(), "Please enter phone no", Toast.LENGTH_SHORT).show();
                }
                else if(phoneno.toString().trim().length()!=10) {
                    Toast.makeText(getApplicationContext(), "Please enter valid phone no", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(email)) {
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
                    //Save Notification Data into Local DB
                    DataBaseHelper mDataBaseHelper = new DataBaseHelper(RegisterActivity.this);

                    if(mDataBaseHelper.CheckForUserExsist(email)) {
                        Toast.makeText(getApplicationContext(), "Sorry, email address is already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        int mPK = mDataBaseHelper.Insert_RegisterData(firstname, email, pass, phoneno);
                        if (mPK != -1) {
                            startActivity(new Intent(RegisterActivity.this, LandingPageActivity.class));
                            finish();
                            overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(getApplicationContext(), "Sorry, due to some reason we are not able to register user", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
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
