package com.hxbj.bijihui.module;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.base.FragmentManager;
import com.hxbj.bijihui.module.geren.LianxiActivity;
import com.hxbj.bijihui.module.home.HomeFragment;
import com.hxbj.bijihui.module.landing.GerenActivity;
import com.hxbj.bijihui.module.landing.LandingActivity;
import com.hxbj.bijihui.module.web.WebViewCurrencyActivity;
import com.hxbj.bijihui.utils.AppUtils;
import com.hxbj.bijihui.utils.AudioRecoderUtils;
import com.hxbj.bijihui.utils.LogUtils;
import com.hxbj.bijihui.utils.SPUtils;
import com.hxbj.bijihui.utils.StringStatic;
import com.hxbj.bijihui.utils.StringUtils;
import com.hxbj.bijihui.utils.TimeUtils;
import com.hxbj.bijihui.view.ArcProgress;
import com.hxbj.bijihui.view.OnTextCenter;

import java.io.IOException;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private AudioRecoderUtils mAudioRecoderUtils;
    private ImageView yinyue;
    private ArcProgress myProgress;
    private Thread thread;
    private TextView mubiao;
    private TextView time;
    private ImageView bofang;
    private LinearLayout mubiaolinear;


    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    private ImageView cehualan;
    private FrameLayout homeframe;
    private ActionBarDrawerToggle drawerbar;
    public DrawerLayout activity_na;
    //侧滑栏头部
    private ImageView cehua_touxiang;
    private TextView cehua_name;
    private TextView cehua_huiyuan;
    private TextView cehua_qianjun;
    private TextView cehua_lianxi;
    private TextView cehua_back;

    private PopupWindow popupWindow;
    private PopupWindow chehuapo;
    private RelativeLayout main_left_drawer_layout;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AppUtils.setTitle(this);
        initView();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (StringUtils.isBlank((String) SPUtils.get(HomeActivity.this, StringStatic.DIYICI,""))){
                    initData();
                }
//                initData();
            }
        }, 100);

    }


    private void initView() {
        cehualan = findViewById(R.id.cehualan);
        homeframe = findViewById(R.id.homeframe);

        activity_na = findViewById(R.id.activity_na);
        main_left_drawer_layout = findViewById(R.id.main_left_drawer_layout);
        cehua_touxiang = findViewById(R.id.cehua_touxiang);
        cehua_name = findViewById(R.id.cehua_name);
        cehua_huiyuan = findViewById(R.id.cehua_huiyuan);
        cehua_qianjun = findViewById(R.id.cehua_qianjun);
        cehua_lianxi = findViewById(R.id.cehua_lianxi);
        cehua_back = findViewById(R.id.cehua_back);


        cehualan.setOnClickListener(this);
        cehua_qianjun.setOnClickListener(this);
        cehua_lianxi.setOnClickListener(this);
        cehua_touxiang.setOnClickListener(this);
        cehua_back.setOnClickListener(this);
        FragmentManager.changeFragment(HomeFragment.class, R.id.homeframe, true, false);

        mediaPlayer=new MediaPlayer();
        mediaPlayer.reset();

        mAudioRecoderUtils = new AudioRecoderUtils();

        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time2) {
                //根据分贝值来设置录音时话筒图标的上下波动，下面有讲解
                myProgress.setProgress((int)(time2/1000));
                if (myProgress.getProgress()>=10){
                    myProgress.setVisibility(View.GONE);
                    mubiao.setVisibility(View.VISIBLE);
                    time.setVisibility(View.VISIBLE);
                    time.setText(TimeUtils.getTimedanqian()+"目标");
                    bofang.setVisibility(View.VISIBLE);
                    //结束录音（保存录音文件）
                    mAudioRecoderUtils.stopRecord();
                }

            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                LogUtils.e("TAG", filePath);
                SPUtils.put(HomeActivity.this, StringStatic.FILEPATH,filePath);
                try {
                    mediaPlayer.setDataSource(filePath);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private ImageView luzhi;
    private LinearLayout popo;
    private View popoview;

    private void initData() {
        SPUtils.put(HomeActivity.this, StringStatic.DIYICI,"111");
        View view = getLayoutInflater().inflate(R.layout.home_popup, null);
        popupWindow = new PopupWindow(view);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //关闭事件
        popupWindow.setOnDismissListener(new popupDismissListener());
        backgroundAlpha(0.5f);

        luzhi = view.findViewById(R.id.luzhi);
        popo = view.findViewById(R.id.popo);
        yinyue = view.findViewById(R.id.yinyue);
        mubiao = view.findViewById(R.id.mubiao);
        time = view.findViewById(R.id.time);
        bofang = view.findViewById(R.id.bofang);
        mubiaolinear = view.findViewById(R.id.mubiaolinear);

        myProgress= view.findViewById(R.id.myProgress);

        myProgress.setOnCenterDraw(new OnTextCenter());

        myProgress.setVisibility(View.GONE);
        mubiao.setVisibility(View.GONE);
        time.setVisibility(View.GONE);
        bofang.setVisibility(View.GONE);
        mubiaolinear.setVisibility(View.GONE);

        ImageView guanbi = view.findViewById(R.id.guanbi);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        setProgressBarVisibility(true);
        luzhi.setOnTouchListener(onTouchListener);

        bofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){

                }else {
                    mediaPlayer.start();
                }
            }
        });
    }

    View.OnTouchListener onTouchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                //按下
                case MotionEvent.ACTION_DOWN:
                    mubiaolinear.setVisibility(View.VISIBLE);
                    yinyue.setVisibility(View.GONE);
                    myProgress.setVisibility(View.VISIBLE);

                    mubiao.setVisibility(View.GONE);
                    time.setVisibility(View.GONE);
                    bofang.setVisibility(View.GONE);
                    mAudioRecoderUtils.startRecord();


                    break;
                //抬起
                case MotionEvent.ACTION_UP:
                    if (myProgress.getProgress()>=10){

                    }else {
                        myProgress.setVisibility(View.GONE);
                        mubiao.setVisibility(View.VISIBLE);
                        time.setVisibility(View.VISIBLE);
                        time.setText(TimeUtils.getTimedanqian()+"目标");
                        bofang.setVisibility(View.VISIBLE);
                        //结束录音（保存录音文件）
                        mAudioRecoderUtils.stopRecord();
                    }


                    break;
            }
            return true;
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //侧滑菜单键
            case R.id.cehualan:
                if (activity_na.isDrawerOpen(main_left_drawer_layout)) {
                    activity_na.closeDrawer(main_left_drawer_layout);
                } else {
                    activity_na.openDrawer(main_left_drawer_layout);
                }

                break;
            case R.id.cehua_qianjun:
                startActivity(WebViewCurrencyActivity.getIntent(HomeActivity.this,"","千钧体育"));

                break;
            case R.id.cehua_lianxi:
                startActivity(LianxiActivity.getIntent(HomeActivity.this));

                break;
            case R.id.cehua_back:
                SPUtils.clear(HomeActivity.this);
                startActivity(LandingActivity.getIntent(HomeActivity.this));
                break;
            case R.id.cehua_touxiang:
                startActivity(GerenActivity.getIntent(HomeActivity.this,"no"));

                break;

        }
    }

    //设置开关监听
    private void initEvent() {
        drawerbar = new ActionBarDrawerToggle(this, activity_na, null, R.string.open, R.string.close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        activity_na.setDrawerListener(drawerbar);
    }



    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     */
    class popupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

    @Override
    protected void onDestroy() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow=null;
        }
        if (mAudioRecoderUtils!=null){
            mAudioRecoderUtils.stopRecord();
            mAudioRecoderUtils=null;
        }

        super.onDestroy();
    }
    private long firstTime=0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Toast.makeText(HomeActivity.this,"再按一次退出程序!",Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
