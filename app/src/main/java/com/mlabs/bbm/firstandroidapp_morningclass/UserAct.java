package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class UserAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public class OnTouchActivity extends AppCompatActivity{

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.mainactivity2);

            final Button onTouch = (Button) findViewById(R.id.nextbtn);

            onTouch.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(UserAct.this, UserAct.class);
                    startActivity(intent);
                }

            });
        }
    }}

