package com.echoman.bb_splash_cycle.Ui.splashui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.echoman.bb_splash_cycle.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {

    @BindView(R.id.splash_upframe)
    ImageView splashUpframe;
    @BindView(R.id.splash_downfram)
    ImageView splashDownfram;
    @BindView(R.id.splash_logo)
    ImageView splashLogo;
    @BindView(R.id.splash_title)
    TextView splashTitle;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        getActivity().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new WelcomeFragment();
                activity.getSupportFragmentManager()
                        .beginTransaction().replace(R.id.framelayout, myFragment)
                        .addToBackStack(null).commit();
                //HelperMethod.replaceFragment(getChildFragmentManager(),R.id.framelayout,myFragment);
            }
        }, SPLASH_TIME_OUT);


        return view;
    }
}
