package com.example.hp.oralpractice.ui.activity;

import android.content.Intent;
import android.graphics.Color;
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

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.ui.contract.SignupContract;
import com.example.hp.oralpractice.util.PopupWindowInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HP on 2017/10/25.
 */

public class SignupActivity extends BaseActivity implements SignupContract.SignupView {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.phone_layout)
    LinearLayout phoneLayout;
    @BindView(R.id.edt_verify)
    EditText edtVerify;
    @BindView(R.id.btu_verify)
    Button btuVerify;
    @BindView(R.id.get_layout)
    LinearLayout getLayout;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.pass_layout)
    LinearLayout passLayout;
    @BindView(R.id.btu_sign)
    Button btuSign;
    private SignupContract.SignupPresenter signupPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        initToolBar();
        initUI();

    }

    private void initToolBar() {
        setSupportActionBar(toolBar);
        toolbarTitle.setText("注册");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);

        }
    }

    private void initUI() {
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//                startActivity(intent);
            }
        });
        btuVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = edtPhone.getText().toString();
                //signupPresenter.CheckVerify(phoneNumber);
                btuVerify.setBackgroundResource(R.drawable.btu_verify_pressed);
            }
        });
        btuSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = edtPhone.getText().toString();
                String verify = edtVerify.getText().toString();
                String pass = edtPass.getText().toString();
                signupPresenter.CheckSignup(phoneNum, verify, pass);
            }
        });
    }


    @Override
    public void setPresenter(SignupContract.SignupPresenter presenter) {

    }

    @Override
    public void CheckSuccucess() {
        PopupWindowInfo popupWindowInfo = new PopupWindowInfo(SignupActivity.this);
        popupWindowInfo.Changepopupwindow("注册成功！");
    }

    @Override
    public void CheckFailed(String failed) {
        Toast.makeText(SignupActivity.this, failed, Toast.LENGTH_LONG).show();
    }


}
