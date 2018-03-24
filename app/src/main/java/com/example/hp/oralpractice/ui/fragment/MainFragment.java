package com.example.hp.oralpractice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.oralpractice.adapter.BasePagerAdapter;
import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.tabs.AttentionFragment;
import com.example.hp.oralpractice.tabs.MovingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HP on 2017/10/25.
 */

public class MainFragment extends BaseFragment {
    View view;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;

    private final  static String[] tabs = new String[]{"关注","动态"};
    public final static int FRAGMENT_ATTENTION=0;
    public final static int FRAGMENT_Move=1;
    private static MainFragment fragment = null;
    private List<Fragment> fragmentList = new ArrayList<>();
    private BasePagerAdapter adapter;

    public static MainFragment getInstance() {
        if (fragment == null) {
            fragment = new MainFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

        //viewPager.setOffscreenPageLimit(pageSize);

        for (int i =0;i<tabs.length;i++){
           tabLayout.addTab(tabLayout.newTab().setText(tabs[i]));
            switch (i){
                case FRAGMENT_ATTENTION:
                    fragmentList.add(AttentionFragment.newInstance());
                    break;
                case FRAGMENT_Move:
                    fragmentList.add(MovingFragment.newInstance());
                    break;
            }
        }

        adapter= new BasePagerAdapter(getChildFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(FRAGMENT_ATTENTION);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setTabMode(TabLayout.GRAVITY_CENTER);
        for (int i = 0;i<tabs.length;i++){
            //tabLayout.setupWithViewPager方法内部会remove所有的tabs，这里重新设置一遍tabs的text，否则tabs的text不显示
            tabLayout.getTabAt(i).setText(tabs[i]);
        }
    }


}
