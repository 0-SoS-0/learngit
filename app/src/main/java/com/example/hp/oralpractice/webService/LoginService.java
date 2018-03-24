package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.LoginBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/16.
 */

public interface LoginService {
    @POST("")
    Call<LoginBean> getlogin(@Query("user_Id") String Id);
}
