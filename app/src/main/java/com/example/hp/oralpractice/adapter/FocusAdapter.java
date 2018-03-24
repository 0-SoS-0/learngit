package com.example.hp.oralpractice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.bean.gsonbean.FocusOnBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by asus on 2018/3/19.
 */

public class FocusAdapter extends RecyclerView.Adapter<FocusAdapter.ViewHolder> {

    private List<FocusOnBean> mfocusOnList;

    public FocusAdapter(List<FocusOnBean> focusList) {
        mfocusOnList=focusList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_focus, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        FocusOnBean focusOnBean=mfocusOnList.get(position);
       // holder.myFocusUserImage=focusOnBean.
        //holder.focusUserNickname=focusOnBean.
        //holder.focusUserId=focusOnBean.

    }

    @Override
    public int getItemCount() {
        return mfocusOnList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.my_focus_user_Image)
        CircleImageView myFocusUserImage;
        @BindView(R.id.focus_user_nickname)
        TextView focusUserNickname;
        @BindView(R.id.focus_user_Id)
        TextView focusUserId;
        private List<FocusOnBean> mfocusList;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
