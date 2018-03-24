package com.example.hp.oralpractice.webService;

import com.example.hp.oralpractice.bean.gsonbean.UploadingRecordBean;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/3/19.
 */

public interface UploadingRecordService {

    @POST("myRecord")
    Call<UploadingRecordBean> getUploadingRecord(@Query("userId") int Id);
}
