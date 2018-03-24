package com.example.hp.oralpractice.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.adapter.UploadingRecordAdapter;
import com.example.hp.oralpractice.bean.gsonbean.UploadingRecordBean;
import com.example.hp.oralpractice.webAPI.UploadingRecordAPI;
import com.example.hp.oralpractice.webService.UploadingRecordService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asus on 2018/3/13.
 */

public class UploadingRecordActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.recyclerView_Uploading)
    RecyclerView recyclerViewUploading;
    @BindView(R.id.swipeRefresh_Uploading)
    SwipeRefreshLayout swipeRefreshUploading;

    private List<UploadingRecordBean> recordBeanList=new ArrayList<>();
    private UploadingRecordAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadingrecord);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        swipeRefreshUploading.setColorSchemeResources(R.color.md_teal_400, R.color.md_light_green_600);
        swipeRefreshUploading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshuploadingrecordList();
            }
        });
    }

    //获取数据
    private void refreshuploadingrecordList() {
        UploadingRecordAPI recordAPI = new UploadingRecordAPI();
        UploadingRecordService service = recordAPI.getService();
        retrofit2.Call<UploadingRecordBean> call_uplaoding = service.getUploadingRecord(1);
        call_uplaoding.enqueue(new Callback<UploadingRecordBean>() {
            @Override
            public void onResponse(retrofit2.Call<UploadingRecordBean> call, Response<UploadingRecordBean> response) {
                adapter();
            }

            @Override
            public void onFailure(retrofit2.Call<UploadingRecordBean> call, Throwable t) {

            }
        });

    }

    private void adapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerViewUploading.setLayoutManager(layoutManager);
        adapter=new UploadingRecordAdapter(recordBeanList,this);
        recyclerViewUploading.setAdapter(adapter);
    }
}
