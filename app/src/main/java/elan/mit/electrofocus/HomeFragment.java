package elan.mit.electrofocus;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import elan.mit.electrofocus.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment
{

    private DatabaseReference mHomePage;
    private RecyclerView mHomeRecycler;
    private FirebaseRecyclerAdapter<HomePageClass,HomePageHolder> firebaseRecyclerAdapter;
    private ShimmerFrameLayout shimmerFrameLayout;

    public HomeFragment() {
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View mview = inflater.inflate(R.layout.fragment_home, container, false);
        mHomePage = FirebaseDatabase.getInstance().getReference().child("HomePage");

        mHomePage.keepSynced(true);

        mHomeRecycler = mview.findViewById(R.id.home_recycler);
        mHomeRecycler.setHasFixedSize(true);

        final SwipeRefreshLayout swipeRefreshLayout = mview.findViewById(R.id.swipe_refresh_id);

        shimmerFrameLayout = mview.findViewById(R.id.shimmerContainer);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mHomeRecycler.setLayoutManager(mLayoutManager);
        Log.d("SHIMMER_WORKING","prefs:"+restorePrefData());
        if(isNetworkAvailable())
        {
            if(!restorePrefData())
            {
                shimmerFrameLayout.setVisibility(View.GONE);
            }
            if(restorePrefData())
            {
                shimmerFrameLayout.startShimmer();
                new MyCountDown(shimmerFrameLayout,getContext(),4000,1000);
                savePrefsData();
            }
        }
        else
        {
            if(!restorePrefData())
            {
                shimmerFrameLayout.setVisibility(View.GONE);
                Toast.makeText(getActivity(),"Please Turn on your Internet Connection and refresh the page to get new posts",Toast.LENGTH_LONG).show();
            }
            if(restorePrefData())
            {
                shimmerFrameLayout.setVisibility(View.GONE);
                Toast.makeText(getActivity(),"Please Turn on your Internet Connection and refresh the page",Toast.LENGTH_LONG).show();
            }
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isNetworkAvailable())
                {
                    if(!restorePrefDataRefresh())
                    {
                        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<HomePageClass, HomePageHolder>(
                                HomePageClass.class,
                                R.layout.home_page_layout,
                                HomePageHolder.class,
                                mHomePage){
                            @Override
                            protected void populateViewHolder(HomePageHolder homePageHolder, HomePageClass homePageClass, int position)
                            {
                                homePageHolder.setImage(homePageClass.getLink(),getContext());
                                homePageHolder.setStory(homePageClass.getStory());
                                homePageHolder.setCaption(homePageClass.getCaption());
                                homePageHolder.setHomeTime(homePageClass.getTime());
                                homePageHolder.setHomeDate(homePageClass.getDate());
                            }
                        };
                        mHomeRecycler.setAdapter(firebaseRecyclerAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    if(restorePrefDataRefresh())
                    {
                        shimmerFrameLayout.setVisibility(View.VISIBLE);
                        shimmerFrameLayout.startShimmer();
                        new MyCountDown(shimmerFrameLayout,getContext(),6000,1000);
                        savePrefsDataRefresh();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                }
                else
                {
                    Toast.makeText(getActivity(),"Please Check Your Internet Connection",Toast.LENGTH_LONG).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        return mview;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("SEQUENCE_CHECK","onStart");
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<HomePageClass, HomePageHolder>(
                HomePageClass.class,
                R.layout.home_page_layout,
                HomePageHolder.class,
                mHomePage){
            @Override
            protected void populateViewHolder(HomePageHolder homePageHolder, HomePageClass homePageClass, int position)
            {
                homePageHolder.setImage(homePageClass.getLink(),getContext());
                homePageHolder.setStory(homePageClass.getStory());
                homePageHolder.setCaption(homePageClass.getCaption());
                homePageHolder.setHomeTime(homePageClass.getTime());
                homePageHolder.setHomeDate(homePageClass.getDate());
            }
        };
        mHomeRecycler.setAdapter(firebaseRecyclerAdapter);
    }

    private boolean restorePrefData()
    {
        SharedPreferences pref = getContext().getSharedPreferences("myShimmerPrefs",MODE_PRIVATE);
        boolean isIntroActivityOpenedBefore = pref.getBoolean("IsShimmerEffectNeeded",true);
        return  isIntroActivityOpenedBefore;
    }

    private void savePrefsData()
    {
        SharedPreferences pref = getContext().getSharedPreferences("myShimmerPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("IsShimmerEffectNeeded",false);
        editor.commit();
    }

    private boolean restorePrefDataRefresh()
    {
        SharedPreferences pref = getContext().getSharedPreferences("myShimmerPrefs",MODE_PRIVATE);
        boolean isIntroActivityOpenedBefore = pref.getBoolean("IsShimmerEffectNeeded",true);
        return  isIntroActivityOpenedBefore;
    }

    private void savePrefsDataRefresh()
    {
        SharedPreferences pref = getContext().getSharedPreferences("myShimmerPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("IsShimmerEffectNeeded",false);
        editor.commit();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
