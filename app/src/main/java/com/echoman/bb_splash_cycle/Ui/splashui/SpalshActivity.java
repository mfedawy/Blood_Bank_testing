package com.echoman.bb_splash_cycle.Ui.splashui;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.echoman.bb_splash_cycle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpalshActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 3000;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spalsh);
        ButterKnife.bind(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

// Create new fragment and transaction
        Fragment newFragment = new SplashFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.framelayout, newFragment);
        transaction.addToBackStack(null);
// Commit the transaction
        transaction.commit();
       // HelperMethod.replaceFragment(getSupportFragmentManager(),0,newFragment);
    }


}


