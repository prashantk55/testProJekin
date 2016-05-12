package com.salesdemo.sales;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Credencys-06 on 11/04/2016.
 */
public class CompleteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        ((TextView) findViewById(R.id.textView)).setText("Complete Coming Soon");

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}