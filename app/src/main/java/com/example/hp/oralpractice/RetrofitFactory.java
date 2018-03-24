package com.example.hp.oralpractice;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 2018/2/21.
 */

public class RetrofitFactory {

    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit retrofit;
    public String url;


    private RetrofitFactory(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
    }
    private static class SingletonHolder{
        private static final RetrofitFactory INSTANCE = new RetrofitFactory();
    }
    /**
     * 获取RetrofitFactory
     * @return
     */
    public static RetrofitFactory getInstance(){
        return SingletonHolder.INSTANCE;
    }
    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }



}
