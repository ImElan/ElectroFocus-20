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

public class AutonomousCar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autonomous_car);
        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Autonomous Cars Using Deep Neural Networks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(!isNetworkAvailable())
        {
            Toast.makeText(this,"Please turn on your Internet Connection to view the latest updates.!",Toast.LENGTH_LONG).show();
        }
        DatabaseReference mAutonomousCar = FirebaseDatabase.getInstance().getReference().child("WorkShops").child("AutonomousCar").child("General");
        mAutonomousCar.addValueEventListener(new ValueEventListener() {
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
                Uri uri = Uri.parse("https://www.payumoney.com/events/#/buyTickets/efocus20_dxc");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        Button PythonEnglish = findViewById(R.id.python_english);
        PythonEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=NUtPEwCaLU4&list=PLPI-yNdCjDx88dWYve3idR7P0B-ewcygY");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });


        Button PythonTamil = findViewById(R.id.python_tamil);
        PythonTamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=8jzN5llIy7I&list=PLPI-yNdCjDx-sq0T-XbfEDMf0lx4te9-7");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
