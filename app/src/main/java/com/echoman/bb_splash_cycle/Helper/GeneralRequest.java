package com.echoman.bb_splash_cycle.Helper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.echoman.bb_splash_cycle.Ui.MainActivity;
import com.echoman.bb_splash_cycle.Ui.mainui.MainUi;
import com.echoman.bb_splash_cycle.data.model.auth.Auth;
import com.echoman.bb_splash_cycle.data.model.donation.Donation;

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

    public static void onAuth(Call<Auth> call, String phone,String password, boolean rememberMe, Activity activity
            , String actionType, String apiToken) {
        call.enqueue(new Callback<Auth>() {
            public String userapiToken;

            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                try {
                    if (response.body().getStatus() == 1) {

                        if (actionType.equals(EDIT_PROFILE)) {
                            response.body().getData().setApiToken(apiToken);
                        }

                        setSharedPreferences (activity);

                        saveData( activity, USER_DATA, response.body().getData());
                        saveData( activity, REMEMBER_ME, rememberMe);
                        saveData( activity, USER_PASSWORD, password);
                        saveApi(activity,USER_APITOKEN,userapiToken);

                        if (actionType.equals(LOGIN)) {
                            userapiToken=  response.body().getData().getApiToken();
                            activity.startActivity( new Intent(activity, MainUi.class));
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
    public static void getalldonations(Activity activity,Call<Donation> call) {
        call.enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation>call, Response<Donation> response) {

                if (response.body().getStatus()==1)
                    Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {

            }

            public String userapiToken;

        });
    }
}
