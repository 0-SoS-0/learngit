package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.Inform_EditBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/16.
 */

public interface Inform_EditService {
    @POST("")
    Call<Inform_EditBean> getInform_Edit(@Query("user_Id") long Id,
                                         @Query("user_nickname") String user_Nickname,
                                         @Query("user_Sex") String user_Sex,
                                         @Query("user_Discribe") String user_Discribe,
                                         @Query("user_Educ") String user_Educ,
                                         @Query("user_home") String user_home);
}
