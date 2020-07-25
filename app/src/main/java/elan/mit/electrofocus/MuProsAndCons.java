package elan.mit.electrofocus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class MuProsAndCons extends AppCompatActivity {
    private DatabaseReference mMuProsAndCons;
    private String check = "no";
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mu_pros_and_cons);

        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("MU Pros And Cons");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(!isNetworkAvailable())
        {
            Toast.makeText(this,"Please turn on your Internet Connection to view the latest updates.!",Toast.LENGTH_LONG).show();
        }

        mMuProsAndCons = FirebaseDatabase.getInstance().getReference().child("Events").child("MuProsAndCons").child("General");
        mMuProsAndCons.keepSynced(true);
        mMuProsAndCons.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date = dataSnapshot.child("date").getValue().toString();
                String venue = dataSnapshot.child("venue").getValue().toString();
                String time = dataSnapshot.child("time").getValue().toString();
                title = dataSnapshot.child("heading").getValue().toString();
                check = dataSnapshot.child("key").getValue().toString();

//                Log.d("FireBase_CHECK","Date:"+date+" Venue:"+venue);

                TextView DateText = findViewById(R.id.date_id);
                TextView VenueText = findViewById(R.id.venue_id);
                TextView TitleText = findViewById(R.id.title_id);
                TextView TimeText = findViewById(R.id.time_id);

                DateText.setText(date);
                VenueText.setText(venue);
                TitleText.setText(title);
                TimeText.setText(time);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button ViewButton = findViewById(R.id.view_selection_list_button);
        ViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(isNetworkAvailable())
                {
                    if(check.equals("yes"))
                    {
                        Intent SelectionListIntent = new Intent(MuProsAndCons.this,EventSelectionList.class);
                        SelectionListIntent.putExtra("event","MuProsAndCons");
                        SelectionListIntent.putExtra("toolbar","MU Pros And Cons");
                        startActivity(SelectionListIntent);
                    }
                    else if(check.equals("anyone"))
                    {
                        Toast.makeText(MuProsAndCons.this,"Anyone Can Attend "+title+": )",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(title==null)
                        {
                            Toast.makeText(MuProsAndCons.this, "Please wait while fetching the data", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(MuProsAndCons.this, "Selection List For " + title + " is not yet updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(MuProsAndCons.this,"Please Check Your Internet Connection",Toast.LENGTH_LONG).show();
                }
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
