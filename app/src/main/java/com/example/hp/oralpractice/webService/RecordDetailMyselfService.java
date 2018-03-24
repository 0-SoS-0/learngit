package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.RecordDetailMyselfBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/19.
 */

public interface RecordDetailMyselfService {
    @POST("myRecord")
    Call<RecordDetailMyselfBean> getrecordmyself(@Query("userId") int userId,
                                                 @Query("recordId") int recordId);
}
