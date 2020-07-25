package elan.mit.electrofocus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DownloadManager;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class LineFollower extends AppCompatActivity {
    private DatabaseReference mLineFollower;
    private String check = "no";
    private String title;
    private StorageReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_follower);

        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Line Follower");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(!isNetworkAvailable())
        {
            Toast.makeText(this,"Please turn on your Internet Connection to view the latest updates.!",Toast.LENGTH_LONG).show();
        }

        mLineFollower = FirebaseDatabase.getInstance().getReference().child("Events").child("LineFollower").child("General");
        mLineFollower.keepSynced(true);

        mRef= FirebaseStorage.getInstance().getReference().child("document").child("LineFollower.pdf");
        
        mLineFollower.addValueEventListener(new ValueEventListener() {
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
                        Intent SelectionListIntent = new Intent(LineFollower.this,EventSelectionList.class);
                        SelectionListIntent.putExtra("event","LineFollower");
                        SelectionListIntent.putExtra("toolbar","Line Follower");
                        startActivity(SelectionListIntent);
                    }
                    else if(check.equals("anyone"))
                    {
                        Toast.makeText(LineFollower.this,"Anyone Can Attend "+title+": )",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(title==null)
                        {
                            Toast.makeText(LineFollower.this, "Please wait while fetching the data", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(LineFollower.this, "Selection List For " + title + " is not yet updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LineFollower.this,"Please Check Your Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        });
        Button DownloadButton = findViewById(R.id.download_button);
        DownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable())
                {
                    mRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri)
                        {
//                        String url = uri.toString();
                            DownloadManager downloadManager = (DownloadManager)(LineFollower.this).getSystemService((LineFollower.this).DOWNLOAD_SERVICE);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalFilesDir(LineFollower.this,DIRECTORY_DOWNLOADS,"LineFollower.pdf");
                            downloadManager.enqueue(request);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LineFollower.this,"Something went wrong try checking your internet.",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(LineFollower.this,"Please Check Your Internet Connection",Toast.LENGTH_LONG).show();
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
