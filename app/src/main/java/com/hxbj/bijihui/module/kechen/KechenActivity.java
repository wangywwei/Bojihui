package com.hxbj.bijihui.module.kechen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.module.kechen.kechenxiangqing.KeChengXQActivity;
import com.hxbj.bijihui.utils.AppUtils;

import java.util.ArrayList;

/*
* 课程分类
* */
public class KechenActivity extends BaseActivity implements KechenContract.KechenView {


    private PopupWindow chujipopup;
    private PopupWindow gaojipopup;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, KechenActivity.class);
        return intent;
    }

    private KechenContract.KechenPresenter kechenPresenter;
    private ImageView back;
    private RecyclerView listview;
    private ArrayList<String> list = new ArrayList<>();
    private KechenAdapter kechenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kechen);
        AppUtils.setTitle(this);
        initView();
        initData();

    }

    private void initData() {
        kechenPresenter = new KechenPresenter(this);

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        listview = (RecyclerView) findViewById(R.id.listview);
        list.add("体验课程");
        list.add("Lv.1  初级课程");
        list.add("Lv.2  中级课程");
        list.add("Lv.3  高级课程");
        kechenAdapter = new KechenAdapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listview.setLayoutManager(layoutManager);
        listview.setNestedScrollingEnabled(false);
        listview.setAdapter(kechenAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        kechenAdapter.setOnItemclickLinter(new KechenAdapter.OnItemclickLinter() {
            @Override
            public void onItemClicj(int position) {
                switch (position) {
                    case 0:
                        startActivity(KeChengXQActivity.getIntent(KechenActivity.this));
                        break;
                    case 1:
//                        initchuji();
                        if (MyApp.instance.getType().equals("会员")){
                            startActivity(KeChengXQActivity.getIntent(KechenActivity.this));
                        }else {
                            initgaoji();
                        }
                        break;
                    case 2:
                        if (MyApp.instance.getType().equals("会员")){
                            startActivity(KeChengXQActivity.getIntent(KechenActivity.this));
                        }else {
                            initgaoji();
                        }
                        break;
                    case 3:
                        if (MyApp.instance.getType().equals("会员")){
                            startActivity(KeChengXQActivity.getIntent(KechenActivity.this));
                        }else {
                            initgaoji();
                        }
                        break;

                }
            }
        });


    }

    private TextView jihuo;
    private TextView queren;
    private EditText yaoqingma;
    private void initgaoji() {
        View gaojiview = getLayoutInflater().inflate(R.layout.kechenggaoji_popup, null);
        gaojipopup = new PopupWindow(gaojiview);
        gaojipopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        gaojipopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        gaojipopup.setBackgroundDrawable(new BitmapDrawable());
        gaojipopup.setOutsideTouchable(true);
        gaojipopup.setFocusable(true);
        gaojipopup.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //关闭事件
        gaojipopup.setOnDismissListener(new popupDismissListener());
        backgroundAlpha(0.5f);
        jihuo = gaojiview.findViewById(R.id.jihuo);
        queren = gaojiview.findViewById(R.id.queren);
        yaoqingma = gaojiview.findViewById(R.id.yaoqingma);
        ImageView guanbi = gaojiview.findViewById(R.id.guanbi);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gaojipopup.dismiss();
            }
        });

        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kechenPresenter.start();
            }
        });
    }

    private void initchuji() {
        View chujiview = getLayoutInflater().inflate(R.layout.kechengchuji_popup, null);
        chujipopup = new PopupWindow(chujiview);
        chujipopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        chujipopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        chujipopup.setBackgroundDrawable(new BitmapDrawable());
        chujipopup.setOutsideTouchable(true);
        chujipopup.setFocusable(true);
        chujipopup.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //关闭事件
        chujipopup.setOnDismissListener(new popupDismissListener());
        ImageView guanbi = chujiview.findViewById(R.id.guanbi);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chujipopup.dismiss();
            }
        });
        backgroundAlpha(0.5f);

    }

    @Override
    public void setResultData(String dataBean) {

    }

    @Override
    public void setPresenter(KechenContract.KechenPresenter kechenPresenter) {
        this.kechenPresenter = kechenPresenter;
    }


    @Override
    protected void onDestroy() {
        if (chujipopup != null) {
            chujipopup.dismiss();
            chujipopup=null;
        }
        if (gaojipopup != null) {
            gaojipopup.dismiss();
            gaojipopup=null;
        }
        super.onDestroy();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    private void submit() {
        // validate
        String yaoqingmaString = yaoqingma.getText().toString().trim();
        if (TextUtils.isEmpty(yaoqingmaString)) {
            Toast.makeText(this, "请输入有效邀请码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     */
    class popupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }
}
