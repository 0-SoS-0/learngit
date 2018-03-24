package com.example.hp.oralpractice.webAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asus on 2018/3/16.
 */

public abstract class webapi {

    Retrofit getapi(String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public abstract <T> T getService();
}
