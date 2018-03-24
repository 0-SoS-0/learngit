package com.example.hp.oralpractice.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.oralpractice.MainActivity;
import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.adapter.MovingAdapter;
import com.example.hp.oralpractice.bean.gsonbean.MovingBean;
import com.example.hp.oralpractice.ui.fragment.BaseFragment;
import com.example.hp.oralpractice.webAPI.MovingAPI;
import com.example.hp.oralpractice.webService.MovingService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 2018/2/3.
 */

public class MovingFragment extends BaseFragment {

    @BindView(R.id.recyclerView_moving)
    RecyclerView recyclerViewMoving;
    @BindView(R.id.refresh_moving)
    SwipeRefreshLayout refreshMoving;

    private List<MovingBean> movingList=new ArrayList<>();
    private MovingAdapter adapter;
    private MainActivity context;
    View view;

    public static MovingFragment newInstance() {

        Bundle args = new Bundle();
        MovingFragment fragment = new MovingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_moving, container, false);
        ButterKnife.bind(this, view);
        init();
        //refreshMovingList();
        return view;
    }


    private void init() {
        refreshMoving.setColorSchemeResources(R.color.md_teal_400, R.color.md_light_green_600);
        refreshMoving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshMovingList();
            }
        });
    }


    //数据请求
    private void refreshMovingList() {
        MovingAPI movingAPI=new MovingAPI();
        MovingService movingService=movingAPI.getService();
        Call<MovingBean> call_moving=movingService.getmoving();
        call_moving.enqueue(new Callback<MovingBean>() {
            @Override
            public void onResponse(Call<MovingBean> call, Response<MovingBean> response) {

                adapter();
            }

            @Override
            public void onFailure(Call<MovingBean> call, Throwable t) {

            }
        });

    }

    private void adapter() {
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        recyclerViewMoving.setLayoutManager(layout);
        adapter = new MovingAdapter(movingList,context);
        recyclerViewMoving.setAdapter(adapter);
        refreshMoving.setRefreshing(false);
    }



}
