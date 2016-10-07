package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity{

    DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        @Override
                public boolean onCreateOptionsMenu(Menu menu){
            // inflate the menu;
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        public void onButtonClick(View v)
    {
        if(v.getId() == R.id.Blogin)
        {
            EditText a = (EditText)findViewById(R.id.TFusername);
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.TFpassword);
            String pass = b.getText().toString();




            String password = helper.searchPass(str);
            if(pass.equals(password))
            {

            Intent i = new Intent(MainActivity.this, Display.class);
            i.putExtra("Username",str);
            startActivity(i);

        }
            else
            {
                Toast temp = Toast.makeText(MainActivity.this, “Username and Password dont match!” ,Toast.LENGTH_SHORT);
                temp.show(); }



        if(v.getId() == R.id.Bsignup) {
            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }
        }
    }

        Button login1 = (Button) findViewById(R.id.login);
        login1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText Email1 = (EditText) findViewById(R.id.Email);
                final EditText Pw1 = (EditText) findViewById(R.id.Pw);

                String email = Email1.getText().toString();
                String password = Pw1.getText().toString();

                if (!validateEmail(email)) {
                    Email1.setError("Not a valid email address!");
                } else if (!validatePassword(password)) {
                    Pw1.setError("Not a valid password!");
                } else {
                    Email1.setError(null);
                    Pw1.setError(null);
                    doLogin();
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

    public void doLogin() {
        Toast.makeText(getApplicationContext(), "Successfully Logged-in", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, SignUp.class);

        startActivity(i);


    }

}



