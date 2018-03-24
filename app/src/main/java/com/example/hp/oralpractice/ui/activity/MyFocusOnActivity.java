package com.example.hp.oralpractice.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.adapter.FocusAdapter;
import com.example.hp.oralpractice.bean.gsonbean.FocusOnBean;
import com.example.hp.oralpractice.bean.gsonbean.MyFocusBean;
import com.example.hp.oralpractice.bean.gsonbean.User;
import com.example.hp.oralpractice.webAPI.MyFocusOn;
import com.example.hp.oralpractice.webService.MyFocusOnService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asus on 2018/3/13.
 */

public class MyFocusOnActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.focus_recyclerView)
    RecyclerView focusRecyclerview;
    @BindView(R.id.swipeRefresh_focus)
    SwipeRefreshLayout swipeRefreshFocus;
    private List<FocusOnBean> focusList = new ArrayList<>();

    private User user = User.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        swipeRefreshFocus.setColorSchemeResources(R.color.md_teal_400, R.color.md_light_green_600);
        swipeRefreshFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshFocusList();
            }
        });
    }


    //获取数据
    private void refreshFocusList() {
        MyFocusOn myFocusOnapi=new MyFocusOn();
        MyFocusOnService myFocusOnService=myFocusOnapi.getService();
        Call<FocusOnBean> call_focus=myFocusOnService.getfocus(1,1,1);
        call_focus.enqueue(new Callback<FocusOnBean>() {
            @Override
            public void onResponse(Call<FocusOnBean> call, Response<FocusOnBean> response) {
                adapter();
            }

            @Override
            public void onFailure(Call<FocusOnBean> call, Throwable t) {

            }
        });

    }

    private void adapter() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager layoutManager=new LinearLayoutManager(MyFocusOnActivity.this);
                focusRecyclerview.setLayoutManager(layoutManager);
                FocusAdapter adapter = new FocusAdapter(focusList);
                focusRecyclerview.setAdapter(adapter);
                swipeRefreshFocus.setRefreshing(false);
            }
        });
    }
}
