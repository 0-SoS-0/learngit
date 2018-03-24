package com.example.hp.oralpractice.presenter;
import com.example.hp.oralpractice.ui.contract.LoginContract;

/**
 * Created by HP on 2018/2/19.
 */

public class LoginPresenter implements LoginContract.LoginPresenter {
    private LoginContract.LoginView loginView;

    public LoginPresenter(LoginContract.LoginView loginView){
        this.loginView = loginView;
    }



    @Override
    public void doShowNetError() {


    }

    @Override
    public void login(String phoneNum, String PassWord) {
        if (phoneNum.equals("") || phoneNum.length() != 11) {
            loginView.LoginFailed("请输入正确的手机号码");
        } else if (PassWord.equals("")) {
            loginView.LoginFailed("请输入密码");
        }else {
            //后台验证，拉取信息
            loginView.LoginSuccess();
        }

    }
}
