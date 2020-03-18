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
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.echoman.bb_splash_cycle.Helper.BaseFragment;
import com.echoman.bb_splash_cycle.Helper.GeneralRequest;
import com.echoman.bb_splash_cycle.Helper.HelperMethod;
import com.echoman.bb_splash_cycle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.echoman.bb_splash_cycle.Helper.Constant.LOGIN;
import static com.echoman.bb_splash_cycle.data.api.RetrofitManger.getRetrofitClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {

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
    boolean rememberme;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setUpActivity();

        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick({R.id.login_log_btn, R.id.login_forget_tv, R.id.login_remember_cb, R.id.constrain, R.id.login_newaccount_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_log_btn:

                String phone = loginPhone.getText().toString();
                String password = loginPass.getText().toString();

           //     if (validation(phone, password)) {
           //         onCall(phone, password);
           //     }
                onCall(phone, password);

                break;
            case R.id.login_forget_tv:
                break;
            case R.id.login_remember_cb:
                boolean checked = ((CheckBox) view).isChecked();

                // Check which checkbox was clicked
                rememberme= checked;



                break;
            case R.id.constrain:
                HelperMethod.disappearKeypad(getActivity(),view);
                break;
            case R.id.login_newaccount_tv:
                break;
        }
    }

    private boolean validation(String phone, String password) {

        boolean valid = true;

        if (phone.length() != 8) {

            valid = false;
        }

        if (password.length() < 3) {

            valid = false;
        }

        return false;
    }
    private void onCall(String phone, String password) {
        GeneralRequest.onAuth(getRetrofitClient().onLogin(phone, password), phone,password, rememberme,getActivity(), LOGIN , "");
    }

}
