package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registerform extends AppCompatActivity {
    DatabaseAdapter db;

    Button  btnSend;
    EditText regEmail,regPass,regConPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new DatabaseAdapter(this);

        regEmail= (EditText) findViewById(R.id.regEmail);
        regPass = (EditText) findViewById(R.id.regPass);
        regConPass = (EditText) findViewById(R.id.regConPass);
        btnSend= (Button)findViewById(R.id.btnSend);
        addAcct();

    }


     public void addAcct() {
         btnSend.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String email = regEmail.getText().toString().trim();
                 String password = regPass.getText().toString();
                 String confirmPassword = regConPass.getText().toString();

                 final String email1 = regEmail.getText().toString();
                 final String pass1 = regPass.getText().toString();
                 final String conPass1 = regConPass.getText().toString();


                 if (email.equals("") || (password.equals("") || (confirmPassword.equals("")))) {
                     Toast.makeText(getApplicationContext(), "Please complete all Fields", Toast.LENGTH_LONG).show();
                     return;

                 }
                 if (!isValidEmail(email1)) {
                     regEmail.setError("Invalid Email");


                 } else if (!isValidPassword(pass1)) {
                     regPass.setError("Invalid Password");


                 }

                 if (!pass1.equals(conPass1)) {
                     Toast.makeText(getApplicationContext(), "Password does not match!", Toast.LENGTH_LONG).show();
                     return;
                 } else {
                     boolean regSuccess = db.registerUser(regEmail.getText().toString(), regPass.getText().toString(), GetCurrentDateAndTime());
                     if (regSuccess == true) {
                         Toast.makeText(getApplicationContext(), "Account Added!", Toast.LENGTH_SHORT).show();
                         Intent loginIntent = new Intent(Registerform.this, MainActivity.class);
                         startActivity(loginIntent);
                         finish();
                     }

                 }

             }
         });
     }

    private boolean isValidEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


                 private boolean isValidPassword(String password) {
                     if (password != null && password.length() > 8) {
                         return true;
                     }
                     return false;

                 }

        public String GetCurrentDateAndTime(){
            DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            System.out.println(dateformat.format(date));
            return dateformat.format(date).toString();

        }}