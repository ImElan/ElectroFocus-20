package elan.mit.electrofocus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import elan.mit.electrofocus.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class AboutActivity extends AppCompatActivity
{
    private StorageReference mRef;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> images = new ArrayList<>();
    private ArrayList<String> designation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About");

        GetImages();

        mRef= FirebaseStorage.getInstance().getReference().child("document").child("timing.jpg");

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
                            DownloadManager downloadManager = (DownloadManager)(AboutActivity.this).getSystemService(DOWNLOAD_SERVICE);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalFilesDir(AboutActivity.this,DIRECTORY_DOWNLOADS,"schedule.jpg");
                            downloadManager.enqueue(request);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AboutActivity.this,"Something went wrong try checking your internet.",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else
                {
                    Toast.makeText(AboutActivity.this,"Please Check Your Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        });

        ImageView InstagramButton = findViewById(R.id.instagram_id);
        InstagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/eea.mit/");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        ImageView FacebookButton = findViewById(R.id.facebook_id);
        FacebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/electrofocus.in/");
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

    private void GetImages()
    {
        names.add("Dr.M.Ganesh Madhan");
        names.add("Mr. G. Balamurugan");
        names.add("Sanjay");
        names.add("Krithika");
        names.add("Parthiban");
        names.add("Thiru Senthil Kumaran");

        designation.add("Professor and Head");
        designation.add("Student President");
        designation.add("Chairman");
        designation.add("Vice Chairman");
        designation.add("General Secretary");
        designation.add("Treasurer");


        images.add(R.drawable.hod);
        images.add(R.drawable.presi);
        images.add(R.drawable.chair);
        images.add(R.drawable.vice);
        images.add(R.drawable.gs);
        images.add(R.drawable.treasurer);

        SetHorizontalRecycler();
    }

    private void SetHorizontalRecycler()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView horizontalRecycler = findViewById(R.id.horizontal_recycler_view);
        horizontalRecycler.setLayoutManager(linearLayoutManager);
        HorizontalRecyclerViewAdapter  adapter = new HorizontalRecyclerViewAdapter(names,images,designation);
        horizontalRecycler.setAdapter(adapter);
    }
}
