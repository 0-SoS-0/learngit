package com.example.hp.oralpractice.model;

import com.example.hp.oralpractice.RetrofitFactory;
import com.example.hp.oralpractice.ui.contract.SignupContract;
import com.example.hp.oralpractice.webService.SignupService;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by HP on 2018/2/23.
 */

public class SignupModel extends BaseModel {
    private SignupContract.SignupPresenter signupPresenter;
    private SignupService signupService;
    public SignupModel(){
        signupService = RetrofitFactory.getInstance().create(SignupService.class);
    }
    public Observable<String> setChecked(String phoneNum){
        return observe(signupService.setVerify(phoneNum))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {

                        return s;
                    }
                });

    }
}
