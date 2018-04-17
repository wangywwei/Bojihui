package com.hxbj.bijihui.video;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.module.quanming.shanchuan.ShangchuanActivity;
import com.hxbj.bijihui.utils.LogUtils;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.ugc.TXRecordCommon;
import com.tencent.ugc.TXUGCRecord;

/*
 * 录制视频页面
 * */
public class LuVideoActivity extends AppCompatActivity implements View.OnClickListener, TXRecordCommon.ITXVideoRecordListener {

    private TXUGCRecord mTXCameraRecord;
    private int paishezhuantai = 1;
    private String videoPath;
    private ImageView lu_fou;
    private ImageView lu_yes;
    private ImageView lu_start;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, LuVideoActivity.class);
        return intent;
    }

    private TXCloudVideoView mVideoView;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && Build.VERSION.SDK_INT >= 19){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lu_video);
        initView();

    }



    private void initView() {
        mVideoView = findViewById(R.id.lv_video);//设置录制回调//准备一个预览摄像头画面的
        lu_start = findViewById(R.id.lu_start);
        lu_fou = findViewById(R.id.lu_fou);
        lu_yes = findViewById(R.id.lu_yes);
        lu_yes.setOnClickListener(this);
        lu_fou.setOnClickListener(this);
        lu_start.setOnClickListener(this);
        /*
        * 刚开始进来默认影藏
        * */
        mTXCameraRecord = TXUGCRecord.getInstance(this.getApplicationContext());
        mTXCameraRecord.setVideoRecordListener(this);
        mVideoView.enableHardwareDecode(true);
        TXRecordCommon.TXUGCSimpleConfig param = new TXRecordCommon.TXUGCSimpleConfig();
//param.videoQuality = TXRecordCommon.VIDEO_QUALITY_LOW;        // 360p
        param.videoQuality = TXRecordCommon.VIDEO_QUALITY_MEDIUM;        // 540p
//param.videoQuality = TXRecordCommon.VIDEO_QUALITY_HIGH;        // 720p
        param.isFront = false;           //是否前置摄像头，使用
        param.minDuration = 1000;    //视频录制的最小时长ms
        param.maxDuration = 60000;    //视频录制的最大时长ms
        mTXCameraRecord.startCameraSimplePreview(param,mVideoView);

        // 切换前后摄像头 参数 mFront 代表是否前置摄像头 默认前置
//        mTXCameraRecord.switchCamera(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lu_start:
                if (paishezhuantai == 1) {
                    lu_fou.setVisibility(View.GONE);
                    lu_yes.setVisibility(View.GONE);
                    lu_start.setImageResource(R.drawable.zanting_3);
                    paishezhuantai = 2;
                    mTXCameraRecord.startRecord();
                    LogUtils.e("TGA","开始");
                } else {
                    paishezhuantai = 1;
                    lu_start.setVisibility(View.GONE);
                    lu_fou.setVisibility(View.VISIBLE);
                    lu_yes.setVisibility(View.VISIBLE);
                    lu_start.setImageResource(R.drawable.paishe);
                    mTXCameraRecord.stopRecord();
                    mTXCameraRecord.pauseRecord();
                    LogUtils.e("TGA","结束");
                }
                break;
            case R.id.lu_fou:
                lu_start.setImageResource(R.drawable.paishe);
                lu_start.setVisibility(View.VISIBLE);
                lu_fou.setVisibility(View.GONE);
                lu_yes.setVisibility(View.GONE);
                break;
            case R.id.lu_yes:
                startActivity(ShangchuanActivity.getIntent(LuVideoActivity.this, videoPath));
                finish();
                break;
        }
    }


    //onRecordEvent 录制事件回调
    @Override
    public void onRecordEvent(int i, Bundle bundle) {


    }

    //onRecordProgress用于反馈录制的进度
    @Override
    public void onRecordProgress(long l) {


    }

    //onRecordComplete 反馈录制的结果
    @Override
    public void onRecordComplete(TXRecordCommon.TXRecordResult txRecordResult) {
        videoPath = txRecordResult.videoPath;
        LogUtils.e("TGA",videoPath+"");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
