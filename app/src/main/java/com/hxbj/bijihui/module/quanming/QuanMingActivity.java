package com.hxbj.bijihui.module.quanming;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.base.FragmentManager;
import com.hxbj.bijihui.module.quanming.geren.GerenFragment;
import com.hxbj.bijihui.module.quanming.paihang.PaihangFragment;
import com.hxbj.bijihui.utils.AppUtils;

public class QuanMingActivity extends BaseActivity implements View.OnClickListener {
    public static Intent getIntent(Context context){
        Intent intent=new Intent(context,QuanMingActivity.class);
        return intent;
    }


    private ImageView back;
    private TextView yaobu_text;
    private View yaobu_view;
    private LinearLayout yaobu;
    private TextView shoubi_text;
    private View shoubi_view;
    private LinearLayout shoubi;
    private TextView bufa_text;
    private View bufa_view;
    private LinearLayout bufa;
    private GerenFragment gerenFragment;
    private PaihangFragment paihangFragment;
    private PaihangFragment zuixinFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanming);
        AppUtils.setTitle(this);
        initView();
        gerenFragment=new GerenFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.framet, gerenFragment).commit();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        yaobu_text = (TextView) findViewById(R.id.yaobu_text);
        yaobu_view = (View) findViewById(R.id.yaobu_view);
        yaobu = (LinearLayout) findViewById(R.id.yaobu);
        shoubi_text = (TextView) findViewById(R.id.shoubi_text);
        shoubi_view = (View) findViewById(R.id.shoubi_view);
        shoubi = (LinearLayout) findViewById(R.id.shoubi);
        bufa_text = (TextView) findViewById(R.id.bufa_text);
        bufa_view = (View) findViewById(R.id.bufa_view);
        bufa = (LinearLayout) findViewById(R.id.bufa);

        yaobu.setOnClickListener(this);
        shoubi.setOnClickListener(this);
        bufa.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hide(transaction);
        switch (v.getId()){
            //个人
            case R.id.yaobu:
                if (gerenFragment == null) {
                    gerenFragment = new GerenFragment();
                    transaction.add(R.id.framet, gerenFragment);
                } else {
                    transaction.show(gerenFragment);
                }
                yaobu_text.setTextColor(getResources().getColor(R.color.color_F2B95A));
                yaobu_view.setVisibility(View.VISIBLE);
                shoubi_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                shoubi_view.setVisibility(View.GONE);
                bufa_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                bufa_view.setVisibility(View.GONE);
                break;
                //排行
            case R.id.shoubi:
                if (paihangFragment == null) {
                    paihangFragment = new PaihangFragment();
                    transaction.add(R.id.framet, paihangFragment);
                } else {
                    transaction.show(paihangFragment);
                }
                yaobu_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                yaobu_view.setVisibility(View.GONE);
                shoubi_text.setTextColor(getResources().getColor(R.color.color_F2B95A));
                shoubi_view.setVisibility(View.VISIBLE);
                bufa_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                bufa_view.setVisibility(View.GONE);
                paihangFragment.setSortType("thump");
                break;
                //最新
            case R.id.bufa:
                if (zuixinFragment == null) {
                    zuixinFragment = new PaihangFragment();
                    transaction.add(R.id.framet, zuixinFragment);
                } else {
                    transaction.show(zuixinFragment);
                }
                yaobu_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                yaobu_view.setVisibility(View.GONE);
                shoubi_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                shoubi_view.setVisibility(View.GONE);
                bufa_text.setTextColor(getResources().getColor(R.color.color_F2B95A));
                bufa_view.setVisibility(View.VISIBLE);
                zuixinFragment.setSortType("createTime");
                break;
            case R.id.back:
                finish();
                break;
        }
        transaction.commit();
    }

    private void hide(FragmentTransaction transaction) {
        if (gerenFragment != null) {
            transaction.hide(gerenFragment);
        }
        if (paihangFragment != null) {
            transaction.hide(paihangFragment);
        }
        if (zuixinFragment != null) {
            transaction.hide(zuixinFragment);
        }
    }
}
