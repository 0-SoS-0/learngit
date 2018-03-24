package com.example.hp.oralpractice.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.bean.gsonbean.UploadingRecordBean;
import com.example.hp.oralpractice.bean.gsonbean.User;
import com.example.hp.oralpractice.ui.activity.RecordDetailOtherActivity;
import com.example.hp.oralpractice.ui.activity.UploadingRecordActivity;
import com.example.hp.oralpractice.util.Player;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2018/3/19.
 */

public class UploadingRecordAdapter extends RecyclerView.Adapter<UploadingRecordAdapter.ViewHolder> {


    private List<UploadingRecordBean> uploadingRecordBeanList;
    private User user = User.getInstance();
    private UploadingRecordActivity mContext;
    private Player player;


    public UploadingRecordAdapter(List<UploadingRecordBean> recordBeanList, UploadingRecordActivity uploadingRecordActivity) {
        uploadingRecordBeanList = recordBeanList;
        mContext = uploadingRecordActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_uploading_record, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UploadingRecordBean uploadingRecordBean = uploadingRecordBeanList.get(position);
                Intent intent = new Intent(mContext, RecordDetailOtherActivity.class);
                intent.putExtra("user_Id", "");
                intent.putExtra("record_Id", "");
                mContext.startActivityForResult(intent, 5);
            }
        });
        holder.btnPlayUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://link.hhtjim.com/163/32507038.mp3";
                player.playUrl(url);
                holder.btnPlayUrl.setVisibility(View.GONE);
                holder.skbProgress.setVisibility(View.VISIBLE);
                holder.btnPause.setVisibility(View.VISIBLE);
            }
        });

        holder.btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player.pause();
                holder.btnPlayUrl.setVisibility(View.GONE);
                holder.btnPause.setVisibility(View.GONE);
                holder.Continue.setVisibility(View.VISIBLE);
            }
        });

        holder.Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                player.Continue();
                holder.btnPlayUrl.setVisibility(View.GONE);
                holder.btnPause.setVisibility(View.VISIBLE);
                holder.Continue.setVisibility(View.GONE);
            }
        });
        holder.skbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public int progress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                this.progress=progress*player.mediaPlayer.getDuration()
                        / seekBar.getMax();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
                player.mediaPlayer.seekTo(progress);
                Log.d("55555555555555", "onStopTrackingTouch: "+progress);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UploadingRecordBean uploadingRecordBean = uploadingRecordBeanList.get(position);
        holder.imageView.setImageResource(R.drawable.music);
        //holder.itemTv.setText();

    }

    @Override
    public int getItemCount() {
        return uploadingRecordBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.view)
        View view;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.btnPlayUrl)
        ImageButton btnPlayUrl;
        @BindView(R.id.btnPause)
        ImageButton btnPause;
        @BindView(R.id.Continue)
        ImageButton Continue;
        @BindView(R.id.item_tv)
        TextView itemTv;
        @BindView(R.id.layout)
        LinearLayout layout;
        @BindView(R.id.skbProgress)
        SeekBar skbProgress;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
