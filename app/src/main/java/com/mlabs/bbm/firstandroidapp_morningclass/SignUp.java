package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Context context = this;
        final DataBaseAdapter DataBaseAdapter;
        DataBaseAdapter = new DataBaseAdapter(this);

        DataBaseAdapter.open();

        final EditText EmailSignUp = (EditText) findViewById(R.id.email_signup);
        final EditText PwSignUp = (EditText) findViewById(R.id.pw_signup);
        final EditText ConPwSignUp = (EditText) findViewById(R.id.conpw_signup);
        final Button CreateAccountBtn = (Button) findViewById(R.id.createaccnt_btn);

        CreateAccountBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String Email = EmailSignUp.getText().toString();
                String Password = PwSignUp.getText().toString();
                String confirmPassword = ConPwSignUp.getText().toString();

                if (Email.equals("") || Password.equals("")
                        || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill Up required fields", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!Password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!validateEmail(Email)) {
                    Toast.makeText(getApplicationContext(), "Not a valid Email", Toast.LENGTH_LONG).show();
                    return;
                } else if (!validatePassword(Password)) {
                    Toast.makeText(getApplicationContext(), "Not a valid Password", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    DataBaseAdapter.registerUser(Email,Password/*,GetCurrentDateAndTime()*/);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(SignUp.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });
    }

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String Pw) {
        return Pw.length() >=8;
    }

   /* public String GetCurrentDateAndTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date).toString();
}*/

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        // DataBaseAdapter.close();
    }



}