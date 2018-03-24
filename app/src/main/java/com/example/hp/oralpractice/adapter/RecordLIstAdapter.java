package com.example.hp.oralpractice.adapter;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.util.Player;


import java.util.ArrayList;

/**
 * Created by asus on 2018/3/14.
 */

public class RecordLIstAdapter extends BaseAdapter {
    ArrayList<String> redordData;
    Context context;
    RecordLIstHolder holder = null;
     Player player;

    public RecordLIstAdapter(ArrayList<String> data, Context context) {
        this.redordData = data;
        this.context = context;

    }

    @Override
    public int getCount() {
        return redordData.size();
    }

    @Override
    public Object getItem(int i) {
        return redordData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recordlist, null);
            holder = new RecordLIstHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
            holder.btnPlayUrl = (ImageButton) convertView.findViewById(R.id.btnPlayUrl);
            holder.btnPause = (ImageButton) convertView.findViewById(R.id.btnPause);
           // holder.btnContinue = (ImageButton) convertView.findViewById(R.id.btnContinue);
            holder.skbProgress = (SeekBar) convertView.findViewById(R.id.skbProgress);
            convertView.setTag(holder);
        } else {
            holder = (RecordLIstHolder) convertView.getTag();
        }
        holder.tv.setText(redordData.get(position));
       /* holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "000" + redordData.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });*/
        holder.btnPlayUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String ss="/storage/emulated/0/Oral_Practice/voice/";
//                String url = ss+redordData.get(position).toString();
                //               player.playUrl(url);
                holder.btnPlayUrl.setVisibility(View.GONE);
                holder.skbProgress.setVisibility(View.VISIBLE);
                holder.btnPause.setVisibility(View.VISIBLE);
                Toast.makeText(context, "000" + redordData.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                holder.btnPlayUrl.setVisibility(View.GONE);
                holder.btnPause.setVisibility(View.GONE);
                holder.btnContinue.setVisibility(View.VISIBLE);
            }
        });
        holder.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.Continue();
                holder.btnPlayUrl.setVisibility(View.GONE);
                holder.btnPause.setVisibility(View.VISIBLE);
                holder.btnContinue.setVisibility(View.GONE);
            }
        });

        return convertView;
    }

    public class RecordLIstHolder {
        TextView tv;
        ImageButton btnPlayUrl;
        ImageButton btnPause;
        ImageButton btnContinue;
        SeekBar skbProgress;
    }

    public void settin(int p, int index) {
        if (index == 0) {
            String temp = redordData.get(p);
            redordData.remove(p);
            redordData.add(0, temp);
            Toast.makeText(context, "置顶成功", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();

        } else if (index == 1) {

            Toast.makeText(context, "上传...", Toast.LENGTH_SHORT).show();
        } else {
            // notifyDataSetChanged();
            String ss = redordData.get(p).toString();
            redordData.remove(p);
            Toast.makeText(context, "删除成功" + ss + redordData.size(), Toast.LENGTH_SHORT).show();

        }

    }

    //设置滑动删除按钮的宽高属性的方法
    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /**
     *
     */


}
