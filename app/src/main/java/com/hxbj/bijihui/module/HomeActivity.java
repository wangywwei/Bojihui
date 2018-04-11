package com.hxbj.bijihui.module;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.base.FragmentManager;
import com.hxbj.bijihui.module.geren.LianxiActivity;
import com.hxbj.bijihui.module.home.HomeFragment;
import com.hxbj.bijihui.module.landing.GerenActivity;
import com.hxbj.bijihui.utils.AppUtils;
import com.jaeger.library.StatusBarUtil;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AppUtils.setTitle(this);

        initView();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                initData();
            }
        }, 1000);

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

    }

    private ImageView luzhi;
    private LinearLayout popo;
    private View popoview;

    private void initData() {
        View view = getLayoutInflater().inflate(R.layout.home_popup, null);
        popupWindow = new PopupWindow(view);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //关闭事件
        popupWindow.setOnDismissListener(new popupDismissListener());
        backgroundAlpha(0.5f);

        luzhi = view.findViewById(R.id.luzhi);
        popo = view.findViewById(R.id.popo);

    }

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


                break;
            case R.id.cehua_lianxi:
                startActivity(LianxiActivity.getIntent(HomeActivity.this));

                break;
            case R.id.cehua_back:


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
        }
        super.onDestroy();
    }
}
