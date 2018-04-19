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
import com.hxbj.bijihui.model.bean.HomeBannerBean;
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

    private ArrayList<HomeBannerBean.DataBean> images = new ArrayList<>();
    private ViewPager banner;
    private LinearLayout mLayout;

    private Timer mTimer;
    private HomeBannerAdapter mAdapter;
    private HomeBannerBean homeBannerBean;
    private View view;

    public void setHomeBannerBean(HomeBannerBean homeBannerBean) {
        this.homeBannerBean = homeBannerBean;
        images.clear();
        images.addAll(homeBannerBean.getData());

        mAdapter = new HomeBannerAdapter(images,context);
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

        mAdapter.notifyDataSetChanged();
    }

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
        view = LayoutInflater.from(context).inflate(R.layout.home_banner, this);
        mLayout= view.findViewById(R.id.mlayout);
        banner = view.findViewById(R.id.banner);


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
