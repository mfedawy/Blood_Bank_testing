package com.echoman.bb_splash_cycle.Helper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.echoman.bb_splash_cycle.Adapter.BloodSpinnerAdapter;
import com.echoman.bb_splash_cycle.Adapter.CitySpinnerAdapter;
import com.echoman.bb_splash_cycle.Adapter.GoveSpinnerAdapter;
import com.echoman.bb_splash_cycle.Adapter.SpinnerAdapter;
import com.echoman.bb_splash_cycle.Ui.mainui.MainUi;
import com.echoman.bb_splash_cycle.data.model.auth.Auth;
import com.echoman.bb_splash_cycle.data.model.donation.Donation;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodData;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodType;
import com.echoman.bb_splash_cycle.data.model.general.city.City;
import com.echoman.bb_splash_cycle.data.model.general.gove.Gove;
import com.echoman.bb_splash_cycle.data.model.generalResponse.GeneralResponse;

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
import static com.echoman.bb_splash_cycle.data.local.SharedPreferencesManger.setSharedPreferences;

public class GeneralRequest {

    public static void onAuth(Call<Auth> call, String phone, String password, boolean rememberMe, Activity activity
            , String actionType, String apiToken) {
        call.enqueue(new Callback<Auth>() {
            public String userapiToken;

            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                try {
                    if (response.body().getStatus() == 1) {


                        setSharedPreferences(activity);

                        saveData(activity, USER_DATA, response.body().getData());
                        saveData(activity, REMEMBER_ME, rememberMe);
                        saveData(activity, USER_PASSWORD, password);
                        saveApi(activity, USER_APITOKEN, userapiToken);

                        if (actionType.equals(LOGIN)) {
                            userapiToken = response.body().getData().getApiToken();
                            activity.startActivity(new Intent(activity, MainUi.class));
                        }
                        if (actionType.equals(EDIT_PROFILE)) {
                            response.body().getData().setApiToken(apiToken);
                        }

                    }

                    //  Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
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

    public static void getBloodTypes(Activity activity, final Call<BloodType> method, Spinner spinner, BloodSpinnerAdapter bloodSpinnerAdapter,
                                     int selectedid) {
        method.enqueue(new Callback<BloodType>() {

            @Override
            public void onResponse(Call<BloodType> call, Response<BloodType> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        bloodSpinnerAdapter.setData(response.body().getData(), "فصيلة الدم");
                        spinner.setAdapter(bloodSpinnerAdapter);


                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<BloodType> call, Throwable t) {

            }
        });


    }


    public static void getgoverment(Activity activity, final Call<Gove> method, Spinner spinner, GoveSpinnerAdapter goveSpinnerAdapter,
                                    int selectedid) {
        method.enqueue(new Callback<Gove>() {

            @Override
            public void onResponse(Call<Gove> call, Response<Gove> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        goveSpinnerAdapter.setData(response.body().getData(), "المحافظة");
                        spinner.setAdapter(goveSpinnerAdapter);


                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Gove> call, Throwable t) {

            }
        });


    }

    public static void getcity(FragmentActivity activity, Call<City> getcity, Spinner fragmentRegisterSpCity, CitySpinnerAdapter citySpinnerAdapter) {
   getcity.enqueue(new Callback<City>() {
       @Override
       public void onResponse(Call<City> call, Response<City> response) {
           try {
               if(response.body().getStatus()== 1)
               {
                   citySpinnerAdapter.setData(response.body().getData(),"إخترالمدينة");
                   fragmentRegisterSpCity.setAdapter(citySpinnerAdapter);
               }
           }
           catch (Exception e){

           }
       }

       @Override
       public void onFailure(Call<City> call, Throwable t) {

       }
   });
    }
}


