package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.FocusOnBean;
import com.example.hp.oralpractice.bean.gsonbean.LoginBean;
import com.example.hp.oralpractice.bean.gsonbean.MyCollectBean;
import com.example.hp.oralpractice.bean.gsonbean.MyFocusBean;
import com.example.hp.oralpractice.ui.activity.UploadingRecordActivity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/16.
 */

public interface MyFocusOnService {

    @POST("")
    Call<FocusOnBean> getfocus(@Query("user_Id") int user_Id,
                               @Query("other_Id") int other_Id,
                               @Query("recordId") int record_Id);
}
