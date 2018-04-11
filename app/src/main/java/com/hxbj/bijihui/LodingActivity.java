package com.hxbj.bijihui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.module.HomeActivity;
import com.hxbj.bijihui.module.landing.LandingActivity;
import com.hxbj.bijihui.utils.SPUtils;
import com.hxbj.bijihui.utils.StaticString;
import com.hxbj.bijihui.utils.StringUtils;

public class LodingActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);

        //第一次进入
        if (StringUtils.isBlank((String) SPUtils.get(this,StaticString.DIYICI,""))){

            startActivity(LandingActivity.getIntent(this));
        }else {
            //没有登陆
            if (StringUtils.isBlank((String) SPUtils.get(this,StaticString.ISDENGLU,""))){

                startActivity(LandingActivity.getIntent(this));

            }else {
                //登陆过
                Intent intent=new Intent(this, HomeActivity.class);
                startActivity(intent);
            }

        }


    }
}
