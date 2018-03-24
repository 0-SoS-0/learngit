package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.PraiseBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/20.
 */

public interface PraiseService {
    @POST("")
    Call<PraiseBean> getpraise(@Query("user_id") int user_Id,
                               @Query("other_id") int other_Id,
                               @Query("other_record_id") int other_Record_ID);
}
