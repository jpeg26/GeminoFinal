package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.database.Cursor;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText uT;

    EditText pT;

    Button Button,signup;
    TextView show;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        uT = (EditText) findViewById(R.id.userText);
        pT = (EditText) findViewById(R.id.passText);
        Button = (Button) findViewById(R.id.button);
        signup = (Button) findViewById(R.id.signbutton);
        show = (TextView) findViewById(R.id.show);
        final Context CTX = this;


        Button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                String uname = uT.getText().toString();
                String pass = pT.getText().toString();
                if(uname == "")
                {
                    uT.setError("Please enter Email");
                }
                else if(pass == "")
                {
                    pT.setError("Please enter Password");
                }
                else {
                    DBHelper db = new DBHelper(CTX);
                    Cursor cr = db.getInfo(db);
                    cr.moveToFirst();
                    boolean logstat = false;

                    do {
                        if ((uname.equals(cr.getString(3)) || uname.equals(cr.getString(4))) && (pass.equals(cr.getString(5))))
                        {
                            logstat = true;
                        }
                    } while (cr.moveToNext());
                    if (logstat == true) {
                        Intent intent = new Intent(MainActivity.this, login.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Email or Password", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, sign_up.class);
                startActivity(intent);
            }
        });

        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("event","down");
                        pT.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("event","up");
                        pT.setTransformationMethod(new PasswordTransformationMethod());
                        return true;

                }
                return false;

            }
        });
    }
    boolean isEmailValid(String x, String y) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(x).matches() && y.length() >= 8 && y.length() !=0) {
            return true;
        }
        else
            return false;
    }



    @Override
    protected void onPause(){
        super.onPause();
        finish();

    }
}
