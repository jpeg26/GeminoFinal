package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {

    EditText User, Pass, showPass;
    Button AccessBtn, signUpBtn;
    DataBaseAdapter db = new DataBaseAdapter(this);

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AccessBtn = (Button) findViewById(R.id.BLogin);
        signUpBtn = (Button) findViewById(R.id.supB);
        User = (EditText) findViewById(R.id.Uname);
        Pass = (EditText) findViewById(R.id.Pword);
        showPass = (EditText) findViewById(R.id.show);


        AccessBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String userName=User.getText().toString();
                String password=Pass.getText().toString();

                if (db.loginCheck(userName, password)){
                    Intent intent = new Intent(MainActivity.this,Loggedin.class );
                    startActivity(intent);
                    finish();
                }

                else
                    Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Sign_Up.class);
                startActivity(intent);
            }
        });
        showPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("event", "down");
                        Pass.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("event", "down");
                        Pass.setTransformationMethod(new PasswordTransformationMethod());
                        return true;
                }
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
