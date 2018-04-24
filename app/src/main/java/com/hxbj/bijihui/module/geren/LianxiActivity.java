package com.hxbj.bijihui.module.geren;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.model.bean.LiuyanBean;
import com.hxbj.bijihui.utils.AppUtils;

/*
* 联系我们页面
* */
public class LianxiActivity extends BaseActivity implements LianxiContract.LianxiView, View.OnClickListener {
    public static Intent getIntent(Context context){
        Intent intent=new Intent(context,LianxiActivity.class);
        return intent;
    }


    private LianxiContract.LianxiPresenter lianxiPresenter;
    private ImageView back;
    private EditText lianxiwomen;
    private TextView lianxi_fasong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lianxi);
        AppUtils.setTitle(this);
        initView();
        initData();

    }

    private void initData() {
        lianxiPresenter = new LianxiPresenter(this);

    }

    @Override
    public void setResultData(LiuyanBean resultData) {
        finish();
    }

    @Override
    public void setPresenter(LianxiContract.LianxiPresenter lianxiPresenter) {
        this.lianxiPresenter = lianxiPresenter;
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        lianxiwomen = (EditText) findViewById(R.id.lianxiwomen);
        lianxi_fasong = (TextView) findViewById(R.id.lianxi_fasong);

        back.setOnClickListener(this);
        lianxi_fasong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.lianxi_fasong:
                lianxiPresenter.start(lianxiwomen.getText().toString().trim());
                break;
        }
    }
}
