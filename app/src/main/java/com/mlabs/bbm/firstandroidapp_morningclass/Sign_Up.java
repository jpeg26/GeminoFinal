package com.mlabs.bbm.firstandroidapp_morningclass;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_Up extends AppCompatActivity {

    Button Create_Acct;
    EditText eMail, rPass, rCpass, uName, fName, lName;
    DataBaseAdapter db = new DataBaseAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);



        Create_Acct = (Button) findViewById(R.id.bCreate);
        uName = (EditText) findViewById(R.id.uNameR);
        eMail = (EditText) findViewById(R.id.eMailR);
        rPass = (EditText) findViewById(R.id.rPassR);
        rCpass = (EditText) findViewById(R.id.rCpassR);
        fName = (EditText) findViewById(R.id.fNameR);
        lName = (EditText) findViewById(R.id.lNameR);

        Create_Acct.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {

                String uN = uName.getText().toString();
                String eM = eMail.getText().toString();
                String pR = rPass.getText().toString();
                String pC = rCpass.getText().toString();
                String fN = fName.getText().toString();
                String lN = lName.getText().toString();

                if (ValidUser(uN) && ValidEmail(eM) && ValidPass(pR, pC)) {
                    DataBaseHelper a = new DataBaseHelper();
                    a.setAccounts(uN, eM, pR, fN, lN);
                    db.addAcc(a);
                    uName.setText("");
                    eMail.setText("");
                    rPass.setText("");
                    rCpass.setText("");
                    fName.setText("");
                    lName.setText("");
                    Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent = new Intent(Sign_Up.this,MainActivity.class );
                    startActivity(intent);

                }
            }
        });
    }

        boolean ValidEmail(String mail) {
            Log.d("Email", mail);
            if  ((android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) && db.checkEmail(mail))
                return true;
            else {
                Toast.makeText(getApplicationContext(), "Invalid Email!", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        boolean ValidPass(String pass, String pass2) {
            if (pass.length() >= 8 && pass.equals(pass2))
                return true;
            else {
                Toast.makeText(getApplicationContext(), "Passwords Don't Match!", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        boolean ValidUser(String user) {
            if (db.checkUser(user))
                return true;
            else {
                Toast.makeText(getApplicationContext(), "Invalid Username!", Toast.LENGTH_LONG).show();
                return false;
            }
        }


}
