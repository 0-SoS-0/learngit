package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.LoginBean;
import com.example.hp.oralpractice.bean.gsonbean.MyparisBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/16.
 */

public interface MyParisService {
    @POST("")
    Call<MyparisBean> getpraise(@Query("user_Id") int userId,
                                @Query("otherId") int otherId,
                                @Query("recordId") int otherRecordId);
}
