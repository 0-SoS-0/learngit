package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.ManageUserGsonBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/18.
 */

public interface PSWChangeService {
    @POST("")
    Call<ManageUserGsonBean.ManagePasswordGsonBean> getPsW(@Query("pswold") String psold,
                                                                 @Query("pswnew1") String pswnew1,
                                                                 @Query("pswnew2") String pswnew2);
}
