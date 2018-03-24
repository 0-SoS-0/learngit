package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.SendCommentBean;

import org.w3c.dom.Comment;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/19.
 */

public interface SendCommentService {
    @POST("")
    Call<SendCommentBean> getsendcomment(@Query("userId") int user_Id,
                                         @Query("otherId") int other_Id,
                                         @Query("record_Id_other") int record_Id_other,
                                         @Query("Comment") String Comment);
}
