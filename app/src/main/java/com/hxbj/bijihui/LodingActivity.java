package com.hxbj.bijihui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.module.HomeActivity;
import com.hxbj.bijihui.module.landing.LandingActivity;
import com.hxbj.bijihui.utils.SPUtils;
import com.hxbj.bijihui.utils.StringStatic;
import com.hxbj.bijihui.utils.StringUtils;

public class LodingActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);


        //第一次进入
        if (StringUtils.isBlank((String) SPUtils.get(this, StringStatic.DIYICI,""))){
            startActivity(LandingActivity.getIntent(this));
            finish();
        }else {
            //没有登陆
            if (StringUtils.isBlank((String) SPUtils.get(this, StringStatic.ISDENGLU,""))){
                startActivity(LandingActivity.getIntent(this));
                finish();
            }else {
                //登陆过
                if (MyApp.instance.getType().equals("游客")){
                    startActivity(LandingActivity.getIntent(this));
                }else {
                    startActivity(HomeActivity.getIntent(this));
                }

                finish();
            }

        }


    }

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

}
