package com.example.hp.oralpractice.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.adapter.RecordDetailOtherAdapter;
import com.example.hp.oralpractice.bean.gsonbean.GetCommentBean;
import com.example.hp.oralpractice.bean.gsonbean.MyparisBean;
import com.example.hp.oralpractice.bean.gsonbean.RecordDetailOtherBean;
import com.example.hp.oralpractice.bean.gsonbean.SendCommentBean;
import com.example.hp.oralpractice.util.Player;
import com.example.hp.oralpractice.webAPI.GetCommentAPI;
import com.example.hp.oralpractice.webAPI.MyparisAPI;
import com.example.hp.oralpractice.webAPI.RecordDetailOtherAPI;
import com.example.hp.oralpractice.webAPI.SendCommentAPI;
import com.example.hp.oralpractice.webService.GetCommentService;
import com.example.hp.oralpractice.webService.MyParisService;
import com.example.hp.oralpractice.webService.RecordDetailOtherService;
import com.example.hp.oralpractice.webService.SendCommentService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asus on 2018/3/19.
 */

public class RecordDetailOtherActivity extends BaseActivity {
    @BindView(R.id.user_Image)
    CircleImageView userImage;
    @BindView(R.id.user_Id)
    TextView userId;
    @BindView(R.id.user_nickname)
    TextView userNickname;
    @BindView(R.id.focus)
    Button focus;
    @BindView(R.id.record_type)
    TextView recordType;
    @BindView(R.id.btnPlayUrl)
    ImageButton btnPlayUrl;
    @BindView(R.id.btnPause)
    ImageButton btnPause;
    @BindView(R.id.Continue)
    ImageButton btnContinue;
    @BindView(R.id.record_Disc)
    TextView recordDisc;
    @BindView(R.id.skbProgress)
    SeekBar skbProgress;
    @BindView(R.id.comment_context)
    EditText comment;
    @BindView(R.id.comment_num)
    TextView commentNum;
    @BindView(R.id.parise_num)
    TextView pariseNum;
    @BindView(R.id.recyclerView_comment_other)
    RecyclerView recyclerViewCommentOther;
    @BindView(R.id.send)
    Button send;
    @BindView(R.id.parise)
    ImageButton parise;

    private List<RecordDetailOtherBean> detailOtherBeanList = new ArrayList<>();
    private Player player;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_record_detail_otheruser);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        RecordDetailOtherAPI otherAPI=new RecordDetailOtherAPI();
        RecordDetailOtherService service=otherAPI.getService();
        Call<RecordDetailOtherBean> call_record=service.getRecord(1,1);
        call_record.enqueue(new Callback<RecordDetailOtherBean>() {
            @Override
            public void onResponse(Call<RecordDetailOtherBean> call, Response<RecordDetailOtherBean> response) {

                adapter();
            }

            @Override
            public void onFailure(Call<RecordDetailOtherBean> call, Throwable t) {

            }
        });
        btnPlayUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在百度MP3里随便搜索到的,大家可以试试别的链接
                hint("准备播放。。。");
                String url="http://link.hhtjim.com/163/32507038.mp3";
                player.playUrl(url);
                btnPlayUrl.setVisibility(View.GONE);
                skbProgress.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.VISIBLE);

            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                btnPlayUrl.setVisibility(View.GONE);
                btnPause.setVisibility(View.GONE);
                btnContinue.setVisibility(View.VISIBLE);
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.Continue();
                btnPlayUrl.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
                btnContinue.setVisibility(View.GONE);
            }
        });
        parise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parise();
            }
        });
        commentNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getComment();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String context=comment.getText().toString();
                send(context);
            }
        });

    }


    private void parise() {
        MyparisAPI myparisAPI = new MyparisAPI();
        MyParisService myParisService = myparisAPI.getService();
        Call<MyparisBean> call_praise = myParisService.getpraise(1, 1, 1);
        call_praise.enqueue(new Callback<MyparisBean>() {
            @Override
            public void onResponse(Call<MyparisBean> call, Response<MyparisBean> response) {
                adapter();
            }

            @Override
            public void onFailure(Call<MyparisBean> call, Throwable t) {

            }
        });
    }

    private void adapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerViewCommentOther.setLayoutManager(layoutManager);
        RecordDetailOtherAdapter adapter=new RecordDetailOtherAdapter(detailOtherBeanList);
        recyclerViewCommentOther.setAdapter(adapter);
    }

    private void getComment() {
        GetCommentAPI getCommentAPI = new GetCommentAPI();
        GetCommentService service = getCommentAPI.getService();
        Call<GetCommentBean> call_sendComment = service.getComment(1,1);
        call_sendComment.enqueue(new Callback<GetCommentBean>() {
            @Override
            public void onResponse(Call<GetCommentBean> call, Response<GetCommentBean> response) {

            }

            @Override
            public void onFailure(Call<GetCommentBean> call, Throwable t) {

            }
        });
    }
    private void send(String context) {
        SendCommentAPI sendCommentAPI=new SendCommentAPI();
        SendCommentService service=sendCommentAPI.getService();
        Call<SendCommentBean> call_sendComment=service.getsendcomment(1,1,1,context);
        call_sendComment.enqueue(new Callback<SendCommentBean>() {
            @Override
            public void onResponse(Call<SendCommentBean> call, Response<SendCommentBean> response) {
                if (response.body()!=null){

                }
            }

            @Override
            public void onFailure(Call<SendCommentBean> call, Throwable t) {

            }
        });
    }


    private String hint(String msg) {
        Toast.makeText(RecordDetailOtherActivity.this,msg,Toast.LENGTH_LONG).show();
        if (player.mediaPlayer==null){
            Toast.makeText(RecordDetailOtherActivity.this,"播放停止",Toast.LENGTH_LONG).show();
        }

        return "";

    }

}
