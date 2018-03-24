package com.example.hp.oralpractice.presenter;

import com.example.hp.oralpractice.model.SignupModel;
import com.example.hp.oralpractice.ui.contract.SignupContract;

/**
 * Created by HP on 2018/2/19.
 */

public class SignupPresenter implements SignupContract.SignupPresenter{
    private SignupContract.SignupView signupView;
    SignupModel signupModel;

    @Override
    public void doShowNetError() {

    }


    @Override
    public void CheckVerify(String phoneNum) {
        if (phoneNum ==null||phoneNum.length()!=11){
            signupView.CheckFailed("请输入正确的手机号");
        }else {
            signupModel.setChecked(phoneNum);

        }
    }

    @Override
    public void CheckSignup(String phoneNum, String verifyNum, String pass) {
        int userId = 1;
        if (userId != 0){
            signupView.CheckSuccucess();
        }

    }
}
