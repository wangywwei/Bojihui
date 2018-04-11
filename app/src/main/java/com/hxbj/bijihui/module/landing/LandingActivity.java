package com.hxbj.bijihui.module.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.module.HomeActivity;
import com.jaeger.library.StatusBarUtil;

/*
* 登陆页面
* */
public class LandingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView landing_youke;
    private TextView landing_shoujidenglu;
    private RelativeLayout landing_denglu;
    private TextView shoujidenglu;
    private ImageView landing_guanbi;
    private EditText landing_shouji;
    private EditText landing_yanzheng;
    private TextView landing_yanzhengbutton;
    private TextView landing;
    private LinearLayout shoujideng;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, LandingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.transparent), 0);
        initView();
        initData();
    }

    private void initData() {


    }

    private void initView() {
        landing_youke = (TextView) findViewById(R.id.landing_youke);
        landing_shoujidenglu = (TextView) findViewById(R.id.landing_shoujidenglu);
        landing_denglu = (RelativeLayout) findViewById(R.id.landing_denglu);
        shoujidenglu = (TextView) findViewById(R.id.shoujidenglu);
        landing_guanbi = (ImageView) findViewById(R.id.landing_guanbi);
        landing_shouji = (EditText) findViewById(R.id.landing_shouji);
        landing_yanzheng = (EditText) findViewById(R.id.landing_yanzheng);
        landing_yanzhengbutton = (TextView) findViewById(R.id.landing_yanzhengbutton);
        landing = (TextView) findViewById(R.id.landing);
        shoujideng = (LinearLayout) findViewById(R.id.shoujideng);

        landing_shoujidenglu.setOnClickListener(this);
        landing_guanbi.setOnClickListener(this);
        landing_youke.setOnClickListener(this);
        landing.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.landing_shoujidenglu:
                landing_denglu.setVisibility(View.GONE);
                shoujideng.setVisibility(View.VISIBLE);
                break;
            case R.id.landing_guanbi:
                landing_denglu.setVisibility(View.VISIBLE);
                shoujideng.setVisibility(View.GONE);
                break;


            case R.id.landing_youke:
                startActivity(HomeActivity.getIntent(this));
                break;
            case R.id.landing:
                startActivity(GerenActivity.getIntent(this,"shouci"));
                break;
        }
    }
}
