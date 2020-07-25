package elan.mit.electrofocus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import elan.mit.electrofocus.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class WorkShopFragment extends Fragment implements ArduinoDialogFragment.ArduinoDialogFragmentListener
{
    private DatabaseReference mWorkRef;
    private FirebaseRecyclerAdapter<WorkShopClass,WorkShopAdapter> adapter;
    private RecyclerView mRecycler;

    public WorkShopFragment()
    {

    }

    @SuppressLint("UseSparseArrays")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View mView = inflater.inflate(R.layout.fragment_work_shop, container, false);

        if(!isNetworkAvailable())
        {
            Toast.makeText(getActivity(),"Please turn on your Internet Connection to view the latest updates.!",Toast.LENGTH_LONG).show();
        }

        mWorkRef = FirebaseDatabase.getInstance().getReference().child("WorkShopFragment");
        mWorkRef.keepSynced(true);

        mRecycler = mView.findViewById(R.id.workshop_recycler);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setHasFixedSize(true);

        adapter = new FirebaseRecyclerAdapter<WorkShopClass, WorkShopAdapter>(
                WorkShopClass.class,
                R.layout.single_workshop_layout,
                WorkShopAdapter.class,
                mWorkRef
        ) {
            @Override
            protected void populateViewHolder(final WorkShopAdapter workShopAdapter, final WorkShopClass workShopClass, final int position) {
                workShopAdapter.setTitleHolder(workShopClass.getTitle());
                workShopAdapter.setDateHolder(workShopClass.getDate());
                workShopAdapter.setDescriptionHolder(workShopClass.getDescription());
                workShopAdapter.setFeeHolder(workShopClass.getFee());
                workShopAdapter.setVenueHolder(workShopClass.getVenue());
                workShopAdapter.setPhoto(getContext(),position);

                workShopAdapter.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position)
                        {
                            case 0:
                                Intent Cyber = new Intent(getActivity(),CryptoGraphy.class);
                                startActivity(Cyber);
                                break;
                            case 1:
                                Intent Autonomus = new Intent(getActivity(),AutonomousCar.class);
                                startActivity(Autonomus);
                                break;
                            case 2:
                                Intent RadioFrequencyIn5G = new Intent(getActivity(),RadioFrequencyIn5G.class);
                                startActivity(RadioFrequencyIn5G);
                                break;
                            case 3:
                                Intent NetworkSecurity = new Intent(getActivity(),NetworkSecurity.class);
                                startActivity(NetworkSecurity);
                                break;
                            case 4:
                                Intent InternetOfThings = new Intent(getActivity(),InternetOfThings.class);
                                startActivity(InternetOfThings);
                                break;
                            case 5:
                                Intent EvolutionOf5G = new Intent(getActivity(),EvolutionOf5G.class);
                                startActivity(EvolutionOf5G);
                                break;
                            case 6:
                                Intent ArtificialIntelligence = new Intent(getActivity(),ArtificialIntelligence.class);
                                startActivity(ArtificialIntelligence);
                                break;
                            case 7:
                                Intent ComputerVision = new Intent(getActivity(),ComputerVision.class);
                                startActivity(ComputerVision);
                                break;
                            case 8:
                                Intent Arduino = new Intent(getActivity(),Arduino.class);
                                startActivity(Arduino);
                                break;
                            case 9:
                                Intent Qualcomm = new Intent(getActivity(),QualcommWifi.class);
                                startActivity(Qualcomm);
                                break;
                        }
                    }
                });

                workShopAdapter.mView.findViewById(R.id.workshop_register_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(position == 8)
                        {
                            ArduinoDialogFragment arduinoDialog = new ArduinoDialogFragment();
                            arduinoDialog.setTargetFragment(WorkShopFragment.this,1);
                            arduinoDialog.show(getFragmentManager(),"Arduino_DIALOG");
                            arduinoDialog.setCancelable(false);
                        }
                        else
                        {
                            Uri uri = Uri.parse(workShopClass.getUrl());
                            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                            startActivity(intent);
                        }
                    }
                });
            }
        };

        mRecycler.setAdapter(adapter);
        return mView;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void ApplyFragmentText(int index)
    {
        switch (index)
        {
            case -1:
                ArduinoDialogFragment arduinoDialog = new ArduinoDialogFragment();
                arduinoDialog.setTargetFragment(WorkShopFragment.this,1);
                arduinoDialog.show(getFragmentManager(),"Arduino_DIALOG");
                arduinoDialog.setCancelable(false);
                Toast.makeText(getActivity(),"You have to Choose any one of them to Continue",Toast.LENGTH_SHORT).show();
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
