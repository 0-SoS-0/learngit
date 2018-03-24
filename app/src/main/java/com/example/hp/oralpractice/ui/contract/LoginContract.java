package com.example.hp.oralpractice.ui.contract;

import com.example.hp.oralpractice.base.IBasePresenter;
import com.example.hp.oralpractice.base.IBaseView;

/**
 * Created by HP on 2018/2/13.
 */

public interface LoginContract {
        interface LoginView extends IBaseView<LoginPresenter>{
            void LoginSuccess();
            void LoginFailed(String failed);

        }
        interface LoginPresenter extends IBasePresenter{
            void login(String phoneNum,String PassWord);



        }
}
