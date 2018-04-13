package com.hxbj.bijihui.module.quanming;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.tencent.rtmp.TXLiveBase;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.module.quanming.shanchuan.ShangchuanActivity;
import com.hxbj.bijihui.utils.LogUtils;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.ugc.TXRecordCommon;
import com.tencent.ugc.TXUGCPartsManager;
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

    private TXCloudVideoView lv_video;

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
        iniquanxian();
    }

    private void iniquanxian() {
        LogUtils.e("TGA","1");
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            LogUtils.e("TGA","2");
            //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {//这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限
                LogUtils.e("TGA","shouldShowRequestPermissionRationale");

            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果

            }
        }
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA
                        ,Manifest.permission.RECORD_AUDIO
                        ,Manifest.permission.ACCESS_FINE_LOCATION
                        ,Manifest.permission.ACCESS_COARSE_LOCATION
                        ,Manifest.permission.READ_EXTERNAL_STORAGE
                        ,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        LogUtils.e("TGA","requestPermissions");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void initView() {
        lv_video = findViewById(R.id.lv_video);
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
        mTXCameraRecord.setVideoRecordListener(this);//设置录制回调
        //准备一个预览摄像头画面的
        lv_video.enableHardwareDecode(true);
        TXRecordCommon.TXUGCSimpleConfig param = new TXRecordCommon.TXUGCSimpleConfig();
        //param.videoQuality = TXRecordCommon.VIDEO_QUALITY_LOW;        // 360p
        param.videoQuality = TXRecordCommon.VIDEO_QUALITY_MEDIUM;        // 540p
        //param.videoQuality = TXRecordCommon.VIDEO_QUALITY_HIGH;        // 720p
        param.isFront = false;           //是否前置摄像头，使用
        param.minDuration = 1000;    //视频录制的最小时长ms
        param.maxDuration = 30000;    //视频录制的最大时长ms

        mTXCameraRecord.startCameraSimplePreview(param, lv_video);



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
                } else {
                    LogUtils.e("TGA","结束");
                    paishezhuantai = 1;
                    lu_start.setVisibility(View.GONE);
                    lu_fou.setVisibility(View.VISIBLE);
                    lu_yes.setVisibility(View.VISIBLE);
                    lu_start.setImageResource(R.drawable.paishe);
                    mTXCameraRecord.stopRecord();
                    mTXCameraRecord.pauseRecord();

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
