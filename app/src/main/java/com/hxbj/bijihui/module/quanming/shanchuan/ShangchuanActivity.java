package com.hxbj.bijihui.module.quanming.shanchuan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.utils.AppUtils;

public class ShangchuanActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private RelativeLayout bofangvidel;
    private ImageView chonglu;
    private ImageView shanchu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangchuan);
        AppUtils.setTitle(this);
        initView();

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        bofangvidel = (RelativeLayout) findViewById(R.id.bofangvidel);
        chonglu = (ImageView) findViewById(R.id.chonglu);
        shanchu = (ImageView) findViewById(R.id.shanchu);

        back.setOnClickListener(this);
        chonglu.setOnClickListener(this);
        shanchu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.chonglu:


                break;
            case R.id.shanchu:


                break;

        }
    }
}
