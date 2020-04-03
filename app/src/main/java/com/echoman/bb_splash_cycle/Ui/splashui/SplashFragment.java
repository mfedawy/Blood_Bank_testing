package com.echoman.bb_splash_cycle.Ui.splashui;

import android.content.Intent;
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
import com.echoman.bb_splash_cycle.Ui.authui.LoginActivity;
import com.echoman.bb_splash_cycle.Ui.mainui.MainUi;

import butterknife.BindView;

import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_DATA;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.loadBoolean;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.loadData;

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

                //HelperMethod.replaceFragment(getChildFragmentManager(),R.id.framelayout,myFragment);

                if(loadData(getActivity(),USER_DATA)!=null){
                    startActivity(new Intent(getActivity(), MainUi.class));
                }
                else {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new WelcomeFragment();
                    activity.getSupportFragmentManager()
                            .beginTransaction().replace(R.id.framelayout, myFragment)
                            .addToBackStack(null).commit();


                }




            }
        }, SPLASH_TIME_OUT);


        return view;
    }

}
