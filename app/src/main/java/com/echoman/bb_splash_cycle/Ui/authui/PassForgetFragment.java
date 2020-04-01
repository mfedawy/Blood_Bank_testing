package com.echoman.bb_splash_cycle.Ui.authui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.echoman.bb_splash_cycle.Helper.BaseFragment;
import com.echoman.bb_splash_cycle.Helper.GeneralRequest;
import com.echoman.bb_splash_cycle.R;

import butterknife.ButterKnife;

import static com.echoman.bb_splash_cycle.data.api.RetrofitManger.getRetrofitClient;

public class PassForgetFragment extends BaseFragment {

private Button send;
private EditText phone;
private String phone_forget;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pass_forget, container, false);
        setUpActivity();

        ButterKnife.bind(this, view);
send = view.findViewById(R.id.send__btn);
phone= view.findViewById(R.id.passforget_phone);
send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        phonesend();

    }
});

        return view;
    }

    private void phonesend() {
        phone_forget= phone.getText().toString();

        GeneralRequest.passForget(getActivity(),getRetrofitClient().resetPass(phone_forget),phone_forget);

    }
}
