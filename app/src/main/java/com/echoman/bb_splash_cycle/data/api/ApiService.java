package com.echoman.bb_splash_cycle.data.api;


import com.echoman.bb_splash_cycle.data.model.auth.Auth;
import com.echoman.bb_splash_cycle.data.model.donation.Donation;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodType;
import com.echoman.bb_splash_cycle.data.model.general.city.City;
import com.echoman.bb_splash_cycle.data.model.general.city.CityData;
import com.echoman.bb_splash_cycle.data.model.general.gove.Gove;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("login")
    @FormUrlEncoded
    Call<Auth> onLogin(@Field("phone") String phone,
                       @Field("password") String password);


    @GET("donation-requests?api_token=")
    Call<Donation> getdonations();

    @GET("blood-types")
    Call<BloodType> getBloodTypes();

    @GET("governorates")
    Call<Gove> getGoves();

    @GET("cities")
    Call<City> getcity(@Query("governorate_id") int govid);
}
