package com.echoman.bb_splash_cycle.Helper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.echoman.bb_splash_cycle.Adapter.BloodSpinnerAdapter;
import com.echoman.bb_splash_cycle.Adapter.CitySpinnerAdapter;
import com.echoman.bb_splash_cycle.Adapter.GoveSpinnerAdapter;
import com.echoman.bb_splash_cycle.Adapter.SpinnerAdapter;
import com.echoman.bb_splash_cycle.R;
import com.echoman.bb_splash_cycle.Ui.authui.PassForgetFragment;
import com.echoman.bb_splash_cycle.Ui.authui.PassReset;
import com.echoman.bb_splash_cycle.Ui.mainui.MainUi;
import com.echoman.bb_splash_cycle.data.model.client.Client;
import com.echoman.bb_splash_cycle.data.model.donation.Donation;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodData;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodType;
import com.echoman.bb_splash_cycle.data.model.general.city.City;
import com.echoman.bb_splash_cycle.data.model.general.gove.Gove;
import com.echoman.bb_splash_cycle.data.model.generalResponse.GeneralResponse;
import com.echoman.bb_splash_cycle.data.model.login.resetPassword.ResetPasword;
import com.echoman.bb_splash_cycle.data.model.login.signUp.SignUp;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.echoman.bb_splash_cycle.Helper.Constant.EDIT_PROFILE;
import static com.echoman.bb_splash_cycle.Helper.Constant.LOGIN;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_APITOKEN;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_DATA;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.saveApi;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.saveData;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.saveUserData;
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.setSharedPreferences;

public class GeneralRequest {

    public static void onAuth(Call<Client> call, String phone, String password, boolean rememberMe, Activity activity
            , String actionType, String apiToken) {
        call.enqueue(new Callback<Client>() {
            public String userapiToken;

            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                try {
                    if (response.body().getStatus() == 1) {


                        setSharedPreferences(activity);

                        if (actionType.equals(LOGIN)) {
                            userapiToken = response.body().getData().getApiToken();
                            activity.startActivity(new Intent(activity, MainUi.class));
                        }
                        if (actionType.equals(EDIT_PROFILE)) {
                            response.body().getData().setApiToken(apiToken);
                        }

                        saveData(activity, USER_DATA, response.body().getData());
                        saveData(activity, REMEMBER_ME, rememberMe);
                        saveData(activity, USER_PASSWORD, password);
                        saveApi(activity, USER_APITOKEN, userapiToken);
                        saveUserData(activity,response.body().getData());

                    }

                    //  Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
            }
        });
    }

    public static void getalldonations(Activity activity, Call<Donation> call) {
        call.enqueue(new Callback<Donation>() {
            List<BloodData> bloodlist;

            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {

                if (response.body().getStatus() == 1)
                    Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {

            }

            public String userapiToken;

        });
    }

    public static void signUp(FragmentActivity activity, Call<Client> mySignUp) {
    mySignUp.enqueue(new Callback<Client>() {
        @Override
        public void onResponse(Call<Client> call, Response<Client> response) {
            try {
                if (response.body().getStatus()==1)
                {
                    Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_LONG).show();

                    setSharedPreferences(activity);

                    saveData(activity, USER_DATA, response.body().getData().getClient());
                    saveApi(activity, USER_APITOKEN, response.body().getData().getApiToken());
                    activity.startActivity(new Intent(activity, MainUi.class));



                }
                else {
                    Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_LONG).show();


                }
            }
            catch(Exception e){
                Toast.makeText(activity, e.getMessage(),Toast.LENGTH_LONG).show();
            }


        }

        @Override
        public void onFailure(Call<Client> call, Throwable t) {
            Toast.makeText(activity,"failer",Toast.LENGTH_LONG).show();

        }
    });
    }


    public static void getSpinnerData(Activity activity, final Spinner spinner, final SpinnerAdapter adapter, final String hint,
                                      Call<GeneralResponse> method, final View view, final int selectedId, final boolean show) {


        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    if (show) {
                        HelperMethod.dismissProgressDialog();
                    }

                    if (response.body().getStatus() == 1) {
                        if (view != null) {
                            view.setVisibility(View.VISIBLE);
                        }
                        adapter.setData(response.body().getData(), hint);

                        spinner.setAdapter(adapter);

                        spinner.setSelection(selectedId);


                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                if (show) {
                    HelperMethod.dismissProgressDialog();
                }

            }
        });
    }

    public static void getSpinnerData(final Activity activity, final Spinner spinner, final SpinnerAdapter adapter, final String hint
            , final Call<GeneralResponse> method, final int selectedId1, final AdapterView.OnItemSelectedListener listener) {

        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {

                    HelperMethod.dismissProgressDialog();
                    if (response.body().getStatus() == 1) {

                        adapter.setData(response.body().getData(), hint);

                        spinner.setAdapter(adapter);

                        spinner.setSelection(selectedId1);

                        spinner.setOnItemSelectedListener(listener);

                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                HelperMethod.dismissProgressDialog();
            }
        });
    }

    public static void passForget(FragmentActivity activity, Call<ResetPasword> resetPass,String phone) {
        resetPass.enqueue(new Callback<ResetPasword>() {
            @Override
            public void onResponse(Call<ResetPasword> call, Response<ResetPasword> response) {
                try {
                    if (response.body().getStatus()==1){
                        Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_LONG).show();
                        saveData(activity,"pincode",String.valueOf(response.body().getData().getPinCodeForTest()));
                        saveData(activity,"user_phone",phone);
                        Fragment fragment = new PassReset();
                        activity.getSupportFragmentManager()
                                .beginTransaction().replace(R.id.login_framlayout, fragment)
                                .addToBackStack(null).commit();

                    }
                    else {
                        Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_LONG).show();

                    }

                }
                catch (Exception e){
                    Toast.makeText(activity,e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResetPasword> call, Throwable t) {
                Toast.makeText(activity,"failure",Toast.LENGTH_LONG).show();

            }
        });
    }

    public static void newpass(FragmentActivity activity, Call<ResetPasword> resetPass) {
        resetPass.enqueue(new Callback<ResetPasword>() {
            @Override
            public void onResponse(Call<ResetPasword> call, Response<ResetPasword> response) {
                try {
                    if (response.body().getStatus()==1){
                        Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_LONG).show();
                        activity.startActivity(new Intent(activity, MainUi.class));

                   /*
                        Fragment fragment = new PassReset();
                        activity.getSupportFragmentManager()
                                .beginTransaction().replace(R.id.login_framlayout, fragment)
                                .addToBackStack(null).commit();*/

                    }
                    else {
                        Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_LONG).show();

                    }

                }
                catch (Exception e){
                    Toast.makeText(activity,e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResetPasword> call, Throwable t) {
                Toast.makeText(activity,"failure",Toast.LENGTH_LONG).show();

            }
        });
    }
}


