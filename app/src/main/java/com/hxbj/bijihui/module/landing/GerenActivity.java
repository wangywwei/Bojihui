package com.hxbj.bijihui.module.landing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.model.bean.OssBean;
import com.hxbj.bijihui.module.HomeActivity;
import com.hxbj.bijihui.module.quanming.shanchuan.ShangchuanActivity;
import com.hxbj.bijihui.utils.GlidUtils;
import com.hxbj.bijihui.utils.StringUtils;
import com.hxbj.bijihui.utils.ToastUtils;
import com.hxbj.bijihui.utils.popuo.ChangeDatePopwindow;

import org.apache.commons.lang.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 首次登陆后的个人资料页面
 *
 * */
public class GerenActivity extends BaseActivity implements View.OnClickListener,GerenContract.GerenView {

    private String sex="男";;
    private String imagePath;

    public static Intent getIntent(Context context, String shouci) {
        Intent intent = new Intent(context, GerenActivity.class);
        intent.putExtra("shouci", shouci);
        return intent;
    }
    private GerenContract.GerenPresenter gerenPresenter;
    private String shouci;
    private ImageView genren_touxiang;
    private EditText genren_name;
    private RadioButton nan;
    private RadioButton nv;
    private RadioButton baomi;
    private RadioGroup genren_xinbie;
    private EditText genren_shouquanma;
    private ImageView hulue;
    private ImageView baocun;
    private TextView shouquan1;
    private TextView shouquan2;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geren);
        shouci = getIntent().getStringExtra("shouci");
        initView();
        initData();
        if (shouci.equals("shouci")) {
            back.setVisibility(View.GONE);
            shouquan1.setText("授权码");
            shouquan2.setText("（可与您所在的俱乐部索要）");
            hulue.setVisibility(View.VISIBLE);
        } else {
            back.setVisibility(View.VISIBLE);
            shouquan1.setText("生日");
            genren_shouquanma.setHint("请输入生日");
            shouquan2.setVisibility(View.GONE);
            hulue.setVisibility(View.GONE);
        }

    }

    private void initData() {
        gerenPresenter=new GerenPresenter(this);
        GlidUtils.setGrid2(this, MyApp.instance.getPicUrl(),genren_touxiang);
        genren_name.setText(MyApp.instance.getNickname());
        switch (MyApp.instance.getSex()){
            case "男":
                nan.isChecked();
                break;
            case "女":
                nv.isChecked();
                break;
            case "保密":
                baomi.isChecked();
                break;
        }
        if (!shouci.equals("shouci")){
            genren_shouquanma.setText(MyApp.instance.getBirthday());
        }else {
            genren_shouquanma.setText(MyApp.instance.getKeyWord());
        }


    }

    private void initView() {
        genren_touxiang = (ImageView) findViewById(R.id.genren_touxiang);
        genren_name = (EditText) findViewById(R.id.genren_name);
        nan = (RadioButton) findViewById(R.id.nan);
        nv = (RadioButton) findViewById(R.id.nv);
        baomi = (RadioButton) findViewById(R.id.baomi);
        genren_xinbie = (RadioGroup) findViewById(R.id.genren_xinbie);
        genren_shouquanma = (EditText) findViewById(R.id.genren_shouquanma);
        hulue = (ImageView) findViewById(R.id.hulue);
        baocun = (ImageView) findViewById(R.id.baocun);
        shouquan1 = findViewById(R.id.shouquan1);
        shouquan2 = findViewById(R.id.shouquan2);
        back = findViewById(R.id.back);
        baocun.setOnClickListener(this);
        hulue.setOnClickListener(this);
        back.setOnClickListener(this);
        genren_touxiang.setOnClickListener(this);
        genren_shouquanma.setOnClickListener(this);

        genren_xinbie.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.nan:
                        sex="男";
                        break;
                    case R.id.nv:
                        sex="女";
                        break;
                    case R.id.baomi:
                        sex="保密";
                        break;
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.genren_touxiang:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);

                break;
            case R.id.hulue:
                startActivity(HomeActivity.getIntent(this));
                finish();
                break;
            case R.id.baocun:
                gerenPresenter.start();
                break;
            case R.id.back:
                startActivity(HomeActivity.getIntent(this));
                finish();
                break;
            case R.id.genren_shouquanma:
                if (!shouci.equals("shouci")){
                    selectDate();
                }
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            startActivity(HomeActivity.getIntent(this));
            finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

    private String[] selectDate() {
        final String[] str = new String[10];
        ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(
                this);
        mChangeBirthDialog.setDate("2016", "1", "1");
        mChangeBirthDialog.showAtLocation(genren_shouquanma, Gravity.BOTTOM, 0, 0);
        mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {

            @Override
            public void onClick(String year, String month, String day) {
                // TODO Auto-generated method stub
                Toast.makeText(GerenActivity.this,year + "-" + month + "-" + day,Toast.LENGTH_LONG).show();
                StringBuilder sb = new StringBuilder();
                sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, day.length() - 1)).append("-").append(day);
                str[0] = year + "-" + month + "-" + day;
                str[1] = sb.toString();
                genren_shouquanma.setText(year + "-" + month + "-" + day);

            }
        });
        return str;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            imagePath = c.getString(columnIndex);
            GlidUtils.setGrid2(this, imagePath,genren_touxiang);
            c.close();
        }
    }


    @Override
    public void setResult(LoginBean loginBean) {
        if (loginBean.getCode()==2000){
            MyApp.instance.saveLogin(loginBean.getData(),this);
            startActivity(HomeActivity.getIntent(this));
            finish();
        }
    }

    @Override
    public void setOSStoken(OssBean osStoken) {
            if (osStoken.getCode()==2000){
                accessKeySecret=osStoken.getData().getAccessKeySecret();
                accessKeyId=osStoken.getData().getAccessKeyId();
                securityToken=osStoken.getData().getSecurityToken();
            }
        if (!StringUtils.isBlank(imagePath)){
            setShangChuan(imagePath);
        }else {
            if (!shouci.equals("shouci")){
                gerenPresenter.start(genren_name.getText().toString().trim(),
                        sex,
                        MyApp.instance.getPicUrl(),
                        genren_shouquanma.getText().toString().trim(),
                        MyApp.instance.getKeyWord());

            }else {
                gerenPresenter.start(genren_name.getText().toString().trim(),
                        sex,
                        MyApp.instance.getPicUrl(),
                        MyApp.instance.getBirthday(),
                        genren_shouquanma.getText().toString().trim());
            }

        }

    }

    @Override
    public void setPresenter(GerenContract.GerenPresenter gerenPresenter) {
    this.gerenPresenter=gerenPresenter;
    }

    private String accessKeySecret;
    private String accessKeyId;
    private String securityToken;

    private void setShangChuan(String imagePath){
        if (StringUtils.isBlank(accessKeyId)){
            return;
        }
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考访问控制章节
        // 也可查看sample 中 sts 使用方式了解更多(https://github.com/aliyun/aliyun-oss-android-sdk/tree/master/app/src/main/java/com/alibaba/sdk/android/oss/app)
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(
                accessKeyId,
                accessKeySecret,
                securityToken);
        //该配置类如果不设置，会有默认配置，具体可看该类
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);

        String originalFilename=".jpg";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //重命名文件名称
        String fileName = sdf.format(new Date());
        //随机5位数
        String random  = RandomStringUtils.randomNumeric(5);
        if (originalFilename.lastIndexOf(".") != -1){
            fileName = fileName+random+originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        PutObjectRequest put = new PutObjectRequest("heixiong-club", "images/"+fileName, imagePath);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
//                Log.e("TAG--PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });
        final String finalFileName = fileName;
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                if (!shouci.equals("shouci")){
                    gerenPresenter.start(genren_name.getText().toString().trim(),
                            sex,
                            finalFileName,
                            genren_shouquanma.getText().toString().trim(),
                            MyApp.instance.getKeyWord());

                }else {
                    gerenPresenter.start(genren_name.getText().toString().trim(),
                            sex,
                            finalFileName,
                            MyApp.instance.getBirthday(),
                            genren_shouquanma.getText().toString().trim());
                }

            }
            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                ToastUtils.showToast(GerenActivity.this,"服务器异常");

            }
        });


    }
}
