package com.example.hp.oralpractice.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.bean.gsonbean.InformBean;

import java.util.List;

/**
 * Created by HP on 2017/11/16.
 */

public class InformAdapter extends BaseQuickAdapter<InformBean,BaseViewHolder> {
    private Context context;
    private List<InformBean> informBeanList;

    //构造方法
    public InformAdapter(@LayoutRes int layoutResId, @Nullable List<InformBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, InformBean item) {
        helper.setText(R.id.tv_item_nick,item.getNickName());
        helper.setText(R.id.tv_item_time,item.getTime());

    }

}
