package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.GetCommentBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/20.
 */

public interface GetCommentService {
    @POST("commentOfRecord")
    Call<GetCommentBean> getComment(@Query("uerId") int user_Id,
                                    @Query("rcordId") int record_Id);
}
