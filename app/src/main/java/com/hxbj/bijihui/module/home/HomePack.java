package com.hxbj.bijihui.module.home;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.module.kechen.KechenActivity;
import com.hxbj.bijihui.module.quanming.QuanMingActivity;
import com.hxbj.bijihui.utils.AudioRecoderUtils;
import com.hxbj.bijihui.utils.LogUtils;
import com.hxbj.bijihui.utils.SPUtils;
import com.hxbj.bijihui.utils.StringStatic;
import com.hxbj.bijihui.utils.TimeUtils;
import com.hxbj.bijihui.view.ArcProgress;
import com.hxbj.bijihui.view.OnTextCenter;

import java.io.IOException;

/*
 * 首页第三块
 * */
public class HomePack extends LinearLayout implements View.OnClickListener {

    private Activity context;
    private ImageView nahandemubiao;
    private ImageView daka1;
    private ImageView home_quanbukecheng;
    private PopupWindow nahanpopup;
    private PopupWindow dakapopo;
    private ImageView quanminzhanshi;
    private AudioRecoderUtils mAudioRecoderUtils;
    private MediaPlayer mediaPlayer;

    public HomePack(Activity context) {
        this(context, null);
    }

    public HomePack(Activity context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomePack(Activity context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.home_pack, this);
        nahandemubiao = view.findViewById(R.id.nahandemubiao);
        daka1 = view.findViewById(R.id.daka1);
        home_quanbukecheng = view.findViewById(R.id.home_quanbukecheng);
        quanminzhanshi=view.findViewById(R.id.quanminzhanshi);

        quanminzhanshi.setOnClickListener(this);
        nahandemubiao.setOnClickListener(this);
        daka1.setOnClickListener(this);
        home_quanbukecheng.setOnClickListener(this);

        mediaPlayer=new MediaPlayer();
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource((String) SPUtils.get(context,StringStatic.FILEPATH,""));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mAudioRecoderUtils = new AudioRecoderUtils();

        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time2) {
                //根据分贝值来设置录音时话筒图标的上下波动，下面有讲解
                myProgress.setProgress((int)(time2/1000));
                if (myProgress.getProgress()>=10){
                    time.setText(TimeUtils.getTimedanqian()+"目标");
                    bofang.setVisibility(View.VISIBLE);
                    myProgress.setVisibility(View.GONE);
                    //结束录音（保存录音文件）
                    mAudioRecoderUtils.stopRecord();
                }

            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                LogUtils.e("TAG",filePath);
                SPUtils.put(context, StringStatic.FILEPATH,filePath);
                try {
                    mediaPlayer.setDataSource(filePath);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nahandemubiao:
                initNahan();

                break;
            case R.id.daka1:
                initDaka();

                break;
            case R.id.home_quanbukecheng:
                context.startActivity(KechenActivity.getIntent(context));
                break;
            case R.id.quanminzhanshi:
                context.startActivity(QuanMingActivity.getIntent(context));
                break;

        }
    }
    private ImageView qiandaotu;
    private ImageView daka_touxiang;
    private ImageView daka_huiyuan;
    private TextView daka_name;
    private TextView daka_liangji;
    private ImageView fenxiangweixing;
    private ImageView fenxiangpengyouquan;
    private ImageView fenxiangweibo;
    private void initDaka() {
        View dakaview = context.getLayoutInflater().inflate(R.layout.home_popup3, null);
        dakapopo = new PopupWindow(dakaview);
        dakapopo.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        dakapopo.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        dakapopo.setBackgroundDrawable(new BitmapDrawable());
        dakapopo.setOutsideTouchable(true);
        dakapopo.setFocusable(true);
        dakapopo.showAtLocation(context.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //关闭事件
        dakapopo.setOnDismissListener(new popupDismissListener());
        backgroundAlpha(0.5f);

        qiandaotu=dakaview.findViewById(R.id.qiandaotu);
        daka_touxiang=dakaview.findViewById(R.id.daka_touxiang);
        daka_huiyuan=dakaview.findViewById(R.id.daka_huiyuan);
        daka_name=dakaview.findViewById(R.id.daka_name);
        daka_liangji=dakaview.findViewById(R.id.daka_liangji);
        fenxiangweixing=dakaview.findViewById(R.id.fenxiangweixing);
        fenxiangpengyouquan=dakaview.findViewById(R.id.fenxiangpengyouquan);
        fenxiangweibo=dakaview.findViewById(R.id.fenxiangweibo);

        ImageView guanbi = dakaview.findViewById(R.id.guanbi);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dakapopo.dismiss();
            }
        });

    }

    private TextView time;
    private ImageView bofang;
    private TextView shanchu;
    private ImageView newbutton;
    private LinearLayout popo;
    private ArcProgress myProgress;
    private void initNahan() {
        View nahanview = context.getLayoutInflater().inflate(R.layout.home_popup2, null);
        nahanpopup = new PopupWindow(nahanview);
        nahanpopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        nahanpopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        nahanpopup.setBackgroundDrawable(new BitmapDrawable());
        nahanpopup.setOutsideTouchable(true);
        nahanpopup.setFocusable(true);
        nahanpopup.showAtLocation(context.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //关闭事件
        nahanpopup.setOnDismissListener(new popupDismissListener());
        backgroundAlpha(0.5f);

        time = nahanview.findViewById(R.id.time);
        bofang = nahanview.findViewById(R.id.bofang);
        shanchu = nahanview.findViewById(R.id.shanchu);
        newbutton = nahanview.findViewById(R.id.newbutton);
        popo = nahanview.findViewById(R.id.popo);
        myProgress= nahanview.findViewById(R.id.myProgress);
        myProgress.setOnCenterDraw(new OnTextCenter());
        myProgress.setVisibility(GONE);
        ImageView guanbi = nahanview.findViewById(R.id.guanbi);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nahanpopup.dismiss();
            }
        });

        bofang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    return;
                }else {
                    mediaPlayer.start();
                }
            }
        });

        newbutton.setOnTouchListener(onTouchListener);
    }

    View.OnTouchListener onTouchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                //按下
                case MotionEvent.ACTION_DOWN:
                    bofang.setVisibility(View.GONE);
                    myProgress.setVisibility(View.VISIBLE);
                    mAudioRecoderUtils.startRecord();

                    break;
                //抬起
                case MotionEvent.ACTION_UP:
                    if (myProgress.getProgress()>=10){

                    }else {
                        time.setText(TimeUtils.getTimedanqian()+"目标");
                        bofang.setVisibility(View.VISIBLE);
                        myProgress.setVisibility(View.GONE);
                        //结束录音（保存录音文件）
                        mAudioRecoderUtils.stopRecord();
                    }


                    break;
            }
            return true;
        }
    };

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
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

    public void onDestroy() {
        if (nahanpopup!=null){
            nahanpopup.dismiss();
            nahanpopup=null;
        }
        if (dakapopo!=null){
            dakapopo.dismiss();
            dakapopo=null;
        }
    }
}
