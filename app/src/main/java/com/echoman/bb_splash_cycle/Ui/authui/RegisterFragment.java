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
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.echoman.bb_splash_cycle.Adapter.SpinnerAdapter;
import com.echoman.bb_splash_cycle.Helper.BaseFragment;
import com.echoman.bb_splash_cycle.Helper.DateTxt;
import com.echoman.bb_splash_cycle.Helper.GeneralRequest;
import com.echoman.bb_splash_cycle.Helper.HelperMethod;
import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.data.model.client.Client;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodData;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodType;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

import static com.echoman.bb_splash_cycle.Helper.GeneralRequest.getSpinnerData;
import static com.echoman.bb_splash_cycle.data.api.RetrofitManger.getRetrofitClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {


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
    @BindView(R.id.blood_lin)
    LinearLayout bloodLin;
    @BindView(R.id.lin2)
    LinearLayout lin2;
    @BindView(R.id.lin3)
    LinearLayout lin3;
    //   Call<BloodType> call;
    private BloodType bloodType;
    private String xx;
    List<BloodData> bloodlist;
    //   public SpinnerAdapter bloodSpinnerAdapter;
    private SpinnerAdapter bloodTypesAdapter, governmentsAdapter, citiesAdapter;
    private int bloodTypesSelectedId = 0, governmentSelectedId = 0, citiesSelectedId = 0;
    private LinearLayout lincity;
    private int mydate = 0;
    private String name, email, birthdate, donationdate, phone, password, passconfirm;
    private DateTxt mydonationdate, birthdayDate;
    private AdapterView.OnItemSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        ButterKnife.bind(this, view);
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
        setUpActivity();
        setSpinner();
        // getblood();
        //  getGover();
        fragmentRegisterBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        return view;
    }

    private void signUp() {
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

        if (name != null && email != null && birthdate != null && donationdate != null
                && phone != null && password != null && bloodTypesSelectedId != 0
                && governmentSelectedId != 0 && citiesSelectedId != 0 && password.equals(passconfirm)) {
            //signupData();
         //   Toast.makeText(getContext(),name+"/"+email+"/"+birthdate+"/"+donationdate+"/"+phone+"/"+
          //        password+"/"+passconfirm+"/"+bloodTypesSelectedId+"/"+governmentSelectedId+"/"+citiesSelectedId,Toast.LENGTH_LONG).show();
            GeneralRequest.signUp(getActivity(), getRetrofitClient().onSignUp(name, email, birthdate, citiesSelectedId, phone, donationdate, password, passconfirm, bloodTypesSelectedId));
           //clientCall = getRetrofitClient().onSignUp(name, email, birthdate,citiesSelectedId, phone, donationdate, password, passconfirm, bloodTypesSelectedId);

        } else {
            if (name == null) {
                Toast.makeText(getContext(), "من فضلك أدخل الإسم ", Toast.LENGTH_SHORT).show();
            }
            if (email == null) {
                Toast.makeText(getContext(), "من فضلك أدخل الإيميل ", Toast.LENGTH_SHORT).show();
            }
            if (birthdate == null) {
                Toast.makeText(getContext(), "من فضلك أدخل تاريخ الميلاد ", Toast.LENGTH_SHORT).show();
            }
            if (bloodTypesSelectedId == 0) {
                Toast.makeText(getContext(), "من فضلك أدخل فصيلة الدم ", Toast.LENGTH_SHORT).show();
            }
            if (donationdate == null) {
                Toast.makeText(getContext(), "من فضلك أدخل تاريخ أخر تبرع ", Toast.LENGTH_SHORT).show();
            }
            if (governmentSelectedId == 0) {
                Toast.makeText(getContext(), "من فضلك أدخل المحافظة ", Toast.LENGTH_SHORT).show();
            }
            if (citiesSelectedId == 0) {
                Toast.makeText(getContext(), "من فضلك أدخل المدينة ", Toast.LENGTH_SHORT).show();
            }
            if (phone == null) {
                Toast.makeText(getContext(), "من فضلك أدخل رقم الهاتف ", Toast.LENGTH_SHORT).show();
            }
            if (password == null) {
                Toast.makeText(getContext(), "من فضلك أدخل رقم السر ", Toast.LENGTH_SHORT).show();
            }
            if (passconfirm == null) {
                Toast.makeText(getContext(), "من فضلك أدخل تأكيد رقم السر ", Toast.LENGTH_SHORT).show();
            }
            if (!password.equals(passconfirm)) {
                Toast.makeText(getContext(), " كلمة السر ليست متطابقة", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private void signupData() {
        // GeneralRequest.signUp(getActivity(), getRetrofitClient().mySignUp(name, email, birthdate, cityid, phone, donationdate, password, passconfirm, bloodid));


    }
/*
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
        citiesAdapter = new SpinnerAdapter(getActivity());
        GeneralRequest.getcity(getActivity(), getRetrofitClient().getcity(governmentSelectedId), fragmentRegisterSpCity, citiesAdapter);

    }


    private void getblood() {

        bloodTypesAdapter = new SpinnerAdapter(getActivity());
        GeneralRequest.getBloodTypes(getActivity(), getRetrofitClient().getBloodTypes(), fragmentRegisterSpBloodType, bloodTypesAdapter, 0);
        fragmentRegisterSpBloodType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodTypesSelectedId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }*/


    private void setDates() {
        DecimalFormat mFormat = new DecimalFormat("00");
        Calendar calander = Calendar.getInstance();
        String cDay = mFormat.format(Double.valueOf(String.valueOf(calander.get(Calendar.DAY_OF_MONTH))));
        String cMonth = mFormat.format(Double.valueOf(String.valueOf(calander.get(Calendar.MONTH + 1))));
        String cYear = String.valueOf(calander.get(Calendar.YEAR));
        mydonationdate = new DateTxt(cDay, cMonth, cYear, cDay + "-" + cMonth + "-" + cYear);
        birthdayDate = new DateTxt("01", "01", "1990", "01-01-1990");

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



