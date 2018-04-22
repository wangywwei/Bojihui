package com.hxbj.bijihui.module.quanming.shanchuan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
import com.hxbj.bijihui.constants.Urls;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.bean.OssBean;
import com.hxbj.bijihui.network.HttpFactory;
import com.hxbj.bijihui.network.MyCallBack;
import com.hxbj.bijihui.utils.AppUtils;
import com.hxbj.bijihui.utils.LogUtils;
import com.hxbj.bijihui.utils.SPUtils;
import com.hxbj.bijihui.utils.StringUtils;
import com.hxbj.bijihui.utils.ToastUtils;
import com.hxbj.bijihui.video.CustomRecordActivity;
import com.hxbj.bijihui.video.VideoQuanpingActivity;
import com.hxbj.bijihui.video.VideoView;

import org.apache.commons.lang.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShangchuanActivity extends BaseActivity implements View.OnClickListener,VideoView.OnItemclickLinter {

    private VideoView videoView;

    public static Intent getIntent(Context context, String videoPath){
        Intent intent=new Intent(context,ShangchuanActivity.class);
        intent.putExtra("videoPath",videoPath);
        return intent;
    }
    private String videoPath;
    private ImageView back;
    private RelativeLayout bofangvidel;
    private ImageView chonglu;
    private ImageView shanchu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangchuan);
        videoPath=getIntent().getStringExtra("videoPath");
        LogUtils.e("TGA",videoPath);
        AppUtils.setTitle(this);
        initView();

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        bofangvidel = (RelativeLayout) findViewById(R.id.bofangvidel);
        chonglu = (ImageView) findViewById(R.id.chonglu);
        shanchu = (ImageView) findViewById(R.id.shanchu);

        back.setOnClickListener(this);
        chonglu.setOnClickListener(this);
        shanchu.setOnClickListener(this);

        videoView = new VideoView(this);
        videoView.setOnItemclickLinter(this);
        videoView.onDestroy();
        bofangvidel.addView(videoView);

        videoView.setStart(videoPath,"","");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.chonglu:
               startActivity(CustomRecordActivity.getIntent(ShangchuanActivity.this));
                finish();
                break;
            case R.id.shanchu:
                gettokent();
                break;

        }
    }


    private String accessKeySecret;
    private String accessKeyId;
    private String securityToken;

    public void gettokent() {
        HttpFactory.create().get(Urls.GETOSSTOKEN, null, new MyCallBack<OssBean>() {
            @Override
            public void onSuccess(OssBean ossBean) {
                if (ossBean.getCode()==2000){
                    accessKeySecret=ossBean.getData().getAccessKeySecret();
                    accessKeyId=ossBean.getData().getAccessKeyId();
                    securityToken=ossBean.getData().getSecurityToken();
                    setShangChuan();
                }
            }
            @Override
            public void onFaile(String msg) {

            }
        });


    }

    private void setShangChuan(){
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

        String originalFilename=".mp4";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //重命名文件名称
        String fileName = sdf.format(new Date());
        //随机5位数
        String random  = RandomStringUtils.randomNumeric(5);
        if (originalFilename.lastIndexOf(".") != -1){
            fileName = fileName+random+originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        PutObjectRequest put = new PutObjectRequest("heixiong-club", "video/"+fileName, videoPath);
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
                Map<String ,String> map=new HashMap<>();
                map.put("iphone", MyApp.instance.getIphone());
                map.put("userId", MyApp.instance.getId());
                map.put("videoUrl", finalFileName);
//                map.put("videoType", videoType);
                HttpFactory.create().post(Urls.UPDATEVIDEO, map, new MyCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        finish();
                    }

                    @Override
                    public void onFaile(String msg) {

                    }
                });


            }
            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常

                ToastUtils.showToast(ShangchuanActivity.this,"服务器异常");
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("TAG--ErrorCode", serviceException.getErrorCode());
                    Log.e("TAG--RequestId", serviceException.getRequestId());
                    Log.e("TAG--HostId", serviceException.getHostId());
                    Log.e("TAG--RawMessage", serviceException.getRawMessage());
                }
            }
        });


    }


    @Override
    protected void onDestroy() {
        if (videoView!=null){
            videoView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onItemClicj(View view) {
        startActivityForResult(VideoQuanpingActivity.getIntent(this,
                videoPath,"","",videoView.getProgress()),100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            int jindu =bundle.getInt("jindu");
            videoView.setJindu(jindu);
        }
    }
}
