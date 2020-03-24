package com.echoman.bb_splash_cycle.Ui.authui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.echoman.bb_splash_cycle.Adapter.BloodSpinnerAdapter;
import com.echoman.bb_splash_cycle.Adapter.CitySpinnerAdapter;
import com.echoman.bb_splash_cycle.Adapter.GoveSpinnerAdapter;
import com.echoman.bb_splash_cycle.Helper.BaseFragment;
import com.echoman.bb_splash_cycle.Helper.GeneralRequest;
import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodData;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodType;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.echoman.bb_splash_cycle.data.api.RetrofitManger.getRetrofitClient;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.saveData;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener {


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
    @BindView(R.id.fragment_register_et_last_date_for_donation)
    EditText fragmentRegisterEtLastDateForDonation;
    @BindView(R.id.fragment_register_sp_government)
    Spinner fragmentRegisterSpGovernment;
    @BindView(R.id.fragment_register_sp_city)
    Spinner fragmentRegisterSpCity;
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
    List<BloodData> bloodTypelist;
    //   Call<BloodType> call;
    private BloodType bloodType;
    private String xx;
    List<BloodData> bloodlist;
    //   public SpinnerAdapter bloodSpinnerAdapter;
    private SpinnerAdapter bloodTypesAdapter, governmentsAdapter, citiesAdapter;
    private int bloodTypesSelectedId = 0, governmentSelectedId = 0, citiesSelectedId = 0;
    private LinearLayout lincity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        fragmentRegisterSpBloodType = view.findViewById(R.id.fragment_register_sp_blood_type);
        fragmentRegisterSpGovernment = view.findViewById(R.id.fragment_register_sp_government);
        fragmentRegisterSpCity = view.findViewById(R.id.fragment_register_sp_city);
        fragmentRegisterEtUserName = view.findViewById(R.id.fragment_register_et_user_name);
        fragmentRegisterEtEMail = view.findViewById(R.id.fragment_register_et_e_mail);
        fragmentRegisterEtDateOfBirth = view.findViewById(R.id.fragment_register_et_date_of_birth);
        fragmentRegisterEtLastDateForDonation = view.findViewById(R.id.fragment_register_et_last_date_for_donation);
        fragmentRegisterEtPhone = view.findViewById(R.id.fragment_register_et_phone);
        fragmentRegisterEtPassword = view.findViewById(R.id.fragment_register_et_password);
        fragmentRegisterEtConfirmPassword = view.findViewById(R.id.fragment_register_et_confirm_password);
        fragmentRegisterBtnSignUp = view.findViewById(R.id.fragment_register_btn_sign_up);

        lincity = view.findViewById(R.id.lin3);
        lincity.setVisibility(View.GONE);

        fragmentRegisterEtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        (DatePickerDialog.OnDateSetListener) RegisterFragment.this,
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
// If you're calling this from a support Fragment
                //     dpd.show(this, "Datepickerdialog");

                dpd.setThemeDark(true);
                dpd.setTitle("إختر يوم");
                dpd.show(getActivity().getSupportFragmentManager(), "Datepick");
            }
        });

        setUpActivity();
        getblood();
        getGover();

        return view;
    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        fragmentRegisterEtDateOfBirth.setText(date);
    }
    private void getGover() {
        GoveSpinnerAdapter goveSpinnerAdapter = new GoveSpinnerAdapter(getActivity());
        GeneralRequest.getgoverment(getActivity(), getRetrofitClient().getGoves(), fragmentRegisterSpGovernment, goveSpinnerAdapter, 0);
        fragmentRegisterSpGovernment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                governmentSelectedId = position;
                if (governmentSelectedId == 0) {
                    lincity.setVisibility(View.GONE);
                } else {
                    lincity.setVisibility(View.VISIBLE);
                    getcity(governmentSelectedId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getcity(int governmentSelectedId) {
        CitySpinnerAdapter citySpinnerAdapter = new CitySpinnerAdapter(getActivity());
        GeneralRequest.getcity(getActivity(), getRetrofitClient().getcity(governmentSelectedId), fragmentRegisterSpCity, citySpinnerAdapter);
        fragmentRegisterSpCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                citiesSelectedId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void getblood() {

        BloodSpinnerAdapter bloodSpinnerAdapter = new BloodSpinnerAdapter(getActivity());
        GeneralRequest.getBloodTypes(getActivity(), getRetrofitClient().getBloodTypes(), fragmentRegisterSpBloodType, bloodSpinnerAdapter, 0);
        fragmentRegisterSpBloodType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodTypesSelectedId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}



