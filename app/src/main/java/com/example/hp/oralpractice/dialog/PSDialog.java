package com.example.hp.oralpractice.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.bean.gsonbean.ManageUserGsonBean;
import com.example.hp.oralpractice.bean.gsonbean.User;
import com.example.hp.oralpractice.webAPI.PSWChangeAPI;
import com.example.hp.oralpractice.webService.PSWChangeService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asus on 2018/3/18.
 */

public class PSDialog extends Dialog implements View.OnClickListener{


    @BindView(R.id.prePassword)
    EditText prePassword;
    @BindView(R.id.newPassword)
    EditText newPassword;
    @BindView(R.id.confirmPassword)
    EditText confirmPassword;
    @BindView(R.id.dialog_button_ok)
    Button submit;
    @BindView(R.id.dialog_button_cancel)
    Button cancel;

    private User user=User.getInstance();
    private Context context;
    public PSDialog(@NonNull Context context) {
        super(context);
        this.context=context;
    }

    public PSDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_user_password_dialog);
        ButterKnife.bind(this);
        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        params.width = (int) (displayMetrics.widthPixels * 0.75);
        params.height = (int) (displayMetrics.heightPixels * 0.65);
        window.setAttributes(params);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(submit)) {
            String passold=prePassword.getText().toString();
            String passnew1 = newPassword.getText().toString();
            String passnew2 = confirmPassword.getText().toString();
            //pwPresent.passChange(passold, passnew1, passnew2);
           // PsWchange(passold, passnew1, passnew2);
        }
        if (v.equals(cancel)) {
            dismiss();
        }
    }

    private void PsWchange(String passold, String passnew1,  String passnew2) {
        PSWChangeAPI changeAPI=new PSWChangeAPI();
        PSWChangeService pswChangeService=changeAPI.getService();
        Call<ManageUserGsonBean.ManagePasswordGsonBean> call_changepsw=pswChangeService.getPsW(passold, passnew1, passnew2);
        call_changepsw.enqueue(new Callback<ManageUserGsonBean.ManagePasswordGsonBean>() {
            @Override
            public void onResponse(Call<ManageUserGsonBean.ManagePasswordGsonBean> call, Response<ManageUserGsonBean.ManagePasswordGsonBean> response) {

            }

            @Override
            public void onFailure(Call<ManageUserGsonBean.ManagePasswordGsonBean> call, Throwable t) {

            }
        });
    }

}
