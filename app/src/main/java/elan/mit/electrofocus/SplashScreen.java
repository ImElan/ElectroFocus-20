package elan.mit.electrofocus;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent MainIntent = new Intent(SplashScreen.this,OnBoardingActivity.class);

        Thread timer = new Thread()
        {
            public void run(){
                try {
                    sleep(400);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    startActivity(MainIntent);
                    finish();
                }
            }
        };
        timer.start();

    }

}
