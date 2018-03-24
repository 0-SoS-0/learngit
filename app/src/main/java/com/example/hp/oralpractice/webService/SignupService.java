package com.example.hp.oralpractice.webService;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by HP on 2018/2/23.
 */

public interface SignupService {
    @GET("checkPhone")
    Observable<String> setVerify(@Query("phone") String phoneNum);

}
