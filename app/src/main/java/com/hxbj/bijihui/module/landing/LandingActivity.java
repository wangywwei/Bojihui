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

import com.google.gson.Gson;
import com.hxbj.bijihui.R;
import com.hxbj.bijihui.constants.Urls;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.module.HomeActivity;
import com.hxbj.bijihui.module.web.WebViewCurrencyActivity;
import com.hxbj.bijihui.network.HttpFactory;
import com.hxbj.bijihui.utils.AppUtils;
import com.hxbj.bijihui.utils.CountDownTextView;
import com.hxbj.bijihui.utils.LogUtils;
import com.hxbj.bijihui.utils.SPUtils;
import com.hxbj.bijihui.utils.StringStatic;
import com.hxbj.bijihui.utils.StringUtils;
import com.hxbj.bijihui.utils.ToastUtils;
import com.jaeger.library.StatusBarUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    private CountDownTextView landing_yanzhengbutton;
    private TextView landing;
    private TextView zhucexieyi;
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
        landing_yanzhengbutton = (CountDownTextView) findViewById(R.id.landing_yanzhengbutton);
        landing = (TextView) findViewById(R.id.landing);
        shoujideng = (LinearLayout) findViewById(R.id.shoujideng);
        zhucexieyi=findViewById(R.id.zhucexieyi);

        zhucexieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(WebViewCurrencyActivity.getIntent(LandingActivity.this,"","注册协议"));
            }
        });

        landing_shoujidenglu.setOnClickListener(this);
        landing_guanbi.setOnClickListener(this);
        landing_youke.setOnClickListener(this);
        landing.setOnClickListener(this);
        landing_yanzhengbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String iphone=landing_shouji.getText().toString().trim();
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
                landingPresenter.start("11099998888","hxbjh110");
                MyApp.instance.setIphone("11099998888");

                break;
            case R.id.landing:
                String yanzheng=landing_yanzheng.getText().toString().trim();
                if (AppUtils.isMobileNO(iphone)){
                    if (!StringUtils.isBlank(landing_yanzhengbutton.getText().toString().trim())){
                        MyApp.instance.setIphone(iphone);
                        landingPresenter.start(iphone,yanzheng);
                    }else {
                        ToastUtils.showToast(this,"请输入验证码");
                    }
                }else {
                    ToastUtils.showToast(this,"请输入正确的手机号");
                }
                break;
            case R.id.landing_yanzhengbutton:
                if (AppUtils.isMobileNO(iphone)){
                    landing_yanzhengbutton.start();
                    getverifyCode(iphone);

                }else {
                    ToastUtils.showToast(this,"请输入正确的手机号");
                }
                break;
        }
    }

    private void getverifyCode(String iphone) {
        OkHttpClient mOkHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url(Urls.VERIFYCODE+"/"+iphone).get().build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


            }
        });


    }

    @Override
    public void setPresenter(LandingContract.LandingPresenter landingPresenter) {
            this.landingPresenter=landingPresenter;
    }

    @Override
    public void setResultData(String token) {
        LogUtils.e("TAG",token+"");
        MyApp.instance.setAuthorization(token);
        landingPresenter.userInfo(MyApp.instance.getIphone());

    }

    @Override
    public void setUserinfo(LoginBean loginBean) {
        if (loginBean.getCode()==2000){
//            try {
//                SPUtils.deleteAll(this);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            MyApp.instance.saveLogin(loginBean.getData(),this);
            SPUtils.put(this,StringStatic.ISDENGLU,"login");

                if (loginBean.getData().getType().equals("游客")){
                    startActivity(HomeActivity.getIntent(this));
                    finish();
                }else {
                    if (loginBean.getData().getFlag()==0){
                    startActivity(GerenActivity.getIntent(this,"shouci"));
                    finish();
                    }else {
                        startActivity(HomeActivity.getIntent(this));
                        finish();
                    }
                }


        }
    }


}
