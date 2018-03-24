package com.example.hp.oralpractice.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.oralpractice.MainActivity;
import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.presenter.LoginPresenter;
import com.example.hp.oralpractice.ui.contract.LoginContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HP on 2017/10/25.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView{

    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.phone_layout)
    LinearLayout phoneLayout;
    @BindView(R.id.pass_layout)
    LinearLayout passLayout;
    @BindView(R.id.login_btu)
    Button btuLogin;
    @BindView(R.id.signup_btu)
    Button btuSignup;
    @BindView(R.id.pass_forgotten)
    TextView passForgotten;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;

    private String phoneNumber = "18271200219";
    private String passWord = "111";
    private LoginContract.LoginPresenter loginPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initToolBar();
        loginPresenter = new LoginPresenter(this);
        initView();

    }

    private void initToolBar() {
        setSupportActionBar(toolBar);
        toolbarTitle.setText("登录");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }
    private void initView(){
        btuLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showProgressDialog();
                //phoneNumber =edtPhone.getText().toString();
                //passWord = edtPass.getText().toString();
                loginPresenter.login(phoneNumber,passWord);
            }
        });
        btuSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
        }
    });
}


    @Override
    public void setPresenter(LoginContract.LoginPresenter presenter) {
        if (presenter ==null){
            this.loginPresenter =new LoginPresenter(this);
        }
    }


    @Override
    public void LoginSuccess() {
        //progressDialog.dismiss();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void LoginFailed(String failed) {
        Toast.makeText(LoginActivity.this,failed, Toast.LENGTH_LONG).show();
    }

    public void showProgressDialog(){
        if (progressDialog !=null){
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("登录中");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置进度条为圆形
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();

        }
    }

}
