package com.hxbj.bijihui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.hxbj.bijihui.global.MyApp;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutId());
        MyApp.mContext=this;
        initView();
        initData();
    }

    protected abstract void initData();

    /**
     * Activity初始化View控件
     */
    protected abstract void initView();

    /**
     *   加载Activity的布局
     * @return Activity的布局ID
     */
    public abstract int getActivityLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
