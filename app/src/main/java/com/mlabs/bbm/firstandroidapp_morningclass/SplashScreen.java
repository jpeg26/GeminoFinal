package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by benjarmanalili on 31/07/2016.
 */
<<<<<<< HEAD

public class SplashScreen extends Activity{
=======
public class SplashScreen extends Activity {
>>>>>>> daf1a508abdefe32f94f5c4ee200722e92e1d82b

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

<<<<<<< HEAD
        Thread thread = new Thread(){

            public void run(){
                try{
                    sleep(3000);
                }catch(Exception exception){
                    exception.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this, LoginCont.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        thread.start();
    }

=======
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


>>>>>>> daf1a508abdefe32f94f5c4ee200722e92e1d82b
}