package com.example.hp.oralpractice.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.ui.activity.MyCollectActivity;
import com.example.hp.oralpractice.ui.activity.MyFeedBackActivity;
import com.example.hp.oralpractice.ui.activity.MyFocusOnActivity;
import com.example.hp.oralpractice.ui.activity.UploadingRecordActivity;
import com.example.hp.oralpractice.ui.activity.MyPersonInfoActivity;
import com.example.hp.oralpractice.ui.activity.MyRecordListActivity;
import com.example.hp.oralpractice.util.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HP on 2017/10/25.
 */

public class MyselfFragment extends BaseFragment {


    View view;
    private static MyselfFragment fragment = null;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_image)
    CircleImageView ivImage;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_item_time)
    TextView tvItemTime;
    @BindView(R.id.myself)
    LinearLayout myself;
    @BindView(R.id.tv_btn_follower)
    TextView tvBtnFollower;
    @BindView(R.id.tv_btn_record)
    TextView tvBtnRecord;
    @BindView(R.id.setting)
    ImageView setting;
    @BindView(R.id.tv_btn_collect)
    TextView tvBtnCollect;
    @BindView(R.id.tv_btn_uploading)
    TextView uploadingrecord;
    @BindView(R.id.tv_btn_feedback)
    TextView tvBtnFeedback;

    Unbinder unbinder;

    public static MyselfFragment getInstance() {
        if (fragment == null) {
            fragment = new MyselfFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myself, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();


        return view;
    }

    private void initView() {

        myself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyPersonInfoActivity.class);
                startActivity(intent);
            }
        });

        tvBtnFollower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyFocusOnActivity.class);
                startActivity(intent);
            }
        });

        tvBtnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyRecordListActivity.class);
                startActivity(intent);
            }
        });


        tvBtnCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(intent);
            }
        });

        uploadingrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UploadingRecordActivity.class);
                startActivity(intent);
            }
        });


        tvBtnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyFeedBackActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
