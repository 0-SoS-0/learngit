package com.example.hp.oralpractice.ui.contract;

import com.example.hp.oralpractice.base.IBasePresenter;
import com.example.hp.oralpractice.base.IBaseView;

/**
 * Created by HP on 2018/2/19.
 */

public interface SignupContract {
    interface SignupView extends IBaseView<SignupPresenter>{
        void CheckSuccucess();
        void CheckFailed(String failed);

    }
    interface SignupPresenter extends IBasePresenter{
        //注册获取验证码
        void CheckVerify(String phoneNum);
        //注册
        void CheckSignup(String phoneNum,String verifyNum,String pass);

    }
}
