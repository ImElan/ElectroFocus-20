package elan.mit.electrofocus;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import elan.mit.electrofocus.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private BottomNavigationView mBottomNavigation;
    private HomeFragment mHome;
    private EventsFragment mEvents;
    private WorkShopFragment mWorkshops;
    private NavigationView mNavigation;
    private int current_page;
    private int RestoringId = R.id.home_id;
    private String check;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TOOLBAR
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // DRAWER TOGGLE
        mDrawer = findViewById(R.id.navigation_drawer_layout_id);
        mDrawerToggle = setupDrawerToggle();
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
        mDrawer.addDrawerListener(mDrawerToggle);


        // INSTANTIATION
        FrameLayout mMainFrame = findViewById(R.id.mMainFrameLayout);
        mBottomNavigation = findViewById(R.id.main_bottom_navigation);
        mNavigation = findViewById(R.id.navigation_drawer_id);
        setupDrawerContent(mNavigation);

        // FRAGMENTS
        mHome = new HomeFragment();
        mEvents = new EventsFragment();
        mWorkshops = new WorkShopFragment();
        mBottomNavigation.setSelectedItemId(R.id.home_id);

        //FireBase

        getSupportActionBar().setTitle("Home");

        check = getIntent().getStringExtra("fromRegistration");

        SetFragment(mHome);

        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.home_id:
                        getSupportActionBar().setTitle("Home");
                        SetFragment(mHome);
                        current_page=1;
                        return true;
                    case R.id.events_id:
                        getSupportActionBar().setTitle("Events");
                        SetFragment(mEvents);
                        current_page=0;
                        return true;
                    case R.id.workshop_id:
                        getSupportActionBar().setTitle("WorkShops");
                        SetFragment(mWorkshops);
                        current_page=0;
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START))
        {
            mDrawer.closeDrawer(GravityCompat.START);
        }
        else if(current_page==0)
        {
            SetFragment(mHome);
            mBottomNavigation.setSelectedItemId(R.id.home_id);
            current_page=1;
        }
        else
        {
            super.onBackPressed();
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open,  R.string.drawer_close);
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass = HomeFragment.class;
        switch(menuItem.getItemId()) {
            case R.id.events_id:
                getSupportActionBar().setTitle("Events");
                mBottomNavigation.setSelectedItemId(R.id.events_id);
                fragmentClass = EventsFragment.class;
                current_page=0;
                break;

            case R.id.workshop_id:
                getSupportActionBar().setTitle("WorkShops");
                mBottomNavigation.setSelectedItemId(R.id.workshop_id);
                fragmentClass = WorkShopFragment.class;
                current_page=0;
                break;

            case R.id.register_id:
                Intent RegisterIntent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(RegisterIntent);
                break;

            case R.id.sponsor_id:
                Intent SponsorIntent = new Intent(MainActivity.this,Sponsors.class);
                startActivity(SponsorIntent);
                break;

            case R.id.accommodation_id:
                AccommodationDialog accommodationDialog = new AccommodationDialog();
                accommodationDialog.show(getSupportFragmentManager(),"Arduino_DIALOG");
                mBottomNavigation.setSelectedItemId(R.id.home_id);
                break;

            case R.id.about_id:
                Intent AboutIntent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(AboutIntent);
                break;

            case R.id.feedback_id:
                Intent FeedbackIntent = new Intent(MainActivity.this,FeedbackActivity.class);
                startActivity(FeedbackIntent);
                break;

            case R.id.whatsapp:
                Uri uri = Uri.parse("smsto:" + "7402465393");
                Intent WhatsApp = new Intent(Intent.ACTION_SENDTO,uri);
                WhatsApp.setPackage("com.whatsapp");
                startActivity(WhatsApp);
                break;

            case R.id.message:
                Uri uri_1 = Uri.parse("smsto:" + "7402465393");
                Intent MessagesApp = new Intent(Intent.ACTION_SENDTO,uri_1);
                startActivity(MessagesApp);
                break;

            case R.id.phone:
                Uri uri_2 = Uri.parse("tel:" + Uri.encode("7402465393"));
                Intent Phone = new Intent(Intent.ACTION_DIAL,uri_2);
                startActivity(Phone);
                break;

            default:
                getSupportActionBar().setTitle("Home");
                mBottomNavigation.setSelectedItemId(R.id.home_id);
                current_page=1;
                fragmentClass = HomeFragment.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mMainFrameLayout, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            mDrawer.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void SetFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mMainFrameLayout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        check = null;
        RestoringId = mBottomNavigation.getSelectedItemId();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(check == null)
        {
            mBottomNavigation.setSelectedItemId(RestoringId);
        }
        else
        {
            mBottomNavigation.setSelectedItemId(R.id.workshop_id);
        }
    }
}





