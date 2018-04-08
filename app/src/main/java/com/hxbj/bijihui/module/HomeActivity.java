package com.hxbj.bijihui.module;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.base.FragmentManager;
import com.hxbj.bijihui.module.home.HomeFragment;

public class HomeActivity extends BaseActivity implements View.OnClickListener {


    private PopupWindow popupWindow;
    private TextView cehualan;
    private FrameLayout homeframe;
    private NavigationView nav;
    private DrawerLayout activity_na;

    @Override
    protected void initView() {
        cehualan = findViewById(R.id.cehualan);
        homeframe = findViewById(R.id.homeframe);
        nav = findViewById(R.id.nav);
        activity_na = findViewById(R.id.activity_na);

        activity_na.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                activity_na.closeDrawer(nav);
                return false;
            }
        });
        cehualan.setOnClickListener(this);

        FragmentManager.changeFragment(HomeFragment.class, R.id.homeframe, true, false);
    }

    @Override
    protected void initData() {
        View view = getLayoutInflater().inflate(R.layout.home_popup, null);
        popupWindow = new PopupWindow(view);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        popupWindow.setOutsideTouchable(true);
//        popupWindow.showAsDropDown(cehualan);
    }

    @Override
    public int getActivityLayoutId() {
        return R.layout.activity_home;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //侧滑菜单键
            case R.id.cehualan:
                if (activity_na.isDrawerOpen(nav)) {
                    activity_na.closeDrawer(nav);
                } else {
                    activity_na.openDrawer(nav);
                }
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
