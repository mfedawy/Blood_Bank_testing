package com.echoman.bb_splash_cycle.data.api;


import com.echoman.bb_splash_cycle.data.model.client.Client;
import com.echoman.bb_splash_cycle.data.model.donation.Donation;
import com.echoman.bb_splash_cycle.data.model.generalResponse.GeneralResponse;
import com.echoman.bb_splash_cycle.data.model.login.resetPassword.ResetPasword;
import com.echoman.bb_splash_cycle.data.model.login.signUp.SignUp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("login")
    @FormUrlEncoded
    Call<Client> onLogin(@Field("phone") String phone,
                       @Field("password") String password);


    @GET("donation-requests")
    Call<Donation> getalldonations(@Query("api_token") String apiToken,
                                   @Query("page") int page);


    @GET("blood-types")
    Call<GeneralResponse> getBloodTypes();

    @GET("governorates")
    Call<GeneralResponse> getGoves();

    @GET("cities")
    Call<GeneralResponse> getcity(@Query("governorate_id") int govid);

    @POST("signup")
    @FormUrlEncoded
    Call<Client> onSignUp(@Field("name") String name,
                          @Field("email") String email,
                          @Field("birth_date") String birth_date,
                          @Field("city_id") int cityId,
                          @Field("phone") String phone,
                          @Field("donation_last_date") String donationLastDate,
                          @Field("password") String password,
                          @Field("password_confirmation") String passwordConfirmation,
                          @Field("blood_type_id") int bloodTypeId);

    @POST("reset-password")
    @FormUrlEncoded
    Call<ResetPasword> resetPass( @Field("phone") String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<ResetPasword> newPass( @Field("password") String pass,
                                @Field("password_confirmation") String passconfirm,
                                @Field("pin_code") String pin,
                                @Field("phone") String phone
    );

    @POST("profile")
    @FormUrlEncoded
    Call<Client> updateuserdata(@Field("name") String name,
                                @Field("email") String email,
                                @Field("birth_date") String birth_date,
                                @Field("city_id") int cityId,
                                @Field("phone") String phone,
                                @Field("donation_last_date") String donationLastDate,
                                @Field("password") String password,
                                @Field("password_confirmation") String passwordConfirmation,
                                @Field("blood_type_id") int bloodTypeId,
                                @Field("api_token") String api);


}
