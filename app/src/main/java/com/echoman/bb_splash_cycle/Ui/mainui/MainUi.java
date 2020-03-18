package com.echoman.bb_splash_cycle.Ui.mainui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.Ui.mainui.ui.home.DonationsFragment;
import com.echoman.bb_splash_cycle.Ui.mainui.ui.home.HomeFragment;
import com.echoman.bb_splash_cycle.Ui.mainui.ui.home.PostsFragment;
import com.echoman.bb_splash_cycle.Ui.mainui.ui.more.MoreFragment;
import com.echoman.bb_splash_cycle.Ui.mainui.ui.notifications.NotificationsFragment;
import com.echoman.bb_splash_cycle.Ui.mainui.ui.profile.ProfileFragment;
import com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainUi extends AppCompatActivity {
    private TabLayout tabLayout;
    // private ViewPager viewPager;
    private LinearLayout lin;
private Toolbar toolbar;
private TextView tooltv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);

        SharedPreferencesManger.setSharedPreferences(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_notifications, R.id.navigation_more)
                .build();
        FrameLayout navController = findViewById(R.id.nav_host_fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, new PostsFragment()).addToBackStack(null).commit();

        //   NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //  NavigationUI.setupWithNavController(navView, navController);

        lin = findViewById(R.id.lin1);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //   viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        //   setupViewPager(viewPager);

        //    tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                 Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new PostsFragment();

                        break;
                    case 1:
                        fragment = new DonationsFragment();


                        break;

                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }



        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        tabLayout.setVisibility(View.VISIBLE);
                        fragment = new PostsFragment();

                        break;
                    case R.id.navigation_profile:
                        tabLayout.setVisibility(View.GONE);
                        fragment = new ProfileFragment();

                        break;
                    case R.id.navigation_more:
                        fragment = new MoreFragment();
                        tabLayout.setVisibility(View.GONE);
                        break;

                    case R.id.navigation_notifications:
                        fragment = new NotificationsFragment();
                        tabLayout.setVisibility(View.GONE);


                        break;
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(null)
                            .replace(R.id.nav_host_fragment, fragment).commit();
                    return true;
                }

                return false;
            }

        };


        private void setupViewPager (ViewPager viewPager){
            MainUi.ViewPagerAdapter adapter = new MainUi.ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFrag(new PostsFragment(), "posts");
            adapter.addFrag(new DonationsFragment(), "Donations");
            viewPager.setAdapter(adapter);
        }


        class ViewPagerAdapter extends FragmentPagerAdapter {
            private final List<Fragment> mFragmentList = new ArrayList<>();
            private final List<String> mFragmentTitleList = new ArrayList<>();

            public ViewPagerAdapter(FragmentManager manager) {
                super(manager);
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            public void addFrag(Fragment fragment, String title) {
                mFragmentList.add(fragment);
                mFragmentTitleList.add(title);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentTitleList.get(position);
            }
        }
    }
