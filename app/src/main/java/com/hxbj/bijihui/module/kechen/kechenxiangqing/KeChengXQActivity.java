package com.hxbj.bijihui.module.kechen.kechenxiangqing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.model.bean.Kecheng;
import com.hxbj.bijihui.module.home.HomedAdapter;
import com.hxbj.bijihui.utils.AppUtils;
import com.hxbj.bijihui.video.VideoView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/*
 * 课程详情页
 * */
public class KeChengXQActivity extends BaseActivity implements KechengXQContract.KechengXQView, View.OnClickListener, KeChengXQAdapter.VideoListener {

    private KeChengXQAdapter adapter;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, KeChengXQActivity.class);
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
    private XRecyclerView kecheng_xrecyclerview;

    private KechengXQContract.KechengXQPresenter kechengXQPresenter;

    private ArrayList<Kecheng> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_cheng_xq);
        AppUtils.setTitle(this);
        initView();
        initData();
    }

    private void initData() {
        kechengXQPresenter = new KechengXQPresenter(this);
        kechengXQPresenter.start();
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
        kecheng_xrecyclerview = (XRecyclerView) findViewById(R.id.kecheng_xrecyclerview);
        for (int i = 0; i <10 ; i++) {
            list.add(new Kecheng());
        }
        adapter = new KeChengXQAdapter(this, list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        kecheng_xrecyclerview.setLayoutManager(layoutManager);
        kecheng_xrecyclerview.setNestedScrollingEnabled(false);
        kecheng_xrecyclerview.setAdapter(adapter);
        adapter.setVideoListener(this);
        yaobu.setOnClickListener(this);
        shoubi.setOnClickListener(this);
        bufa.setOnClickListener(this);
        back.setOnClickListener(this);
        kecheng_xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                kecheng_xrecyclerview.refreshComplete();//刷新完成
            }

            @Override
            public void onLoadMore() {
                kecheng_xrecyclerview.refreshComplete();//加载完成
            }
        });
    }

    @Override
    public void setResultData(String resultData) {

    }

    @Override
    public void setPresenter(KechengXQContract.KechengXQPresenter kechengXQPresenter) {
        this.kechengXQPresenter = kechengXQPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

    @Override
    public void playVideo(String url, String imgurl, String title, int position, RelativeLayout relativeLayout) {
        if (position == -1) {
            return;
        }

        VideoView videoView = new VideoView(this);
        relativeLayout.addView(videoView);
        videoView.onDestroy();
        //初始化所有数据
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelect(false);
        }
        list.get(position).setSelect(true);
        adapter.notifyDataSetChanged();

        videoView.setStart(url,imgurl,title);

    }

    @Override
    public boolean isPlaying() {
        return false;
    }
}
