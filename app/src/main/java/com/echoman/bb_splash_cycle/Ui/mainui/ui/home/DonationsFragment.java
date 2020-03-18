package com.echoman.bb_splash_cycle.Ui.mainui.ui.home;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.echoman.bb_splash_cycle.Helper.GeneralRequest;
import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger;

import static android.content.Context.MODE_PRIVATE;
import static com.echoman.bb_splash_cycle.Helper.Constant.LOGIN;
import static com.echoman.bb_splash_cycle.data.api.RetrofitManger.getRetrofitClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationsFragment extends Fragment {

    SharedPreferences prefs ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donations, container, false);


    }
    private void onCall() {
        String api = prefs.getString(SharedPreferencesManger.USER_APITOKEN, "No name defined");//"No name defined" is the default value.

        Toast.makeText(getContext(),api,Toast.LENGTH_LONG).show();
        GeneralRequest.getalldonations(getActivity(),getRetrofitClient().getdonations("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl") );
    }
}
