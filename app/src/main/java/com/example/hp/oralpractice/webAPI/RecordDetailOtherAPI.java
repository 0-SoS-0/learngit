package com.example.hp.oralpractice.webAPI;

import com.example.hp.oralpractice.webService.RecordDetailOtherService;

import retrofit2.Retrofit;

/**
 * Created by asus on 2018/3/19.
 */

public class RecordDetailOtherAPI extends webapi{
    String url="";
    Retrofit retrofit=getapi(url);

    @Override
    public <T> T getService() {
        return (T) retrofit.create(RecordDetailOtherService.class);
    }
}
