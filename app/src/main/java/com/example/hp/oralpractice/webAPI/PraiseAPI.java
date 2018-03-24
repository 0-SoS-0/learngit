package com.example.hp.oralpractice.webAPI;

import com.example.hp.oralpractice.webService.PraiseService;

import retrofit2.Retrofit;

/**
 * Created by asus on 2018/3/20.
 */

public class PraiseAPI extends webapi{
    String url="";
    Retrofit retrofit=getapi(url);
    @Override
    public <T> T getService() {
        return (T) retrofit.create(PraiseService.class);
    }
}
