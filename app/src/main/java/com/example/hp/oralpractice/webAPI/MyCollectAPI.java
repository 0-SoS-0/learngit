package com.example.hp.oralpractice.webAPI;

import com.example.hp.oralpractice.webService.Mycollect;

import retrofit2.Retrofit;

/**
 * Created by asus on 2018/3/16.
 */

public class MyCollectAPI extends webapi {
    String url="";
    Retrofit retrofit=getapi(url);

    @Override
    public <T> T getService() {
        return (T) retrofit.create(Mycollect.class);
    }
}
