package com.example.govind.nicappnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by nicsi on 05-07-2017.
 */

public class loginselect extends Activity {
    Button b1, b2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_select);

        // Download JSON file AsyncTask
        b1 = (Button) findViewById(R.id.button_login);
        b2 = (Button) findViewById(R.id.button_ror);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginselect.this,login.class);
                loginselect.this.startActivity(i);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginselect.this,MainActivity.class);
                loginselect.this.startActivity(i);

            }
        });
    }

}