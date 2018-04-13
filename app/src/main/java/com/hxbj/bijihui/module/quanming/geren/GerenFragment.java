package com.hxbj.bijihui.module.quanming.geren;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseFragment;
import com.hxbj.bijihui.module.quanming.LuVideoActivity;
import com.hxbj.bijihui.video.CustomRecordActivity;
import com.hxbj.bijihui.video.VideoView;


public class GerenFragment extends BaseFragment implements GerenContract.GerenView, View.OnClickListener {

    private GerenContract.GerenPresenter gerenPresenter;


    private View view;
    private RelativeLayout gerenvideo;
    private ImageView kongzhuangtai;
    private ImageView shangchuanwode;
    private LinearLayout meiyouwode;
    private RelativeLayout gerenmyvideo;
    private ImageView zan;
    private TextView zannum;
    private ImageView fenxiang;
    private TextView chonglu;
    private LinearLayout youwode;
    private PopupWindow chongluWindow;
    private ImageView beijing;
    private ImageView bofang;
    private RelativeLayout gerenvideo2;
    private RelativeLayout gerenmyvideo2;
    private ImageView beijing2;
    private ImageView bofang2;
    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_geren, null);
            initView(view);
            initData();
        } else {
            // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }

        return view;
    }

    private void initData() {
        gerenPresenter = new GerenPresenter(this);
        gerenPresenter.start();
    }

    private void initView(View view) {
        kongzhuangtai = (ImageView) view.findViewById(R.id.kongzhuangtai);
        shangchuanwode = (ImageView) view.findViewById(R.id.shangchuanwode);
        meiyouwode = (LinearLayout) view.findViewById(R.id.meiyouwode);

        zan = (ImageView) view.findViewById(R.id.zan);
        zannum = (TextView) view.findViewById(R.id.zannum);
        fenxiang = (ImageView) view.findViewById(R.id.fenxiang);
        chonglu = (TextView) view.findViewById(R.id.chonglu);
        youwode = (LinearLayout) view.findViewById(R.id.youwode);

        /*
        * 规范的动作
        * */
        gerenvideo = (RelativeLayout) view.findViewById(R.id.gerenvideo);
        gerenvideo2 = view.findViewById(R.id.gerenvideo2);
        beijing = view.findViewById(R.id.beijing);
        bofang = view.findViewById(R.id.bofang);

        /*
        * 个人的动作
        * */
        gerenmyvideo = (RelativeLayout) view.findViewById(R.id.gerenmyvideo);
        gerenmyvideo2 = view.findViewById(R.id.gerenmyvideo2);
        beijing2 = view.findViewById(R.id.beijing2);
        bofang2 = view.findViewById(R.id.bofang2);

        shangchuanwode.setOnClickListener(this);
        chonglu.setOnClickListener(this);
        fenxiang.setOnClickListener(this);
        zan.setOnClickListener(this);
        bofang.setOnClickListener(this);
        bofang2.setOnClickListener(this);
    }

    @Override
    public void setResultData(String resultData) {

    }

    @Override
    public void setPresenter(GerenContract.GerenPresenter gerenPresenter) {
        this.gerenPresenter = gerenPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shangchuanwode:
                //跳转到录制视频页面
                getActivity().startActivity(CustomRecordActivity.getIntent(getActivity()));
                break;
            case R.id.chonglu:
                initchonglu();

                break;
            case R.id.fenxiang:


                break;
            case R.id.zan:


                break;
            case R.id.bofang:
                gerenvideo.setVisibility(View.VISIBLE);
                gerenvideo2.setVisibility(View.GONE);
                /*
                 * 将下面的变回原来的
                 * */
                gerenmyvideo.setVisibility(View.GONE);
                gerenmyvideo2.setVisibility(View.VISIBLE);

                videoView = new VideoView(getActivity());
                videoView.onDestroy();
                gerenvideo.addView(videoView);
                videoView.setStart("http://heixiong-wlf.oss-cn-beijing.aliyuncs.com/videos/outputCut.mp4",null,null);
                break;
            case R.id.bofang2:
                gerenmyvideo.setVisibility(View.VISIBLE);
                gerenmyvideo2.setVisibility(View.GONE);
                /*
                * 将上面的变回原来的
                * */
                gerenvideo.setVisibility(View.GONE);
                gerenvideo2.setVisibility(View.VISIBLE);

                videoView = new VideoView(getActivity());
                videoView.onDestroy();

                gerenmyvideo.addView(videoView);
                videoView.setStart("http://heixiong-wlf.oss-cn-beijing.aliyuncs.com/videos/outputCut.mp4",null,null);

                break;
        }
    }

    private void initchonglu() {
        View chonglu = getLayoutInflater().inflate(R.layout.chonglu_popup, null);
        chongluWindow = new PopupWindow(chonglu);
        chongluWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        chongluWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        chongluWindow.setBackgroundDrawable(new BitmapDrawable());
        chongluWindow.setOutsideTouchable(true);
        chongluWindow.setFocusable(true);
        chongluWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //关闭事件
        chongluWindow.setOnDismissListener(new popupDismissListener());
        backgroundAlpha(0.5f);

        TextView fou=chonglu.findViewById(R.id.fou);
        TextView yes=chonglu.findViewById(R.id.yes);

        fou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chongluWindow.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到录制视频页面
                chongluWindow.dismiss();
                getActivity().startActivity(CustomRecordActivity.getIntent(getActivity()));
            }
        });
    }

    @Override
    public void onDestroy() {
        if (chongluWindow!=null){
            chongluWindow.dismiss();
            chongluWindow=null;
        }
        super.onDestroy();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
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
