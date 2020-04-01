package com.echoman.bb_splash_cycle.Ui.mainui.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.echoman.bb_splash_cycle.Adapter.SpinnerAdapter;
import com.echoman.bb_splash_cycle.Helper.BaseFragment;
import com.echoman.bb_splash_cycle.Helper.DateTxt;
import com.echoman.bb_splash_cycle.Helper.HelperMethod;
import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.*;
import com.echoman.bb_splash_cycle.data.model.client.Client;
import com.echoman.bb_splash_cycle.data.model.client.ClientFullData;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.echoman.bb_splash_cycle.Helper.GeneralRequest.getSpinnerData;
import static com.echoman.bb_splash_cycle.data.api.RetrofitManger.getRetrofitClient;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_APITOKEN;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_DATA;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.loadData;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.loadUserData;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.saveApi;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.saveData;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.saveUserData;


public class ProfileFragment extends BaseFragment {


    @BindView(R.id.fragment_register_tv_crate_a_new_account)
    TextView fragmentRegisterTvCrateANewAccount;
    @BindView(R.id.fragment_register_et_user_name)
    EditText fragmentRegisterEtUserName;
    @BindView(R.id.fragment_register_et_e_mail)
    EditText fragmentRegisterEtEMail;
    @BindView(R.id.fragment_register_et_date_of_birth)
    EditText fragmentRegisterEtDateOfBirth;
    @BindView(R.id.fragment_register_sp_blood_type)
    Spinner fragmentRegisterSpBloodType;
    @BindView(R.id.blood_lin)
    LinearLayout bloodLin;
    @BindView(R.id.fragment_register_et_last_date_for_donation)
    EditText fragmentRegisterEtLastDateForDonation;
    @BindView(R.id.fragment_register_sp_government)
    Spinner fragmentRegisterSpGovernment;
    @BindView(R.id.lin2)
    LinearLayout lin2;
    @BindView(R.id.fragment_register_sp_city)
    Spinner fragmentRegisterSpCity;
    @BindView(R.id.lin3)
    LinearLayout lin3;
    @BindView(R.id.fragment_register_et_phone)
    EditText fragmentRegisterEtPhone;
    @BindView(R.id.fragment_register_et_password)
    EditText fragmentRegisterEtPassword;
    @BindView(R.id.fragment_register_et_confirm_password)
    EditText fragmentRegisterEtConfirmPassword;
    @BindView(R.id.fragment_register_btn_sign_up)
    Button fragmentRegisterBtnSignUp;
    @BindView(R.id.fragment_register_cl_sub_view)
    ConstraintLayout fragmentRegisterClSubView;
    private DateTxt mydonationdate, birthdayDate;
    private SpinnerAdapter bloodTypesAdapter, governmentsAdapter, citiesAdapter;
    private int bloodTypesSelectedId = 0, governmentSelectedId = 0, citiesSelectedId = 0;
    private AdapterView.OnItemSelectedListener listener;
    private String name, email, birthdate, donationdate, phone, password, passconfirm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,root);
       getProfile();
        fragmentRegisterEtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelperMethod.showCalender(getContext(), "تاريخ الميلاد", fragmentRegisterEtDateOfBirth, birthdayDate);
            }
        });
        fragmentRegisterEtLastDateForDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelperMethod.showCalender(getContext(), "تاريخ التبرع", fragmentRegisterEtLastDateForDonation, mydonationdate);
                //  mydate = 2;
            }
        });
        setDates();
//        setUpActivity();
        setSpinner();


        return root;
    }

    private void setDates() {
        DecimalFormat mFormat = new DecimalFormat("00");
        Calendar calander = Calendar.getInstance();
        String cDay = mFormat.format(Double.valueOf(String.valueOf(calander.get(Calendar.DAY_OF_MONTH))));
        String cMonth = mFormat.format(Double.valueOf(String.valueOf(calander.get(Calendar.MONTH + 1))));
        String cYear = String.valueOf(calander.get(Calendar.YEAR));
        mydonationdate = new DateTxt(cDay, cMonth, cYear, cDay + "-" + cMonth + "-" + cYear);
        birthdayDate = new DateTxt("01", "01", "1990", "01-01-1990");

    }

    private void getProfile() {
        ClientFullData myclient=loadUserData(getActivity()).getClient();
        setData(myclient);

    }

    private void setData(ClientFullData client) {
        fragmentRegisterEtUserName.setText(client.getName());
        fragmentRegisterEtEMail.setText(client.getEmail());
        fragmentRegisterEtDateOfBirth.setText(client.getBirthDate());
        fragmentRegisterEtLastDateForDonation.setText(client.getDonationLastDate());
        fragmentRegisterEtPhone.setText(client.getPhone());
        bloodTypesSelectedId=client.getBloodType().getId();
        citiesSelectedId=client.getCity().getId();
        governmentSelectedId=client.getCity().getGovernorate().getId();
        fragmentRegisterEtPassword.setText(loadData(getActivity(),USER_PASSWORD));
        fragmentRegisterEtConfirmPassword.setText(loadData(getActivity(),USER_PASSWORD));

    }

    @OnClick({R.id.fragment_register_et_date_of_birth, R.id.fragment_register_et_last_date_for_donation, R.id.fragment_register_btn_sign_up, R.id.fragment_register_cl_sub_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_register_et_date_of_birth:
                break;
            case R.id.fragment_register_et_last_date_for_donation:
                break;
            case R.id.fragment_register_btn_sign_up:
                updatedata();
                break;
            case R.id.fragment_register_cl_sub_view:
                break;
        }
    }

    private void updatedata() {
        name = fragmentRegisterEtUserName.getText().toString();
        email = fragmentRegisterEtEMail.getText().toString();
        birthdate = fragmentRegisterEtDateOfBirth.getText().toString();
        donationdate = fragmentRegisterEtLastDateForDonation.getText().toString();
        phone = fragmentRegisterEtPhone.getText().toString();
        password = fragmentRegisterEtPassword.getText().toString();
        passconfirm = fragmentRegisterEtConfirmPassword.getText().toString();
        citiesSelectedId = citiesAdapter.selectedId;
        governmentSelectedId=governmentsAdapter.selectedId;
        bloodTypesSelectedId=bloodTypesAdapter.selectedId;
        String apiToken=loadUserData(getActivity()).getApiToken();

        if (name != null && email != null && birthdate != null && donationdate != null
                && phone != null && password != null && bloodTypesSelectedId != 0
                && governmentSelectedId != 0 && citiesSelectedId != 0 && password.equals(passconfirm)) {
            getRetrofitClient().updateuserdata(name, email, birthdate, citiesSelectedId, phone, donationdate, password, passconfirm, bloodTypesSelectedId, apiToken).enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    try {
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(getContext(), "if " + response.body().getMsg(), Toast.LENGTH_LONG).show();

                            saveData(getActivity(), USER_DATA, response.body().getData());
                            saveData(getActivity(), USER_PASSWORD, password);
                            saveUserData(getActivity(),response.body().getData());
                            Objects.requireNonNull(getActivity()).recreate();

                        } else {
                            Toast.makeText(getContext(), "else " + response.body().getMsg(), Toast.LENGTH_LONG).show();


                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "e " + e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {

                }
            });
        }
    }


    private void setSpinner() {
        bloodTypesAdapter = new SpinnerAdapter(getActivity());
        governmentsAdapter = new SpinnerAdapter(getActivity());
        citiesAdapter = new SpinnerAdapter(getActivity());
        getSpinnerData(getActivity(), fragmentRegisterSpBloodType, bloodTypesAdapter, getString(R.string.select_blood_type),
                getRetrofitClient().getBloodTypes(), null, bloodTypesSelectedId, true);

        listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    getSpinnerData(getActivity(), fragmentRegisterSpCity, citiesAdapter, getString(R.string.select_city)
                            , getRetrofitClient().getcity(governmentsAdapter.selectedId), lin3, citiesSelectedId, true);
                } else {
                    lin3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        getSpinnerData(getActivity(), fragmentRegisterSpGovernment, governmentsAdapter, getString(R.string.select_government),
                getRetrofitClient().getGoves(), governmentSelectedId, listener);
    }
}
