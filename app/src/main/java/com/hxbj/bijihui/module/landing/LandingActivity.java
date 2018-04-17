package com.hxbj.bijihui.module.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.module.HomeActivity;
import com.hxbj.bijihui.utils.LogUtils;
import com.hxbj.bijihui.utils.SPUtils;
import com.jaeger.library.StatusBarUtil;

/*
* 登陆页面
* */
public class LandingActivity extends AppCompatActivity implements View.OnClickListener ,LandingContract.LancingView{
    private LandingContract.LandingPresenter landingPresenter;


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
        initView();
        initData();
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

    private void initData() {
        landingPresenter=new LandingPresenter(this);


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
                finish();
                break;
            case R.id.landing:
                landingPresenter.start();

                break;
        }
    }

    @Override
    public void setPresenter(LandingContract.LandingPresenter landingPresenter) {
            this.landingPresenter=landingPresenter;
    }

    @Override
    public void setResultData(String token) {
        LogUtils.e("TAG",token+"");
        SPUtils.put(this,"Authorization",token);
        startActivity(GerenActivity.getIntent(this,"shouci"));
        finish();
    }
}
