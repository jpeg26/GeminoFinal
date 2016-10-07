package com.mlabs.bbm.firstandroidapp_morningclass;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        final DataBaseAdapter sqlDB = new DataBaseAdapter(getApplicationContext());

        final EditText email = (EditText) findViewById(R.id.etRegUser);
        final EditText password = (EditText) findViewById(R.id.etRegPass);
        final EditText Conpass = (EditText) findViewById(R.id.etConPass);
        final EditText username = (EditText) findViewById(R.id.eTxtUser);
        final EditText firstname = (EditText) findViewById(R.id.eTxtFname);
        final EditText lastname = (EditText) findViewById(R.id.eTextLname);
        Button register = (Button) findViewById(R.id.btnReg);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Emails = email.getText().toString();
                String Pass = password.getText().toString();
                String confirmPassword = Conpass.getText().toString();
                String uname = username.getText().toString();
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();

                if (!Emails.isEmpty() && !Pass.isEmpty() && !confirmPassword.isEmpty() && !uname.isEmpty() && !fname.isEmpty() && !lname.isEmpty()) {
                    if (validateName(fname) == true && validateName(lname) == true) {
                        if (validateEmail(Emails) == true && validatePassword(Pass) == true)
                            if (sqlDB.validateEmail(Emails) == true) {
                                if (sqlDB.validateUserName(uname) == true) {
                                    if (Pass.equals(confirmPassword)) {
                                        sqlDB.registerUser(Emails, Pass, uname, fname, lname, getDateTime());
                                        Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Username already exists.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Email Address already exists", Toast.LENGTH_SHORT).show();
                            }
                        else if (validateEmail(Emails) == false && validatePassword(Pass) == true) {
                            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                        } else if (validateEmail(Emails) == true && validatePassword(Pass) == false) {
                            Toast.makeText(getApplicationContext(), "Password must be more than 8 characters", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Names must not contain numbers", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill up required fields", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public boolean validateEmail(String Emails) {
        String regexEmail = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";

        Pattern p = Pattern.compile(regexEmail);
        Matcher m = p.matcher(Emails);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePassword(String Pass) {
        if (Pass.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateName(String name) {
        String regexEmail = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$";
        Pattern p = Pattern.compile(regexEmail);
        Matcher m = p.matcher(name);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }

    }
}