package com.example.hp.oralpractice.webAPI;

import com.example.hp.oralpractice.webService.MyParisService;

import retrofit2.Retrofit;

/**
 * Created by asus on 2018/3/16.
 */

public class MyPotraitAPI extends webapi {
    String url="";
    Retrofit retrofit=getapi(url);
    @Override
    public <T> T getService() {
        return (T) retrofit.create(MyParisService.class);
    }
}
