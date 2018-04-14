package com.hxbj.bijihui.module.quanming.geren;

import android.content.Intent;
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
import com.hxbj.bijihui.utils.SPUtils;
import com.hxbj.bijihui.utils.StringUtils;
import com.hxbj.bijihui.utils.ToastUtils;
import com.hxbj.bijihui.video.CustomRecordActivity;
import com.hxbj.bijihui.video.LuVideoActivity;
import com.hxbj.bijihui.video.VideoQuanpingActivity;
import com.hxbj.bijihui.video.VideoView;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;


public class GerenFragment extends BaseFragment implements GerenContract.GerenView, View.OnClickListener, VideoView.OnItemclickLinter {

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

    private String videopath;

    @Override
    public void onResume() {
        super.onResume();
        videopath = (String) SPUtils.get(getActivity(), "video", "");
        if (StringUtils.isBlank(videopath)) {
            meiyouwode.setVisibility(View.VISIBLE);
            youwode.setVisibility(View.GONE);
        } else {
            youwode.setVisibility(View.VISIBLE);
            meiyouwode.setVisibility(View.GONE);
        }
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

    private String url = "http://heixiong-wlf.oss-cn-beijing.aliyuncs.com/videos/outputCut.mp4";
    private String imgurl = "";
    private String title = "";
    private int jindu;
    private boolean luzhiquanxian;

    private void initquanxian() {
        AndPermission.with(this)
                .permission(Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE,
                        Permission.CAMERA,
                        Permission.RECORD_AUDIO
                )
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        luzhiquanxian = true;
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                luzhiquanxian = false;
            }
        }).start();

    }

    private int zannum1 = 0;
    private boolean ISzannum1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shangchuanwode:
                AndPermission.with(this)
                        .permission(Permission.WRITE_EXTERNAL_STORAGE,
                                Permission.READ_EXTERNAL_STORAGE,
                                Permission.CAMERA,
                                Permission.RECORD_AUDIO
                        )
                        .onGranted(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                getActivity().startActivity(CustomRecordActivity.getIntent(getActivity()));
                            }
                        }).onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        ToastUtils.showToast(getActivity(), "没有权限");
                    }
                }).start();

                break;
            case R.id.chonglu:
                initchonglu();

                break;
            case R.id.fenxiang:


                break;
            case R.id.zan:
                zannum.setText("" + zannum1);
                if (ISzannum1) {
                    ISzannum1 = false;
                    zannum1 = zannum1 - 1;
                    zan.setImageResource(R.drawable.zan_1);
                    zannum.setTextColor(getActivity().getResources().getColor(R.color.color_C9C9C9));
                    zannum.setText(zannum1 + "");
                } else {
                    ISzannum1 = true;
                    zannum1 = zannum1 + 1;
                    zan.setImageResource(R.drawable.zan);
                    zannum.setTextColor(getActivity().getResources().getColor(R.color.color_F2B95A));
                    zannum.setText(zannum1 + "");
                }

                break;
            case R.id.bofang:
                gerenvideo.setVisibility(View.VISIBLE);
                gerenvideo2.setVisibility(View.GONE);
                /*
                 * 将下面的变回原来的
                 * */
                gerenmyvideo.setVisibility(View.GONE);
                gerenmyvideo2.setVisibility(View.VISIBLE);
                if (videoView != null) {
                    videoView.onDestroy();
                    videoView = null;
                }
                videoView = new VideoView(getActivity());
                videoView.setOnItemclickLinter(this);

                gerenvideo.addView(videoView);
                videoView.setStart(url, imgurl, title);
                this.jindu = videoView.getProgress();
                break;
            case R.id.bofang2:
                gerenmyvideo.setVisibility(View.VISIBLE);
                gerenmyvideo2.setVisibility(View.GONE);
                /*
                 * 将上面的变回原来的
                 * */
                gerenvideo.setVisibility(View.GONE);
                gerenvideo2.setVisibility(View.VISIBLE);
                if (videoView != null) {
                    videoView.onDestroy();
                    videoView = null;
                }

                videoView = new VideoView(getActivity());
                videoView.setOnItemclickLinter(this);
                gerenmyvideo.addView(videoView);
                if (StringUtils.isBlank(videopath)) {
                    videoView.setStart(url, imgurl, title);
                } else {
                    videoView.setStart(videopath, imgurl, title);
                }


                this.jindu = videoView.getProgress();
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

        TextView fou = chonglu.findViewById(R.id.fou);
        TextView yes = chonglu.findViewById(R.id.yes);

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
        if (chongluWindow != null) {
            chongluWindow.dismiss();
            chongluWindow = null;
        }
        if (videoView!=null){
            videoView.onDestroy();
            videoView=null;
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

    @Override
    public void onItemClicj(View view) {
        startActivityForResult(VideoQuanpingActivity.getIntent(getActivity(),
                url, imgurl, title, jindu), 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == getActivity().RESULT_OK) {
            Bundle bundle = data.getExtras();
            int jindu = bundle.getInt("jindu");
            videoView.setJindu(jindu);
        }
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
