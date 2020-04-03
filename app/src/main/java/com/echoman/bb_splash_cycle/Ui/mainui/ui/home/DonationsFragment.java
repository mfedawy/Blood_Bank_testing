package com.echoman.bb_splash_cycle.Ui.mainui.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.echoman.bb_splash_cycle.Adapter.DonationAdapter;
import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.api.RetrofitManger;
import com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger;
import com.echoman.bb_splash_cycle.data.model.donation.Donation;
import com.echoman.bb_splash_cycle.data.model.donation.DonationData;
import com.echoman.bb_splash_cycle.data.api.RetrofitManger.*;

import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_APITOKEN;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_DATA;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.loadData;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.loadUserData;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.saveApi;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.saveData;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.saveUserData;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationsFragment extends Fragment {

    SharedPreferences prefs;
    @BindView(R.id.don_recycl)
    RecyclerView donRecycl;
    private LinearLayoutManager linearLayoutManager;
    private DonationAdapter donationAdapter;
    private List<DonationData> donationList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donations, container, false);
        ButterKnife.bind(this, view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        donRecycl.setLayoutManager(linearLayoutManager);
        donationAdapter = new DonationAdapter(getContext(), donationList);
        donRecycl.setAdapter(donationAdapter);
getDonations(1);
        return view;
    }

    private void getDonations(int page) {
         String  api = loadData(getActivity(),USER_APITOKEN);
        Toast.makeText(getContext(), api, Toast.LENGTH_LONG).show();

        RetrofitManger.getRetrofitClient().getalldonations(api,page).enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {
                try {
                    if (response.body().getStatus()==1) {
                        Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                        donationList.addAll(response.body().getData().getData());
                        donationAdapter.notifyDataSetChanged();
                    }
else {
                        Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                    }
                }
                catch (Exception e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {

            }
        });
    }

    private void onCall() {
   //     String api = prefs.getString(SharedPreferencesManger.USER_APITOKEN, "No name defined");//"No name defined" is the default value.

      //  Toast.makeText(getContext(), api, Toast.LENGTH_LONG).show();
//        GeneralRequest.getalldonations(getActivity(),getRetrofitClient().getdonations("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl") );
    }
}
