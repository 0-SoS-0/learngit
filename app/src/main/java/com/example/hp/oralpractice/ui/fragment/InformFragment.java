package com.example.hp.oralpractice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hp.oralpractice.adapter.InformAdapter;
import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.bean.gsonbean.InformBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by HP on 2017/10/25.
 */

public class InformFragment extends BaseFragment {

    View view;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    public List<InformBean> informBeanList = new ArrayList<>();
    public InformAdapter informAdapter;
    private static InformFragment instance = null;

    public static InformFragment getInstance() {
        if (instance == null) {
            instance = new InformFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_inform, container, false);
        ButterKnife.bind(this, view);

            initRecycleView();



        return view;
    }

    private void initRecycleView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(linearLayoutManager);
        informAdapter = new InformAdapter(R.layout.item_inform,informBeanList);
        recycleView.setAdapter(informAdapter);
        recycleView.addItemDecoration(new DividerItemDecoration(getActivity(), VERTICAL));

        informAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        //informAdapter.setEmptyView();
    }

}
