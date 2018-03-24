package com.example.hp.oralpractice.webAPI;

import com.example.hp.oralpractice.webService.GetCommentService;

import retrofit2.Retrofit;

/**
 * Created by asus on 2018/3/20.
 */

public class GetCommentAPI extends webapi {
    String url="";
    Retrofit retrofit=getapi(url);

    @Override
    public <T> T getService() {
        return (T) retrofit.create(GetCommentService.class);
    }
}
