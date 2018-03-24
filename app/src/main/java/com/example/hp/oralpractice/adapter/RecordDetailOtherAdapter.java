package com.example.hp.oralpractice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.bean.gsonbean.RecordDetailOtherBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by asus on 2018/3/19.
 */

public class RecordDetailOtherAdapter extends RecyclerView.Adapter<RecordDetailOtherAdapter.ViewHolder> {


    private List<RecordDetailOtherBean> mdetailOtherBeanList;

    public RecordDetailOtherAdapter(List<RecordDetailOtherBean> detailOtherBeanList) {
        mdetailOtherBeanList=detailOtherBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecordDetailOtherBean recordDetailOtherBean=mdetailOtherBeanList.get(position);
//        holder.userImage.setImageResource();
//        holder.userNickname.setText();
//        holder.userId.setText();
//        holder.comment.setText();

    }

    @Override
    public int getItemCount() {
        return mdetailOtherBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_Image)
        CircleImageView userImage;
        @BindView(R.id.user_Id)
        TextView userId;
        @BindView(R.id.user_nickname)
        TextView userNickname;
        @BindView(R.id.comment)
        TextView comment;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
