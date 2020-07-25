package elan.mit.electrofocus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import elan.mit.electrofocus.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Arduino extends AppCompatActivity implements ArduinoDialog.ArduinoDialogListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino);

        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Robo-Duino");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(!isNetworkAvailable())
        {
            Toast.makeText(this,"Please turn on your Internet Connection to view the latest updates.!",Toast.LENGTH_LONG).show();
        }
        DatabaseReference mArduino = FirebaseDatabase.getInstance().getReference().child("WorkShops").child("Arduino").child("General");
        mArduino.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date = dataSnapshot.child("date").getValue().toString();
                String duration = dataSnapshot.child("duration").getValue().toString();
                String fee = dataSnapshot.child("fee").getValue().toString();
                String venue = dataSnapshot.child("venue").getValue().toString();

                TextView DateText = findViewById(R.id.date_id);
                TextView VenueText = findViewById(R.id.venue_id);
                TextView DurationText = findViewById(R.id.duration_id);
                TextView FeeText = findViewById(R.id.fee_id);

                DateText.setText(date);
                VenueText.setText(venue);
                FeeText.setText(fee);
                DurationText.setText(duration);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button RegisterNowButton = findViewById(R.id.register_now_button);
        RegisterNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ArduinoDialog arduinoDialog = new ArduinoDialog();
                arduinoDialog.show(getSupportFragmentManager(),"Arduino_DIALOG");
                arduinoDialog.setCancelable(false);
            }
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void ApplyText(int index)
    {
        Log.d("INDEX_TAG",index+"");
        switch (index)
        {
            case -1:
                ArduinoDialog arduinoDialog = new ArduinoDialog();
                arduinoDialog.show(getSupportFragmentManager(),"Arduino_DIALOG");
                arduinoDialog.setCancelable(false);
                Toast.makeText(this,"You have to Choose any one of them to Continue",Toast.LENGTH_SHORT).show();
                break;
            case 0:
                Uri uri = Uri.parse("https://www.payumoney.com/events/#/buyTickets/efocus20_robo1");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case 1:
                Uri uri1 = Uri.parse("https://www.payumoney.com/events/#/buyTickets/efocus20_robo2");
                Intent intent1 = new Intent(Intent.ACTION_VIEW,uri1);
                startActivity(intent1);
                break;
            case 2:
                Uri uri2 = Uri.parse("https://www.payumoney.com/events/#/buyTickets/efocus20_robo3");
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri2);
                startActivity(intent2);
                break;
            case 3:
                Uri uri3 = Uri.parse("https://www.payumoney.com/events/#/buyTickets/efocus20_robo4");
                Intent intent3 = new Intent(Intent.ACTION_VIEW,uri3);
                startActivity(intent3);
                break;
        }
    }
}
