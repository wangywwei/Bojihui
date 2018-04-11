package com.hxbj.bijihui.module.quanming;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.base.FragmentManager;
import com.hxbj.bijihui.module.quanming.geren.GerenFragment;

public class QuanMingActivity extends BaseActivity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanming);
        initView();

        FragmentManager.changeFragment(GerenFragment.class,R.id.framet,true,true);

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
        switch (v.getId()){
            case R.id.yaobu:
                yaobu_text.setTextColor(getResources().getColor(R.color.color_F2B95A));
                yaobu_view.setVisibility(View.VISIBLE);
                shoubi_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                shoubi_view.setVisibility(View.GONE);
                bufa_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                bufa_view.setVisibility(View.GONE);
                break;
            case R.id.shoubi:
                yaobu_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                yaobu_view.setVisibility(View.GONE);
                shoubi_text.setTextColor(getResources().getColor(R.color.color_F2B95A));
                shoubi_view.setVisibility(View.VISIBLE);
                bufa_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                bufa_view.setVisibility(View.GONE);
                break;
            case R.id.bufa:
                yaobu_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                yaobu_view.setVisibility(View.GONE);
                shoubi_text.setTextColor(getResources().getColor(R.color.color_2C2C2C));
                shoubi_view.setVisibility(View.GONE);
                bufa_text.setTextColor(getResources().getColor(R.color.color_F2B95A));
                bufa_view.setVisibility(View.VISIBLE);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
