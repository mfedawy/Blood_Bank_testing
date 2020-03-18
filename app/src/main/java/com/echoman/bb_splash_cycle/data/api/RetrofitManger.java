package com.echoman.bb_splash_cycle.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManger {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://ipda3-tech.com/blood-bank/api/v1/";

    public static synchronized ApiService getRetrofitClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(ApiService.class);
    }

}
