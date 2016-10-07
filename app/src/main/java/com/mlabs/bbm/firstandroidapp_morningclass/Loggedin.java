package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Loggedin extends AppCompatActivity {

    Button OnTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        OnTouch = (Button) findViewById(R.id.OnTouch);
        OnTouch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Loggedin.this, OnTouchActivity.class);
                startActivity(intent);
            }
        });

        OnTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(Loggedin.this, OnTouchActivity.class);
                startActivity(intent);
                return false;
            }
        });

    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}
