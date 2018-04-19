package com.hxbj.bijihui.module.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.bean.HomeBannerBean;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.module.landing.GerenActivity;
import com.hxbj.bijihui.utils.GlidUtils;
import com.hxbj.bijihui.utils.SPUtils;
import com.hxbj.bijihui.utils.StringStatic;
import com.hxbj.bijihui.utils.StringUtils;

/*
* 首页第2快
* */
public class HomeMy extends LinearLayout implements View.OnClickListener {
    private Context context;
    private ImageView home_touxiang;
    private TextView home_name;
    private TextView home_huiyuan;
    private TextView home_jinyan;
    private LinearLayout jinyan;
    private ImageView home_huizhang;
    private TextView daka;
    private TextView xunlian;
    private TextView weixue;
    private LoginBean loginBean;

    public void setHomeMyBean(LoginBean loginBean) {
        this.loginBean = loginBean;
        GlidUtils.setGrid2(context,loginBean.getData().getPicUrl(),home_touxiang);
        if (StringUtils.isBlank((String) SPUtils.get(context, StringStatic.ISDENGLU,""))){
            home_name.setText("游客");
        }else {
            if (StringUtils.isBlank(loginBean.getData().getNickname())){
                home_name.setText(MyApp.instance.getIphone());
            }else {
                home_name.setText(loginBean.getData().getNickname());
            }
        }
        if (loginBean.getData().getType().equals("会员")){
            home_huiyuan.setVisibility(VISIBLE);
        }else {
            home_huiyuan.setVisibility(GONE);
        }

        home_jinyan.setText(loginBean.getData().getExperience()+"");
        daka.setText(loginBean.getData().getPunch()+"");
        xunlian.setText(loginBean.getData().getOldCourse()+"");
        weixue.setText(loginBean.getData().getNewCourse()+"");
    }

    public HomeMy(Context context) {
        this(context, null);
    }

    public HomeMy(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeMy(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.home_my, this);
        home_touxiang=view.findViewById(R.id.home_touxiang);
        home_name=view.findViewById(R.id.home_name);
        home_huiyuan=view.findViewById(R.id.home_huiyuan);
        home_jinyan=view.findViewById(R.id.home_jinyan);
        jinyan=view.findViewById(R.id.jinyan);
        home_huizhang=view.findViewById(R.id.home_huizhang);
        daka=view.findViewById(R.id.daka);
        xunlian=view.findViewById(R.id.xunlian);
        weixue=view.findViewById(R.id.weixue);

        home_touxiang.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_touxiang:
                context.startActivity(GerenActivity.getIntent(context,"no"));
                break;

        }
    }
}
