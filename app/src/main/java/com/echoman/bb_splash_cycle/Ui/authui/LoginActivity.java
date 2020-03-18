package com.echoman.bb_splash_cycle.Ui.authui;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.echoman.bb_splash_cycle.Helper.BaseActivity;
import com.echoman.bb_splash_cycle.Helper.HelperMethod;
import com.echoman.bb_splash_cycle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {


    //@BindView(R.id.framlayout)
    FrameLayout framlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        framlayout = findViewById(R.id.login_framlayout);
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.login_framlayout, loginFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}
