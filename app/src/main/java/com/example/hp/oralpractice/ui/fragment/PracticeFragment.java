package com.example.hp.oralpractice.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.ui.activity.RecordingActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lsp.com.library.FlowLayout;


/**
 * Created by HP on 2017/10/25.
 */

public class PracticeFragment extends BaseFragment  {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lianlian)
    TextView lianlian;
    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;
    @BindView(R.id.select)
    LinearLayout select;
    @BindView(R.id.add_descrip)
    LinearLayout addDescrip;
    @BindView(R.id.edt_description)
    EditText edt_description;
    @BindView(R.id.submmit)
    Button submmit;
    Unbinder unbinder;
    View view;


    private static PracticeFragment fragment = null;

    private String[] tags = new String[]{"初级面试", "情景会话", "求职面试", "VOA慢速", "音标发音", "英语六级", "民族语言", "地方方言", "标准国语"};
    private static String tag;
    private static String describ;

    public static PracticeFragment getInstance() {

        Bundle bundle = new Bundle();
        bundle.putString("theme", tag);
        bundle.putString("introduce", describ);
        if (fragment == null) {
            fragment = new PracticeFragment();
            fragment.setArguments(bundle);
        }
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_practice, container, false);
        unbinder = ButterKnife.bind(this, view);
        showTag();
        initUI();



        return view;
    }
    public void showTag(){
        flowLayout.initData(Arrays.asList(tags), 4, R.drawable.tag_shape);
        flowLayout.setOnTabClickListener(new FlowLayout.IOnTabClickListener() {
        @Override
        public void onTabClick(int i, TextView textView) {
            for (int j = 0; j < flowLayout.getChildCount(); j++) {
                if (j == i) {
                    tag = tags[j];
                    textView.setBackgroundResource(R.drawable.tag_pressed_shape);
                    textView.setSelected(true);
                    //textView.setTextColor(Color.parseColor("#FFFFFF"));
                    Toast.makeText(fragment.getActivity(), i + " " + textView.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    flowLayout.getChildAt(j).setBackgroundResource(R.drawable.tag_shape);
                }
            }

        }
    });
}



    private void initUI(){
    submmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String des = edt_description.getText().toString();

                if (tag ==null) {
                    Toast.makeText(fragment.getActivity(), "请选择类型", Toast.LENGTH_LONG);
                }

            else if (des ==null){
                Toast.makeText(fragment.getActivity(),"请添加描述",Toast.LENGTH_LONG);
            }
            else {
                describ =des;
                    Intent intent =new Intent(fragment.getActivity(), RecordingActivity.class);
                    startActivity(intent);
            }
        }
    });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();



    }


}
