package com.example.hp.oralpractice.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hp.oralpractice.R;
import com.example.hp.oralpractice.bean.gsonbean.Inform_EditBean;
import com.example.hp.oralpractice.bean.gsonbean.User;
import com.example.hp.oralpractice.bean.gsonbean.UserPortraitGsonBean;
import com.example.hp.oralpractice.dialog.PSDialog;
import com.example.hp.oralpractice.util.LocalGetImageUtil;
import com.example.hp.oralpractice.webAPI.Inform_EditAPI;
import com.example.hp.oralpractice.webAPI.MyPotraitAPI;
import com.example.hp.oralpractice.webService.Inform_EditService;
import com.example.hp.oralpractice.webService.MyPortraitService;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import it.sephiroth.android.library.picasso.Picasso;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 2017/12/3.
 */

public class MyPersonInfoActivity extends BaseActivity implements View.OnClickListener {
    //    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;
    @BindView(R.id.info_toolbar)
    Toolbar toolbar;
    @BindView(R.id.nickname)
    EditText nickname;
    @BindView(R.id.sign)
    EditText sign;
    @BindView(R.id.education)
    EditText education;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.cancel)
    Button cancel;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.person_image)
    CircleImageView personImage;
    @BindView(R.id.layout)
    LinearLayout layout;

    private Uri image_head_uri = null;
    private User user = User.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initUI();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_hint_inform, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.user_edit_inform:
                cancel.setVisibility(View.GONE);
                submit.setVisibility(View.GONE);
                break;
            case R.id.user_edit_password:
                Dialog dialogx = new PSDialog(MyPersonInfoActivity.this,
                        R.style.recorddialog);
                dialogx.setContentView(R.layout.activity_setting_user_password_dialog);
                dialogx.show();
                break;
            case R.id.singout:
                Intent intent = new Intent(MyPersonInfoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            default:
                break;
        }
        return true;
    }

    private void initUI() {
        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);
        personImage.setOnClickListener(this);


        Picasso.with(MyPersonInfoActivity.this).
                load(user.getUserportrait_url()).
                error(R.drawable.someone)
                .placeholder(R.drawable.someone)
                .into(personImage);
        Picasso.with(MyPersonInfoActivity.this)
                .load(user.getUserportrait_url())
                .into(personImage);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }


    //检查个人信息
    private void CheckInfo() {



    }

    @Override
    public void onClick(View v) {
        if (v.equals(cancel)) {
            cancel.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);
        }
        if (v.equals(submit)) {

            CheckInfo();
        }
        if (v.equals(personImage)) {
            LocalGetImageUtil util = new LocalGetImageUtil(MyPersonInfoActivity.this);
            util.ChangeheadImage();
            util.setOnGetTypeClckListener(new LocalGetImageUtil.onGetTypeClckListener() {
                @Override
                public void getImgUri(Uri ImgUri, File file) {
                    image_head_uri = ImgUri;
                    // System.out.println("getImgUrigetImgUri" + image_head_uri + "   byProtrait" + " " + selfId);
                }
            });
        }
    }


    public String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                startPhoneZoom(image_head_uri);
                break;
            case 2:
                if (data != null) {
                    Cursor cursor = this.getContentResolver().query(data.getData(),
                            new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                    //游标移到第一位，即从第一位开始读取
                    cursor.moveToFirst();
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//                    Uri uri = Uri.fromFile(new File(path));
//                    image_head_uri = uri;
                    cursor.close();
                    //调用系统裁剪
                    startPhoneZoom(Uri.fromFile(new File(path)));
                }
                break;
            case 3://返回裁剪结果
                //设置裁剪返回的位图
                Bundle bundle = null;
                if (data != null) {
                    bundle = data.getExtras();
                }
                if (bundle != null) {
                    Bitmap bitmap = bundle.getParcelable("data");
                    //将裁剪后得到的位图在组件中显示
                    Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
                    personImage.setImageBitmap(bitmap);
                    image_head_uri = uri;
                    SaveHead();
                }
                break;
        }
    }


    //裁剪器
    private void startPhoneZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //是否可裁剪
        intent.putExtra("corp", "true");
        //裁剪器高宽比
        intent.putExtra("aspectY", 1);
        intent.putExtra("aspectX", 1);
        //设置裁剪框高宽
        intent.putExtra("outputX", 120);
        intent.putExtra("outputY", 120);
        //返回数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }


    private void SaveHead() {
        MyPotraitAPI myPotraitAPI = new MyPotraitAPI();
        MyPortraitService myPortraitS = myPotraitAPI.getService();
        File file = new File(getRealPathFromUri(image_head_uri));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("portrait", file.getName(), requestFile);
        Log.d("mmm", "SaveHead: " + " file " + file.getName());
        Call<UserPortraitGsonBean> call_userportrait = myPortraitS.getportrait(user.getUserID(), body);
        call_userportrait.enqueue(new Callback<UserPortraitGsonBean>() {
            @Override
            public void onResponse(Call<UserPortraitGsonBean> call, Response<UserPortraitGsonBean> response) {
                if (response.body() != null) {
                    UserPortraitGsonBean userPortraitGsonBean = response.body();
                    String status = userPortraitGsonBean.getStatus();
                    if (status.equals("0")) {
                        // ChangeFailed("头像更改失败");
                    } else {
                        String url = userPortraitGsonBean.getPortrait();
                        if (url != null) {
                            Picasso.with(MyPersonInfoActivity.this).load(url).fit()
                                    .placeholder(R.drawable.someone)
                                    .error(R.drawable.someone)
                                    .into(personImage);
                            user.setUserportrait_url(url);
                            System.out.println("PicassoPicasso:" + url + "   byProtrait" + " " + url);
                        } else {
                            System.out.println("url is null");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserPortraitGsonBean> call, Throwable t) {

            }
        });
    }

    private void SaveInfo(final String nickname, final String phonenum, final String gender, String dis, String educ, String home) {
        Inform_EditAPI changeapi = new Inform_EditAPI();
        Inform_EditService changeservice = changeapi.getService();
        Call<Inform_EditBean> call_change = changeservice.getInform_Edit(user.getUserID(), nickname, gender, dis, educ, home);
        call_change.enqueue(new Callback<Inform_EditBean>() {
            @Override
            public void onResponse(Call<Inform_EditBean> call, Response<Inform_EditBean> response) {
                Log.d("edit_onFailureByPor", response.toString());
                if (response.body() != null) {
                    Inform_EditBean manageUserGsonBean = response.body();
                    String state = manageUserGsonBean.getStatus();
                    Log.d("MyListeGsonBean", state + "00000000000");
                    if (state.equals("1")) {
                        user.setUsername(nickname);
                        //user.setUseraccount(phonenum);
                        user.setUserGender(gender);
//                        nickName.setText(nickname);
//                        phoneNun.setText(phonenum);
//                        gerdenhint.setText(gender);
                        //SaveInfor(nickname, phonenum, gender);
                        // handler.postDelayed(saveResult, 100);
                        Toast.makeText(MyPersonInfoActivity.this, "修改成功...", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MyPersonInfoActivity.this, "修改不成功", Toast.LENGTH_SHORT).show();
                }
                Log.d("edit_onFailureByPor", response.toString());

            }

            @Override
            public void onFailure(Call<Inform_EditBean> call, Throwable t) {
                Toast.makeText(MyPersonInfoActivity.this, "连接失败，请检查网络设置", Toast.LENGTH_SHORT).show();
                Log.d("edit_onFailureByPor", String.valueOf(t));
            }
        });
    }


}
