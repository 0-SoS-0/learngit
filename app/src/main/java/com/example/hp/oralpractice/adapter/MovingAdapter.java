package com.example.hp.oralpractice.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.oralpractice.MainActivity;
import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.bean.gsonbean.FocusOnBean;
import com.example.hp.oralpractice.bean.gsonbean.MovingBean;
import com.example.hp.oralpractice.bean.gsonbean.MyFocusBean;
import com.example.hp.oralpractice.bean.gsonbean.PraiseBean;
import com.example.hp.oralpractice.bean.gsonbean.SendCommentBean;
import com.example.hp.oralpractice.ui.activity.RecordDetailOtherActivity;
import com.example.hp.oralpractice.webAPI.MyFocusOn;
import com.example.hp.oralpractice.webAPI.MyparisAPI;
import com.example.hp.oralpractice.webAPI.PraiseAPI;
import com.example.hp.oralpractice.webAPI.SendCommentAPI;
import com.example.hp.oralpractice.webService.MyFocusOnService;
import com.example.hp.oralpractice.webService.MyParisService;
import com.example.hp.oralpractice.webService.PraiseService;
import com.example.hp.oralpractice.webService.SendCommentService;

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

public class MovingAdapter extends RecyclerView.Adapter<MovingAdapter.ViewHolder> {


    private List<MovingBean> movingBeanList;
    private MainActivity mContext;

    public MovingAdapter(List<MovingBean> movingList, MainActivity context) {
        movingBeanList = movingList;
        mContext = context;
    }


    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view1;
        @BindView(R.id.user_Image)
        CircleImageView userImage;
        @BindView(R.id.user_Id)
        TextView userId;
        @BindView(R.id.user_nickname)
        TextView userNickname;
        @BindView(R.id.send_comment)
        EditText sendComment;
        @BindView(R.id.focus)
        Button focus;
        @BindView(R.id.record_type)
        TextView recordType;
        @BindView(R.id.online_play)
        ImageButton onlinePlay;
        @BindView(R.id.praise)
        ImageButton praise;
        @BindView(R.id.record_Disc)
        TextView recordDisc;
        @BindView(R.id.comment)
        ImageButton comment;
        @BindView(R.id.layout)
        LinearLayout layout;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view1=itemView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_move, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MovingBean movingBean = movingBeanList.get(position);
                Intent intent=new Intent(mContext, RecordDetailOtherActivity.class);
                intent.putExtra("user_Id","");
                intent.putExtra("record_Id","");
                mContext.startActivityForResult(intent,5);
            }
        });
        holder.focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MovingBean movingBean = movingBeanList.get(position);
                MyFocusOn myFocusOnAPI = new MyFocusOn();
                MyFocusOnService service = myFocusOnAPI.getService();
                Call<FocusOnBean> call_focus = service.getfocus(1, 1,1);
                call_focus.enqueue(new Callback<FocusOnBean>() {
                    @Override
                    public void onResponse(Call<FocusOnBean> call, Response<FocusOnBean> response) {

                    }

                    @Override
                    public void onFailure(Call<FocusOnBean> call, Throwable t) {

                    }
                });
            }
        });
        holder.praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MovingBean movingBean = movingBeanList.get(position);
                PraiseAPI praiseAPI = new PraiseAPI();
                PraiseService service = praiseAPI.getService();
                Call<PraiseBean> call_praise = service.getpraise(1, 1, 1);
                call_praise.enqueue(new Callback<PraiseBean>() {
                    @Override
                    public void onResponse(Call<PraiseBean> call, Response<PraiseBean> response) {

                    }

                    @Override
                    public void onFailure(Call<PraiseBean> call, Throwable t) {

                    }
                });
            }
        });
        holder.sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MovingBean movingBean = movingBeanList.get(position);
                SendCommentAPI commentAPI=new SendCommentAPI();
                SendCommentService service=commentAPI.getService();
                Call<SendCommentBean> call_comment=service.getsendcomment(1,1,1,"");
                call_comment.enqueue(new Callback<SendCommentBean>() {
                    @Override
                    public void onResponse(Call<SendCommentBean> call, Response<SendCommentBean> response) {

                    }

                    @Override
                    public void onFailure(Call<SendCommentBean> call, Throwable t) {

                    }
                });

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovingBean movingBean = movingBeanList.get(position);
        //holder.

    }

    @Override
    public int getItemCount() {
        return movingBeanList.size();
    }


}
