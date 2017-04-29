package com.epitech.foodielife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tsy-jon on 30/04/2017.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button register = (Button) findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        final Button log_in = (Button) findViewById(R.id.log_in_button);
        log_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, MapsActivity.class));
            }
        });
    }
}
