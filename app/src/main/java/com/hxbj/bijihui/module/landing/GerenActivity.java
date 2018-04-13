package com.hxbj.bijihui.module.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.module.HomeActivity;
import com.hxbj.bijihui.utils.popuo.ChangeDatePopwindow;

/*
 * 首次登陆后的个人资料页面
 *
 * */
public class GerenActivity extends BaseActivity implements View.OnClickListener {
    public static Intent getIntent(Context context, String shouci) {
        Intent intent = new Intent(context, GerenActivity.class);
        intent.putExtra("shouci", shouci);
        return intent;
    }

    private String shouci;
    private ImageView genren_touxiang;
    private EditText genren_name;
    private RadioButton nan;
    private RadioButton nv;
    private RadioButton baomi;
    private RadioGroup genren_xinbie;
    private EditText genren_shouquanma;
    private ImageView hulue;
    private ImageView baocun;
    private TextView shouquan1;
    private TextView shouquan2;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geren);
        shouci = getIntent().getStringExtra("shouci");
        initView();

        if (shouci.equals("shouci")) {
            back.setVisibility(View.GONE);
            shouquan1.setText("授权码");
            shouquan2.setText("（可与您所在的俱乐部索要）");
            hulue.setVisibility(View.VISIBLE);
        } else {
            back.setVisibility(View.VISIBLE);
            shouquan1.setText("生日");
            genren_shouquanma.setHint("请输入生日");
            shouquan2.setVisibility(View.GONE);
            hulue.setVisibility(View.GONE);
        }

    }

    private void initView() {
        genren_touxiang = (ImageView) findViewById(R.id.genren_touxiang);
        genren_name = (EditText) findViewById(R.id.genren_name);
        nan = (RadioButton) findViewById(R.id.nan);
        nv = (RadioButton) findViewById(R.id.nv);
        baomi = (RadioButton) findViewById(R.id.baomi);
        genren_xinbie = (RadioGroup) findViewById(R.id.genren_xinbie);
        genren_shouquanma = (EditText) findViewById(R.id.genren_shouquanma);
        hulue = (ImageView) findViewById(R.id.hulue);
        baocun = (ImageView) findViewById(R.id.baocun);
        shouquan1 = findViewById(R.id.shouquan1);
        shouquan2 = findViewById(R.id.shouquan2);
        back = findViewById(R.id.back);
        baocun.setOnClickListener(this);
        hulue.setOnClickListener(this);
        back.setOnClickListener(this);
        genren_touxiang.setOnClickListener(this);
        genren_shouquanma.setOnClickListener(this);
        genren_xinbie.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.nan:

                        break;
                    case R.id.nv:

                        break;
                    case R.id.baomi:

                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.genren_touxiang:

                break;
            case R.id.hulue:


                startActivity(HomeActivity.getIntent(this));
                finish();
                break;
            case R.id.baocun:

                startActivity(HomeActivity.getIntent(this));
                finish();
                break;
            case R.id.back:
                startActivity(HomeActivity.getIntent(this));
                finish();
                break;
            case R.id.genren_shouquanma:
                if (!shouci.equals("shouci")){
                    selectDate();
                }

                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            startActivity(HomeActivity.getIntent(this));
            finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

    private String[] selectDate() {
        final String[] str = new String[10];
        ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(
                this);
        mChangeBirthDialog.setDate("2016", "1", "1");
        mChangeBirthDialog.showAtLocation(genren_shouquanma, Gravity.BOTTOM, 0, 0);
        mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {

            @Override
            public void onClick(String year, String month, String day) {
                // TODO Auto-generated method stub
                Toast.makeText(GerenActivity.this,year + "-" + month + "-" + day,Toast.LENGTH_LONG).show();
                StringBuilder sb = new StringBuilder();
                sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, day.length() - 1)).append("-").append(day);
                str[0] = year + "-" + month + "-" + day;
                str[1] = sb.toString();
                genren_shouquanma.setText(year + "-" + month + "-" + day);

            }
        });
        return str;
    }


}
