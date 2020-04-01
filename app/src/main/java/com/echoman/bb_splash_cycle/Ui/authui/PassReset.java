package com.echoman.bb_splash_cycle.Ui.authui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.load.model.ModelLoader;
import com.echoman.bb_splash_cycle.Helper.BaseFragment;
import com.echoman.bb_splash_cycle.Helper.GeneralRequest;
import com.echoman.bb_splash_cycle.R;

import butterknife.ButterKnife;

import static com.echoman.bb_splash_cycle.data.api.RetrofitManger.getRetrofitClient;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.loadData;

public class PassReset extends BaseFragment {

Button change;
EditText pin,pass,pass_confirm;
String pin_code,new_pass,new_pass_confirm,pincodefortest,phone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pass_reset, container, false);
        setUpActivity();

change = view.findViewById(R.id.change__btn);
pin= view.findViewById(R.id.pin_code);
        pass= view.findViewById(R.id.new_pass);
        pass_confirm= view.findViewById(R.id.new_pass_conf);

        pincodefortest= loadData(getActivity(),"pincode");
        Toast.makeText(getContext(),pincodefortest,Toast.LENGTH_LONG).show();

change.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        changepass();

    }
});

        return view;
    }

    private void changepass() {
        pin_code= pin.getText().toString();
        new_pass= pass.getText().toString();
        new_pass_confirm= pass_confirm.getText().toString();
        phone= loadData(getActivity(),"user_phone");
        if(new_pass.equals(new_pass_confirm) && pin_code.equals(pincodefortest)){

GeneralRequest.newpass(getActivity(),getRetrofitClient().newPass(new_pass,new_pass_confirm,pin_code,phone));
        }



     //   GeneralRequest.passForget(getActivity(),getRetrofitClient().resetPass(phone_forget));

    }

    private void phonesend() {

    }
}
