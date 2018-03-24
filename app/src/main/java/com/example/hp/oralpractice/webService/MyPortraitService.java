package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.LoginBean;
import com.example.hp.oralpractice.bean.gsonbean.UserPortraitGsonBean;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/16.
 */

public interface MyPortraitService {
    @POST("uploadUserImg")
    Call<UserPortraitGsonBean> getportrait(@Part("user_Id" )long user_Id,
                                           @Part MultipartBody.Part file);
}
