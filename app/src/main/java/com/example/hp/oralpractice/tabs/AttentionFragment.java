package com.example.hp.oralpractice.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HP on 2018/2/3.
 */

public class AttentionFragment extends BaseFragment {

    View view;
    @BindView(R.id.recyclerView_moving)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_moving)
    SwipeRefreshLayout refresh;
    Unbinder unbinder;

    public static AttentionFragment newInstance() {
        Bundle args = new Bundle();
        AttentionFragment fragment = new AttentionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_moving, container, false);

        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshMovingList();
            }
        });
    }

    private void refreshMovingList() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
