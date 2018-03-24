package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.RecordDetailOtherBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/19.
 */

public interface RecordDetailOtherService {
    @POST("")
    Call<RecordDetailOtherBean> getRecord(@Query("otherId") int othetId,
                                          @Query("recordId") int recordId);
}
