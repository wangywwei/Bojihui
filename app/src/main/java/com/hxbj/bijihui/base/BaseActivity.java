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
        MyApp.mContext=this;

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
