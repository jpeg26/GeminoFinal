package com.mlabs.bbm.firstandroidapp_morningclass.MainAct;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountRegister extends AppCompatActivity {


    DatabaseHelper accountsDb;


    Button registerButton, viewAccountsButton, backButton;
    EditText registerEmail, registerPassword, registerVerifyPassword, registerUname,registerFname,registerLname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);

        accountsDb = new DatabaseHelper(this);


        registerFname = (EditText) findViewById(R.id.registerFname);
        registerLname = (EditText) findViewById(R.id.registerLname);
        registerUname = (EditText) findViewById(R.id.registerUname);
        registerEmail = (EditText) findViewById(R.id.registerEmail);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerVerifyPassword = (EditText) findViewById(R.id.registerVerifyPass);
        registerVerifyPassword = (EditText) findViewById(R.id.registerVerifyPassword);
        registerButton = (Button) findViewById(R.id.btnRegister);
        viewAccountsButton = (Button) findViewById(R.id.buttonViewAccounts);
        backButton = (Button) findViewById(R.id.buttonBack);
        registerFname.requestFocus();
        addAccount();
        viewAll();
        back();
    }

    public void back(){
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent("android.intent.action.MAIN");
                startActivityForResult(myIntent, 0);
                onPause();
                finish();
            }
        });
    }


    public void addAccount() {

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                                String fname=registerFname.getText().toString();
                                String lname=registerLname.getText().toString();
                                String username=registerUname.getText().toString();
                String email=registerEmail.getText().toString();
                String password=registerPassword.getText().toString();
                String verifyPassword = registerVerifyPassword.getText().toString();

                                if(email.equals("")||password.equals("")||verifyPassword.equals(""))
                                    if(fname.equals("") || lname.equals("") || username.equals("") || email.equals("")||password.equals("")||verifyPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Incomplete.", Toast.LENGTH_LONG).show();
                    return;
                }else if (!validateEmail(email)) {
                    registerEmail.setError("Invalid Email Format");
                    registerEmail.requestFocus();
                }
                else if (!validatePassword(password)) {
                    registerPassword.setError("Atleast Minimum of 10 either Numerical or Alphabetical or Mix");
                    registerPassword.requestFocus();
                }

                else if(!password.equals(verifyPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {

                                        accountsDb.insertAccount(email, password);
                                        accountsDb.insertAccount(fname,lname,username,email,password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                                        Intent myIntent = new Intent("android.intent.action.MAIN");
                                        startActivity(myIntent);
                                        finish();
                }
            }
        });
    }




    public void viewAll() {


        viewAccountsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = accountsDb.getAllData();
                if (cursor.getCount() == 0) {

                    showMessage("Error", "No data found.");

                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("Id: " + cursor.getString(0) + " \n");
                    buffer.append("Email: " + cursor.getString(1) + "\n");
                    buffer.append("Password: " + cursor.getString(2) + "\n");
                }

                showMessage("Data",buffer.toString());
            }
        });

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    protected boolean validatePassword(String password) {
                if(password!=null && password.length()>9) {
                    if(password!=null && password.length()>=8) {
                return true;
            } else {
                return false;
            }
        }



        @Override
        protected void onPause() {
            super.onPause();
        }
    }