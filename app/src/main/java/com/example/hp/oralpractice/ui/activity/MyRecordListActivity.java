package com.example.hp.oralpractice.ui.activity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.bean.SwipeBean;
import com.example.hp.oralpractice.util.Player;
import com.example.swipemenulib.SwipeMenuLayout;
import com.mcxtzhang.commonadapter.lvgv.CommonAdapter;
import com.mcxtzhang.commonadapter.lvgv.ViewHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2018/3/13.
 */

public class MyRecordListActivity extends BaseActivity {

    // 存放录音文件位置
    String AUDIO_RECORD = "/storage/emulated/0/Oral_Practice/voice/";
    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.play_hint)
    TextView playhint;
    @BindView(R.id.skbProgress)
    SeekBar skbProgress;
    @BindView(R.id.btnPause)
    ImageButton btnPause;
    @BindView(R.id.btnContinue)
    ImageButton btnContinue;
    private List<SwipeBean> mDatas = new ArrayList<>();
    private SwipeMenuLayout menuLayout;
    private boolean l;
    private Player player;
    private static int num;
    //private Timer mTimer = new Timer();
    private MediaPlayer mediaPlayer;
    public static int value;

    Handler handleProgress = new Handler() {
        public void handleMessage(Message msg) {

            int position = player.mediaPlayer.getCurrentPosition();
            int duration = player.mediaPlayer.getDuration();

            if (duration > 0) {
                long pos = skbProgress.getMax() * position / duration;
                skbProgress.setProgress((int) pos);
                Log.d("33333333300000", "handleMessage: " + pos);
                //Continue((int) pos);
                /*if (pos == 100) {
                    //mediaPlayer = new MediaPlayer();
                    if (player.mediaPlayer != null) {
                        player.mediaPlayer.stop();
                        player.mediaPlayer.release();
                        player.mediaPlayer = null;
                    }
                }*/
            }
        }

        ;
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecord);
        ButterKnife.bind(this);
        File sceneFile = new File(AUDIO_RECORD);
        File[] files = sceneFile.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                System.out.println("文件夹下的文件：" + files[i].getName());
                Log.d("555555555", "文件夹下的文件: " + files[i].getName());
                mDatas.add(new SwipeBean("" + files[i].getName()));
                //load();
            }
        } else {
            Toast.makeText(MyRecordListActivity.this, "列表空空如也", Toast.LENGTH_SHORT).show();
        }
        skbProgress();
        setAdapter();

        player = new Player(skbProgress);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.Continue();
                btnContinue.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);

            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                btnContinue.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
            }
        });

    }

    private void setAdapter() {

        listView.setAdapter(new CommonAdapter<SwipeBean>(this, mDatas, R.layout.item_recordlist) {


            @Override
            public void convert(final ViewHolder holder, SwipeBean swipeBean, final int position) {
                //((SwipeMenuLayout)holder.getConvertView()).setIos(false);//这句话关掉IOS阻塞式交互效果

                //当前结束歌曲播放
                holder.setText(R.id.item_tv, swipeBean.name);
                SwipeBean temp = mDatas.get(num);
                playhint.setText(temp.getName());
                holder.setOnClickListener(R.id.palay_layout, new View.OnClickListener() {

                    SwipeBean temp = mDatas.get(position);

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View v) {

                        if (num == position) {

                            if (player.mediaPlayer == null) {
                                SwipeBean temp = mDatas.get(num);
                                String ss = temp.getName();
                                player.playUrl(AUDIO_RECORD + ss);
                                Toast.makeText(MyRecordListActivity.this, "已经在播放:" + temp.getName(), Toast.LENGTH_SHORT).show();
                                playhint.setText(temp.getName());
                                //player.Continue();

                            } else {
                                Toast.makeText(MyRecordListActivity.this, "已经在播放:" + temp.getName(), Toast.LENGTH_SHORT).show();
                                playhint.setText(temp.getName());
                            }

                        } else {
                            num = position;
                            SwipeBean temp = mDatas.get(position);
                            // player.stop();
                            String ss = temp.getName();
                            player.playUrl(AUDIO_RECORD + ss);
                            btnContinue.setVisibility(View.GONE);
                            btnPause.setVisibility(View.VISIBLE);
                            playhint.setText(temp.getName());
                            btnContinue.setBackgroundColor(R.color.md_light_green_A700);
                            btnPause.setBackgroundColor(R.color.md_light_green_A700);

                        }
                    }
                });
             /*   holder.setOnClickListener(R.id.item_tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SwipeBean temp = mDatas.get(position);
                        Toast.makeText(MyRecordListActivity.this, "position:" + position + temp.getName(), Toast.LENGTH_SHORT).show();

                    }
                });*/


                holder.setOnClickListener(R.id.btnDelete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SwipeBean temp = mDatas.get(position);

                        //在ListView里，点击侧滑菜单上的选项时，如果想让侧滑菜单同时关闭，调用这句话
                        ((SwipeMenuLayout) holder.getConvertView()).quickClose();
                        File deletefile = new File(AUDIO_RECORD + temp.getName());
                        deletefile.delete();
                        mDatas.remove(position);
                        notifyDataSetChanged();
                        deletefile.getName();

                        Toast.makeText(MyRecordListActivity.this, temp.getName() + "已经删除:", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.setOnClickListener(R.id.btnTop, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SwipeBean temp = mDatas.get(position);
                        ((SwipeMenuLayout) holder.getConvertView()).quickClose();
                        mDatas.remove(position);
                        mDatas.add(0, temp);
                        Toast.makeText(MyRecordListActivity.this, "置顶成功" + temp.getName().toString(), Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
                holder.setOnClickListener(R.id.btnUpload, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
    }

    private void skbProgress() {
        skbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                value = progress * player.mediaPlayer.getDuration() / seekBar.getMax();
                skbProgress.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.mediaPlayer.seekTo(value);


            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("4444444444444", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("4444444444444", "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("4444444444444", "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("4444444444444", "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("4444444444444", "onResume: ");
        setAdapter();
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.Continue();
                btnContinue.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);

            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                btnContinue.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
            }
        });
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (player.mediaPlayer == null)
                    return;
                if (player.mediaPlayer.isPlaying() && skbProgress.isPressed() == false) {
                    handleProgress.sendEmptyMessage(0);
                }
            }
        };
        Timer mTimer = new Timer();
        //mTimer.schedule(mTimerTask, 0, 50);

    }

}
