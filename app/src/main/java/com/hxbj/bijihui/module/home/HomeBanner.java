package com.hxbj.bijihui.module.home;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.hxbj.bijihui.R;
import com.hxbj.bijihui.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/*
* 首页banner
* */
public class HomeBanner extends LinearLayout {
    private Activity context;

    private ArrayList<String> images = new ArrayList<>();
    private ViewPager banner;
    private LinearLayout mLayout;

    private Timer mTimer;

    public HomeBanner(Activity context) {
        this(context,null);

    }

    public HomeBanner(Activity context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomeBanner(Activity context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.home_banner, this);
        images.add("http://heixiong-wlf.oss-cn-beijing.aliyuncs.com/images/2018012301.png");
        images.add("http://heixiong-wlf.oss-cn-beijing.aliyuncs.com/images/2018012202.png");
        images.add("http://heixiong-wlf.oss-cn-beijing.aliyuncs.com/images/2018012303.png");
        images.add("http://heixiong-wlf.oss-cn-beijing.aliyuncs.com/images/2018020815072481729990.JPG");
        images.add("http://heixiong-wlf.oss-cn-beijing.aliyuncs.com/images/2018030615084479647358.png");
        mLayout=view.findViewById(R.id.mlayout);
        banner = view.findViewById(R.id.banner);
        HomeBannerAdapter mAdapter = new HomeBannerAdapter(images,context);
        banner.setAdapter(mAdapter);
        banner.setCurrentItem(100000);
        mTimer = new Timer(true);
        mTimer.schedule(mTask, 3000, 3000);


        for (int i = 0; i < images.size(); i++) {
            View zhishiqi = new View(context);
            // 正常情况下五个点都是白色的
            view.setBackgroundResource(R.drawable.lunbo_1);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(32, 4);
            if (i != 0) {
                // lp.setMargins(10, 0,0, 0);
                lp.leftMargin = 16;
            }
            mLayout.addView(zhishiqi, lp);
            // 添加View对象
        }

        //每次打开程序ViewPager默认选中第0个点
        mLayout.getChildAt(0).setBackgroundResource(
                R.drawable.lunbo);

        banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int newPosition = position % images.size();
                for (int i = 0; i < images.size(); i++) {
                    if (i == newPosition) {
                        mLayout.getChildAt(i).setBackgroundResource(
                                R.drawable.lunbo);
                    } else {
                        mLayout.getChildAt(i).setBackgroundResource(
                                R.drawable.lunbo_1);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private TimerTask mTask = new TimerTask() {
        @Override
        public void run() {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    banner.setCurrentItem(banner.getCurrentItem()+1);
                }
            });
        }
    };

    public void onDestroy(){
        if (mTimer != null && mTask != null) {
            mTimer.cancel();
            mTask = null;
            mTimer = null;
        }
    }

}
