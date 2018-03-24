package com.example.hp.oralpractice.ui.activity;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.dialog.RecordignDialog;
import com.example.hp.oralpractice.util.EnvironmentShare;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2018/3/13.
 */

public class RecordingActivity extends BaseActivity {
    private static final String LOG_TAG = "AudioRecordTest";
    //语音文件保存路径
    private String FileName = null;
    @BindView(R.id.startPlay)
    Button startPlay;
    private boolean isplaying;
    private ImageView mPressToSay;
    private TextView hint;
    private ExecutorService executorService;

    private MediaPlayer mPlayer = null;
    private MediaRecorder mMediaRecorder = null;
    // private MediaRecorder mMediaRecorder;
    private File mAudioFile , dir;
    private long mStartRecorderTime, mStopRecorderTime;
    private Handler mMainThreadHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);

        mPressToSay = (ImageView) findViewById(R.id.mPressToSay);
        hint = (TextView) findViewById(R.id.hint);
        //设置sdcard的路径
        FileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        FileName += "/Oral_Practice/voice";
        Log.d("00000000", "onCreate: " + FileName);
        //录音 JIN 函数不具备线程安全性，要用单线程
        executorService = Executors.newSingleThreadExecutor();
        mMainThreadHandler = new Handler(Looper.getMainLooper());

        mPressToSay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startRecord();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        Log.d("11111", "onTouch: ");
                        stopRecord();
                        Dialog dialogx = new RecordignDialog(RecordingActivity.this,
                                R.style.recorddialog);
                        dialogx.setContentView(R.layout.activity_recordinghint);
                        dialogx.show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        //播放录音
        startPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPlayer = new MediaPlayer();
                isplaying=mPlayer.isPlaying();

                if (isplaying==true){

                    mPlayer.release();
                    mPlayer.pause();
                }else {
                    try {
                        mPlayer.setDataSource("/storage/emulated/0/qqmusic/song/TFBOYS - 宠爱 [mqms2].mp3");
                        //File time = new File("/storage/emulated/0/qqmusic/song/TFBOYS - 宠爱 [mqms2].mp3");
                        mPlayer.prepare();
                        mPlayer.start();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "播放失败");
                    }
                }

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity 销毁时，停止后台任务，避免内存泄漏
        executorService.shutdownNow();
        releaseRecorder();
        //stopPlay();
    }

    private void startRecord() {
        hint.setText(R.string.speaking);
        mPressToSay.setBackgroundResource(R.color.md_green_A100);
        //提交录音任务，开始录音
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //释放录音Mediarecorder
                releaseRecorder();
                //失败提示
                if (!doStart()) {
                    Log.d("0000", "run: " + doStart());
                    recoredFail();
                }
            }
        });
    }

    private void stopRecord() {
        Log.d("11111", "onTouch: ");
        hint.setText(R.string.press_to_say);
        mPressToSay.setBackgroundResource(R.color.md_green_A200);
        //提交后台任务，停止录音
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //失败提示
                if (!doStop()) {
                    Log.d("2222", "onTouch: ");
                    recoredFail();
                }
                releaseRecorder();
            }
        });
    }


    /*
    * 录音逻辑
    * */
    private boolean doStart() {


        if (!EnvironmentShare.haveSdCard()) {
            Toast.makeText(this, "SD不存在，不正常录音！！", Toast.LENGTH_LONG).show();
        } else {
            try {
                //创建 MediaRecorder
                mMediaRecorder = new MediaRecorder();
                //创建录音文件
                //配置 MediaRecorder
                //从麦克风采集
                mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                //设置保存格式
                mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                //音频采样频率
                mMediaRecorder.setAudioSamplingRate(44100);
                //通用 AAC 编码格式
                mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                //音质比较好的编码率   /* 编码率  */
                mMediaRecorder.setAudioEncodingBitRate(96000);
                // 创建一个临时的音频输出文件.record_是文件的前缀名 .amr是后缀名
                mAudioFile = File.createTempFile("record_", ".amr", EnvironmentShare.getAudioRecordDir());
                // 设置录音文件位置
                mMediaRecorder.setOutputFile(mAudioFile.getAbsolutePath());
                Log.d("333333333333", "doStart: "+mAudioFile.getAbsolutePath());
                //开始录音
                mMediaRecorder.prepare();
                mMediaRecorder.start();
                //统计时长
                // mStartRecorderTime = System.currentTimeMillis();

                //录音成功
            } catch (IOException | RuntimeException e) {
                Log.d("Eror", "doStart: " + e);

                e.printStackTrace();
                //捕获异常，避免闪退，返回false 提醒用户
                return false;
            }

        }
        return true;
    }

    private boolean doStop() {

        Log.d("00000000000", "doStop: ");
        try {
            mMediaRecorder.stop();
            //mStopRecorderTime = System.currentTimeMillis();
           /* final int second= (int) (mStartRecorderTime-mStopRecorderTime)/1000;
            if (second>=3){
                mMainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTvLog.setText(mTvLog.getText()+"\n 录音成功"+second+"秒");
                    }
                });
                return true;
            }*/
            // final int second = (int) (mStartRecorderTime - mStopRecorderTime) / 1000;
            mMainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    // mTvLog.setText(mTvLog.getText() + "\n 录音成功");
                }
            });
        } catch (RuntimeException e) {
            Log.d("Eror", "doStop: " + e);
            e.printStackTrace();
            //e.printStackTrace();
            //捕获异常，避免闪退，返回false 提醒用户
            return false;
        }
        return true;
    }

    /*
   * 释放MediaRecorder
   * */
    private void releaseRecorder() {
        //检查 MediaRecoder 不为空
        if (mMediaRecorder != null) {
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
    }

    /*
   * 失败处理*/
    private void recoredFail() {
        mAudioFile = null;
        mMainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("00000", "run:失败 ");
                Toast.makeText(RecordingActivity.this, "录音失败...", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
