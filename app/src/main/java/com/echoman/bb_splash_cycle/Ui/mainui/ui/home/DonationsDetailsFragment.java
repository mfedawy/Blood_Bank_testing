package com.echoman.bb_splash_cycle.Ui.mainui.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.echoman.bb_splash_cycle.Adapter.DonationAdapter;
import com.echoman.bb_splash_cycle.Adapter.SpinnerAdapter;
import com.echoman.bb_splash_cycle.Helper.OnEndLess;
import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.model.donation.Donation;
import com.echoman.bb_splash_cycle.data.model.donation.DonationData;
import com.echoman.bb_splash_cycle.data.model.donation.donationDetail.DonationDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.echoman.bb_splash_cycle.Helper.GeneralRequest.getSpinnerData;
import static com.echoman.bb_splash_cycle.data.api.RetrofitManger.getRetrofitClient;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_APITOKEN;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.loadData;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationsDetailsFragment extends Fragment {
    String api;
public int donationId;
private String name,age,blood_type,count,hospital,address,phone,notes;
private TextView name_tv,age_tv,blood_type_tv,count_tv,hospital_tv,address_tv,phone_tv,notes_tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_details, container, false);
        Toast.makeText(getContext(),String.valueOf(donationId),Toast.LENGTH_LONG).show();
        name_tv=view.findViewById(R.id.donation_details_name);
        age_tv=view.findViewById(R.id.donation_details_age);
        blood_type_tv=view.findViewById(R.id.donation_details_blood_type);
        count_tv=view.findViewById(R.id.donation_details_count);
        hospital_tv=view.findViewById(R.id.donation_details_hospital);
        address_tv=view.findViewById(R.id.donation_details_address);
        phone_tv=view.findViewById(R.id.donation_details_phone);
        notes_tv=view.findViewById(R.id.donation_details_notes);

     api = loadData(getActivity(), USER_APITOKEN);

getData(api,donationId);

        return view;
    }

    private void getData(String api, int donationId) {

        getRetrofitClient().getadonationsdetails(api, donationId).enqueue(new Callback<DonationDetail>() {
            @Override
            public void onResponse(Call<DonationDetail> call, Response<DonationDetail> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                       name=response.body().getData().getPatientName();
                       age=response.body().getData().getPatientAge();
                    blood_type=response.body().getData().getBloodType().getName();
                    hospital=response.body().getData().getHospitalName();
                    address=response.body().getData().getHospitalAddress();
                    phone=response.body().getData().getPhone();
                    notes=response.body().getData().getNotes();
                    count=response.body().getData().getCity().getName();

                    name_tv.setText(name);
                        age_tv.setText(age);
                        blood_type_tv.setText(blood_type);
                        hospital_tv.setText(hospital);
                        address_tv.setText(address);
                        phone_tv.setText(phone);
                        notes_tv.setText(notes);
                        count_tv.setText(count);

                    } else {
                        Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("donationdetails", "errrrrror: "+ e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<DonationDetail> call, Throwable t) {

            }
        });

    }

}
