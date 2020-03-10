package com.echoman.bb_splash_cycle.Ui.authui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.echoman.bb_splash_cycle.Helper.HelperMethod;
import com.echoman.bb_splash_cycle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_pass)
    EditText loginPass;
    @BindView(R.id.login_log_btn)
    Button loginLogBtn;
    @BindView(R.id.login_forget_tv)
    TextView loginForgetTv;
    @BindView(R.id.login_remember_cb)
    CheckBox loginRememberCb;
    @BindView(R.id.constrain)
    ConstraintLayout constrain;
    @BindView(R.id.login_newaccount_tv)
    TextView loginNewaccountTv;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick({R.id.login_log_btn, R.id.login_forget_tv, R.id.login_remember_cb, R.id.constrain, R.id.login_newaccount_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_log_btn:
                break;
            case R.id.login_forget_tv:
                break;
            case R.id.login_remember_cb:
                break;
            case R.id.constrain:
                HelperMethod.disappearKeypad(getActivity(),view);
                break;
            case R.id.login_newaccount_tv:
                break;
        }
    }
}
