package elan.mit.electrofocus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import elan.mit.electrofocus.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button TechEvent = findViewById(R.id.tech_book_button);
        TechEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.payumoney.com/events/#/buyTickets/efocus20_events");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        Button RoboticEvent = findViewById(R.id.robotic_book_button);
        RoboticEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.payumoney.com/events/#/buyTickets/efocus20_robo_events");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        Button PaperEvent = findViewById(R.id.paper_book_button);
        PaperEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.payumoney.com/events/#/buyTickets/efocus20_paper");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        Button WorkShop = findViewById(R.id.workshop_book_button);
        WorkShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainIntent = new Intent(RegistrationActivity.this,MainActivity.class);
                MainIntent.putExtra("fromRegistration","workshop");
                startActivity(MainIntent);
            }
        });
    }
}
