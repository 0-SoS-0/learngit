package com.example.hp.oralpractice.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.hp.oralpractice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2018/3/14.
 */

public class RecordignDialog extends Dialog implements View.OnClickListener{


    @BindView(R.id.hint)
    TextView recordhint;
    @BindView(R.id.dialog_button_cancel)
    Button cancel;
    @BindView(R.id.dialog_button_ok)
    Button submit;
    private Context context;

    public RecordignDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public RecordignDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_recordinghint);
        ButterKnife.bind(this);
        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        params.width = (int) (displayMetrics.widthPixels * 1);
        //params.height = (int) (displayMetrics.heightPixels * 0.2);
        window.setAttributes(params);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(submit)){
            dismiss();
        }if (v.equals(cancel)){
            dismiss();
        }


    }
}
