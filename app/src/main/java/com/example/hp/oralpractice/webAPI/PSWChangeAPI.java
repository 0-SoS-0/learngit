package com.example.hp.oralpractice.webAPI;

import com.example.hp.oralpractice.webService.PSWChangeService;

import retrofit2.Retrofit;

/**
 * Created by asus on 2018/3/18.
 */

public class PSWChangeAPI extends webapi {
    String url="";
    Retrofit retrofit=getapi(url);

    @Override
    public <T> T getService() {
        return (T) retrofit.create(PSWChangeService.class);
    }
}
