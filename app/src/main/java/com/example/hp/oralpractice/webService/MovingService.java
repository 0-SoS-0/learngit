package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.MovingBean;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/19.
 */

public interface MovingService {
    @POST("")
    Call<MovingBean> getmoving();
}
