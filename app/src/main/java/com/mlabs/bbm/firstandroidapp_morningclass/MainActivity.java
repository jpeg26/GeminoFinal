package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;
import android.app.AlertDialog;
import android.text.InputType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity extends AppCompatActivity {

    EditText password, email;
    TextView Show, reglink;
    Button login;
    boolean showpassword = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.etUser);
        password = (EditText) findViewById(R.id.etPass);
        Show = (TextView) findViewById(R.id.tvshow);
        reglink = (TextView) findViewById(R.id.tvSignUp);
        login = (Button) findViewById(R.id.bLog);

        final DataBaseAdapter sqlDB = new DataBaseAdapter(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (checkLogin(emailAdd.getText().toString(), pword.getText().toString()) == true) {
                if (sqlDB.validateUserFromEmail(email.getText().toString(), password.getText().toString()) == true
                        || sqlDB.validateUserFromUName(email.getText().toString(), password.getText().toString()) == true) {
                    //Toast.makeText(getApplicationContext(), "Connecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, UserAct.class);
                    startActivity(intent);

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            MainActivity.this).create();
                    alertDialog.setMessage("Invalid Email Address/Password");
                    alertDialog.show();
                }
            }

        });

        Show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        password.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_UP:
                        password.setTransformationMethod(new PasswordTransformationMethod());
                        return false;
                }
                return false;

            }
        });


        reglink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(MainActivity.this, SignUp.class);
                startActivity(signup);

            }

        });
    }


    public boolean checkLogin(String email, String password) {
        String regexEmail = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";

        Pattern p = Pattern.compile(regexEmail);
        Matcher m = p.matcher(email);

        if (password.length() >= 8 && m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();

    }

}



