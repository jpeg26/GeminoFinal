package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPW;
    Pattern emailP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPW = (EditText) findViewById(R.id.etPW);
        findViewById(R.id.btnLogin).setOnClickListener(btnLoginClickListener);

        emailP = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    View.OnClickListener btnLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (validate(etEmail.getText().toString(),etPW.getText().toString())){
                Intent intent = new Intent(MainActivity.this,Blank.class );
                startActivity(intent);
            }
            else
                Toast.makeText(getApplicationContext(), "Invalid Email Address/Password", Toast.LENGTH_LONG).show();
        }
    };

    Boolean validate(String email, String pw)
    {   Matcher m;
        m = emailP.matcher(email);
        if (m.matches()&&pw.length()>=8&&pw.length()!=0){
            return true;
        }
        else
            return false;
    }
}
