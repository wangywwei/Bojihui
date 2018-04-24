package com.hxbj.bijihui.module.quanming.geren;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.hxbj.bijihui.constants.Urls;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.bean.DianzanBean;
import com.hxbj.bijihui.model.bean.GuanVideoBean;
import com.hxbj.bijihui.network.HttpFactory;
import com.hxbj.bijihui.network.MyCallBack;
import com.hxbj.bijihui.utils.SPUtils;
import com.hxbj.bijihui.utils.StringUtils;
import com.hxbj.bijihui.utils.ToastUtils;
import com.hxbj.bijihui.video.CustomRecordActivity;
import com.hxbj.bijihui.video.LuVideoActivity;
import com.hxbj.bijihui.video.VideoQuanpingActivity;
import com.hxbj.bijihui.video.VideoView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.internal.Util;


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
    private String id;
    private PopupWindow fenxiangpopo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_geren, null);
            initView(view);

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


    @Override
    public void onResume() {
        super.onResume();
        initData();
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
    public void setResultData(GuanVideoBean guanVideoBean) {
        if (guanVideoBean.getData().size() == 1) {
            meiyouwode.setVisibility(View.VISIBLE);
            youwode.setVisibility(View.GONE);
            url = guanVideoBean.getData().get(0).getVideoUrl();
            imgurl = guanVideoBean.getData().get(0).getCoverUrl();
            title = guanVideoBean.getData().get(0).getTitle();

        } else {
            youwode.setVisibility(View.VISIBLE);
            meiyouwode.setVisibility(View.GONE);
            for (int i = 0; i < guanVideoBean.getData().size(); i++) {
                if (guanVideoBean.getData().get(i).getType() == 0) {
                    url = guanVideoBean.getData().get(i).getVideoUrl();
                    imgurl = guanVideoBean.getData().get(i).getCoverUrl();
                    title = guanVideoBean.getData().get(i).getTitle();

                } else if (guanVideoBean.getData().get(i).getType() == 1) {
                    url2 = guanVideoBean.getData().get(i).getVideoUrl();
                    imgurl2 = guanVideoBean.getData().get(i).getCoverUrl();
                    title2 = guanVideoBean.getData().get(i).getTitle();
                    id = guanVideoBean.getData().get(i).getId();
                    if (guanVideoBean.getData().get(i).getThumbType()==0){
                        zan.setImageResource(R.drawable.zan_1);
                        zannum.setTextColor(getActivity().getResources().getColor(R.color.color_C9C9C9));
                        zannum.setText(guanVideoBean.getData().get(i).getThumb() + "");
                    } else {
                        zan.setImageResource(R.drawable.zan);
                        zannum.setTextColor(getActivity().getResources().getColor(R.color.color_F2B95A));
                        zannum.setText(guanVideoBean.getData().get(i).getThumb() + "");
                    }

                }
            }
        }

    }

    @Override
    public void setPresenter(GerenContract.GerenPresenter gerenPresenter) {
        this.gerenPresenter = gerenPresenter;
    }

    private String url;
    private String imgurl;
    private String title;

    private String url2;
    private String imgurl2;
    private String title2;

    private int jindu;
    private boolean luzhiquanxian;
    private int zannum1 = 0;
    private boolean ISzannum1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shangchuanwode:
                AndPermission.with(getActivity())
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

                fenxiangpopo();
                break;
            case R.id.zan:
                zannum.setText("" + zannum1);
                if (ISzannum1) {
                    ISzannum1 = false;
                    dinazan(id, "0", zan, zannum);
                } else {
                    ISzannum1 = true;
                    dinazan(id, "1", zan, zannum);
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
                videoView.setStart(url2, imgurl2, title2);

                this.jindu = videoView.getProgress();
                break;
        }
    }

    private void fenxiangpopo() {
        View fenxiangview = getLayoutInflater().inflate(R.layout.fenxiangpopo, null);
        fenxiangpopo = new PopupWindow(fenxiangview);
        fenxiangpopo.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        fenxiangpopo.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        fenxiangpopo.setBackgroundDrawable(new BitmapDrawable());
        fenxiangpopo.setOutsideTouchable(true);
        fenxiangpopo.setFocusable(true);
        fenxiangpopo.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        //关闭事件
        fenxiangpopo.setOnDismissListener(new popupDismissListener());
        backgroundAlpha(0.5f);

        ImageView weixinhaoyou=fenxiangview.findViewById(R.id.weixinhaoyou);
        ImageView pengyouquan=fenxiangview.findViewById(R.id.pengyouquan);
        TextView quxiao=fenxiangview.findViewById(R.id.quxiao);

        WXVideoObject videoObject=new WXVideoObject();
        videoObject.videoUrl=url2;

        WXMediaMessage mediaMessage=new WXMediaMessage(videoObject);
        mediaMessage.title="黑熊搏击会";
        mediaMessage.description="我的视频";
//        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        final SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction="video";
        req.message=mediaMessage;

        weixinhaoyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req.scene=SendMessageToWX.Req.WXSceneSession;
                MyApp.iwxapi.sendReq(req);
            }
        });

        pengyouquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req.scene=SendMessageToWX.Req.WXSceneTimeline;
                MyApp.iwxapi.sendReq(req);
            }
        });

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fenxiangpopo.dismiss();
            }
        });
    }

    private void dinazan(String id, String thumbType, final ImageView zan, final TextView zannum) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", MyApp.instance.getId());
        map.put("id", id);
        map.put("thumbType", thumbType);
        HttpFactory.create().post(Urls.UPDATETHUMB, map, new MyCallBack<DianzanBean>() {
            @Override
            public void onSuccess(DianzanBean dianzanBean) {
                if (dianzanBean.getData().getThumbType() == 0) {
                    zan.setImageResource(R.drawable.zan_1);
                    zannum.setTextColor(getActivity().getResources().getColor(R.color.color_C9C9C9));
                    zannum.setText(dianzanBean.getData().getThumb() + "");
                } else {
                    zan.setImageResource(R.drawable.zan);
                    zannum.setTextColor(getActivity().getResources().getColor(R.color.color_F2B95A));
                    zannum.setText(dianzanBean.getData().getThumb() + "");
                }
            }

            @Override
            public void onFaile(String msg) {

            }
        });

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
                AndPermission.with(getActivity())
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

            }
        });
    }

    @Override
    public void onDestroy() {
        if (chongluWindow != null) {
            chongluWindow.dismiss();
            chongluWindow = null;
        }
        if (videoView != null) {
            videoView.onDestroy();
            videoView = null;
        }
        if (fenxiangpopo != null) {
            fenxiangpopo.dismiss();
            fenxiangpopo = null;
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
