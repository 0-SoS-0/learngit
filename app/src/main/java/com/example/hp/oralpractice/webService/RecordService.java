package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.LoginBean;
import com.example.hp.oralpractice.bean.gsonbean.Record;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/16.
 */

public interface RecordService {
    @Multipart
    @POST("uploadRecord")
    Call<Record> getrecord(@Part("user_Id") String user_Id,
                           @Part MultipartBody.Part file

    );
}
