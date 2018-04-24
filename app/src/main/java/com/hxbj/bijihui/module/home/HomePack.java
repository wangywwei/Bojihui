package com.hxbj.bijihui.module.home;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.hxbj.bijihui.R;
import com.hxbj.bijihui.constants.Urls;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.model.bean.OssBean;
import com.hxbj.bijihui.module.HomeActivity;
import com.hxbj.bijihui.module.kechen.KechenActivity;
import com.hxbj.bijihui.module.landing.LandingActivity;
import com.hxbj.bijihui.module.quanming.QuanMingActivity;
import com.hxbj.bijihui.network.HttpFactory;
import com.hxbj.bijihui.network.MyCallBack;
import com.hxbj.bijihui.utils.AudioRecoderUtils;
import com.hxbj.bijihui.utils.GlidUtils;
import com.hxbj.bijihui.utils.LogUtils;
import com.hxbj.bijihui.utils.SPUtils;
import com.hxbj.bijihui.utils.StringStatic;
import com.hxbj.bijihui.utils.StringUtils;
import com.hxbj.bijihui.utils.TimeUtils;
import com.hxbj.bijihui.utils.ToastUtils;
import com.hxbj.bijihui.view.ArcProgress;
import com.hxbj.bijihui.view.OnTextCenter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 首页第三块
 * */
public class HomePack extends LinearLayout implements View.OnClickListener {

    private Activity context;
    private ImageView nahandemubiao;
    private ImageView daka1;
    private ImageView home_quanbukecheng;
    private PopupWindow nahanpopup;
    private PopupWindow dakapopo;
    private ImageView quanminzhanshi;
    private AudioRecoderUtils mAudioRecoderUtils;
    private MediaPlayer mediaPlayer;
    private String armpath;

    private LoginBean loginBean;
    private PopupWindow loginpopup;

    public void setHomePackBean(LoginBean loginBean) {
        this.loginBean = loginBean;


    }

    public HomePack(Activity context) {
        this(context, null);
    }

    public HomePack(Activity context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomePack(Activity context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.home_pack, this);

        nahandemubiao = view.findViewById(R.id.nahandemubiao);
        daka1 = view.findViewById(R.id.daka1);
        home_quanbukecheng = view.findViewById(R.id.home_quanbukecheng);
        quanminzhanshi = view.findViewById(R.id.quanminzhanshi);

        quanminzhanshi.setOnClickListener(this);
        nahandemubiao.setOnClickListener(this);
        daka1.setOnClickListener(this);
        home_quanbukecheng.setOnClickListener(this);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource(MyApp.instance.getSoundUrl());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mAudioRecoderUtils = new AudioRecoderUtils();

        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time2) {
                //根据分贝值来设置录音时话筒图标的上下波动，下面有讲解
                myProgress.setProgress((int) (time2 / 1000));
                if (myProgress.getProgress() >= 10) {
                    time.setText(TimeUtils.getTimedanqian() + "目标");
                    bofang.setVisibility(View.VISIBLE);
                    myProgress.setVisibility(View.GONE);
                    //结束录音（保存录音文件）
                    mAudioRecoderUtils.stopRecord();
                }

            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                LogUtils.e("TAG", filePath);
                armpath = filePath;
                MyApp.instance.setSoundUrl(armpath);
                try {
                    mediaPlayer.setDataSource(filePath);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nahandemubiao:
                if (!MyApp.instance.getType().equals("游客")) {
                    initNahan();
                } else {
                    initlogin();
                }
                break;
            case R.id.daka1:
                if (!MyApp.instance.getType().equals("游客")) {
                    initDaka();
                    initdaka2();
                } else {
                    initlogin();
                }

                break;
            case R.id.home_quanbukecheng:
                context.startActivity(KechenActivity.getIntent(context));
                break;
            case R.id.quanminzhanshi:
                if (!MyApp.instance.getType().equals("游客")) {
                    context.startActivity(QuanMingActivity.getIntent(context));
                } else {
                    initlogin();
                }
                break;

        }
    }

    private void initdaka2() {
        Map<String, String> map = new HashMap<>();
        map.put("iphone", MyApp.instance.getIphone());
        map.put("punch", "1");
        HttpFactory.create().post(Urls.UPDATEUSER, map, new MyCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {


            }

            @Override
            public void onFaile(String msg) {

            }
        });

    }

    private ImageView qiandaotu;
    private ImageView daka_touxiang;
    private ImageView daka_huiyuan;
    private TextView daka_name;
    private TextView daka_liangji;
    private ImageView fenxiangweixing;
    private ImageView fenxiangpengyouquan;
    private ImageView fenxiangweibo;

    private void initDaka() {
        View dakaview = context.getLayoutInflater().inflate(R.layout.home_popup3, null);
        dakapopo = new PopupWindow(dakaview);
        dakapopo.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        dakapopo.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        dakapopo.setBackgroundDrawable(new BitmapDrawable());
        dakapopo.setOutsideTouchable(true);
        dakapopo.setFocusable(true);
        dakapopo.showAtLocation(context.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //关闭事件
        dakapopo.setOnDismissListener(new popupDismissListener());
        backgroundAlpha(0.5f);

        qiandaotu = dakaview.findViewById(R.id.qiandaotu);
        daka_touxiang = dakaview.findViewById(R.id.daka_touxiang);
        daka_huiyuan = dakaview.findViewById(R.id.daka_huiyuan);
        daka_name = dakaview.findViewById(R.id.daka_name);
        daka_liangji = dakaview.findViewById(R.id.daka_liangji);
        fenxiangweixing = dakaview.findViewById(R.id.fenxiangweixing);
        fenxiangpengyouquan = dakaview.findViewById(R.id.fenxiangpengyouquan);
        fenxiangweibo = dakaview.findViewById(R.id.fenxiangweibo);

        GlidUtils.setGrid2(context, MyApp.instance.getPicUrl(), daka_touxiang);
        if (StringUtils.isBlank(MyApp.instance.getNickname())) {
            daka_name.setText(MyApp.instance.getIphone());
        } else {
            daka_name.setText(MyApp.instance.getNickname());
        }


        if (MyApp.instance.getType().equals("会员")) {
            daka_huiyuan.setVisibility(VISIBLE);
        } else {
            daka_huiyuan.setVisibility(GONE);
        }

        daka_liangji.setText(MyApp.instance.getLevel());
        ImageView guanbi = dakaview.findViewById(R.id.guanbi);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dakapopo.dismiss();
            }
        });

    }

    private TextView time;
    private ImageView bofang;
    private TextView shanchu;
    private ImageView newbutton;
    private LinearLayout popo;
    private ArcProgress myProgress;
    private boolean Isquanxian;
    private void initNahan() {
        View nahanview = context.getLayoutInflater().inflate(R.layout.home_popup2, null);
        nahanpopup = new PopupWindow(nahanview);
        nahanpopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        nahanpopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        nahanpopup.setBackgroundDrawable(new BitmapDrawable());
        nahanpopup.setOutsideTouchable(true);
        nahanpopup.setFocusable(true);
        nahanpopup.showAtLocation(context.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //关闭事件
        nahanpopup.setOnDismissListener(new popupDismissListener());
        backgroundAlpha(0.5f);

        AndPermission.with(context)
                .permission(Permission.RECORD_AUDIO,
                        Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Isquanxian = true;

                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                Isquanxian = false;
                ToastUtils.showToast(context, "请打开麦克风权限");
            }
        }).start();

        time = nahanview.findViewById(R.id.time);
        bofang = nahanview.findViewById(R.id.bofang);

        newbutton = nahanview.findViewById(R.id.newbutton);
        popo = nahanview.findViewById(R.id.popo);
        myProgress = nahanview.findViewById(R.id.myProgress);
        myProgress.setOnCenterDraw(new OnTextCenter());
        myProgress.setVisibility(GONE);
        ImageView guanbi = nahanview.findViewById(R.id.guanbi);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nahanpopup.dismiss();
            }
        });

        if (StringUtils.isBlank(loginBean.getData().getSoundTime())) {
            time.setText("");
        } else {
            time.setText(loginBean.getData().getSoundTime() + "目标");
        }

        bofang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    return;
                } else {
                    if (StringUtils.isBlank(MyApp.instance.getSoundUrl())) {
                        ToastUtils.showToast(context, "您还没有录制呐喊目标，请录制！");
                    } else {
                        mediaPlayer.start();
                    }

                }
            }
        });

        newbutton.setOnTouchListener(onTouchListener);
    }

    private String accessKeySecret;
    private String accessKeyId;
    private String securityToken;

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, final MotionEvent event) {
            if (Isquanxian) {
            switch (event.getAction()) {
                //按下
                case MotionEvent.ACTION_DOWN:

                    bofang.setVisibility(View.GONE);
                    myProgress.setVisibility(View.VISIBLE);
                    mAudioRecoderUtils.startRecord();


                    break;
                //抬起
                case MotionEvent.ACTION_UP:
                    if (myProgress.getProgress() >= 10) {

                    } else {
                        time.setText(TimeUtils.getTimedanqian() + "目标");
                        bofang.setVisibility(View.VISIBLE);
                        myProgress.setVisibility(View.GONE);
                        //结束录音（保存录音文件）
                        mAudioRecoderUtils.stopRecord();
                    }

                    if (StringUtils.isBlank(accessKeyId)) {
                        gettokent();
                    } else {
                        if (!StringUtils.isBlank(armpath)) {
                            setShangChuan(armpath);
                        }
                    }

                    break;
            }}else {
                ToastUtils.showToast(context, "请打开麦克风权限");
            }


            return true;
        }
    };


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
    }

    public void gettokent() {
        HttpFactory.create().get(Urls.GETOSSTOKEN, null, new MyCallBack<OssBean>() {
            @Override
            public void onSuccess(OssBean ossBean) {
                if (ossBean.getCode() == 2000) {
                    accessKeySecret = ossBean.getData().getAccessKeySecret();
                    accessKeyId = ossBean.getData().getAccessKeyId();
                    securityToken = ossBean.getData().getSecurityToken();
                    if (!StringUtils.isBlank(armpath)) {
                        setShangChuan(armpath);
                    }
                }
            }

            @Override
            public void onFaile(String msg) {

            }
        });
    }

    public void setShangChuan(String shangChuan) {
        if (StringUtils.isBlank(accessKeyId)) {
            return;
        }
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考访问控制章节
        // 也可查看sample 中 sts 使用方式了解更多(https://github.com/aliyun/aliyun-oss-android-sdk/tree/master/app/src/main/java/com/alibaba/sdk/android/oss/app)
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(
                accessKeyId,
                accessKeySecret,
                securityToken);
        //该配置类如果不设置，会有默认配置，具体可看该类
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(context.getApplicationContext(), endpoint, credentialProvider);

//        String originalFilename=".amr";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        //重命名文件名称
//        String fileName = sdf.format(new Date());
//        //随机5位数
//        String random  = RandomStringUtils.randomNumeric(5);
//        if (originalFilename.lastIndexOf(".") != -1){
//            fileName = fileName+random+originalFilename.substring(originalFilename.lastIndexOf("."));
//        }

        PutObjectRequest put = new PutObjectRequest("heixiong-club", "sound/" + MyApp.instance.getId() + ".amr", shangChuan);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
//                Log.e("TAG--PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });
//        final String finalFileName = fileName;
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Map<String, String> map = new HashMap<>();
                map.put("iphone", MyApp.instance.getIphone());
                map.put("soundUrl", MyApp.instance.getId() + ".amr");
                HttpFactory.create().post(Urls.UPDATEUSER, map, new MyCallBack<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        MyApp.instance.saveLogin(loginBean.getData(), context);

                    }

                    @Override
                    public void onFaile(String msg) {

                    }
                });

            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                ToastUtils.showToast(context, "服务器异常");

            }
        });


    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     */
    class popupDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
            if (null != onItemclickLinter) {
                onItemclickLinter.onItemClicj();
            }
        }

    }

    public void onDestroy() {
        if (nahanpopup != null) {
            nahanpopup.dismiss();
            nahanpopup = null;
        }
        if (dakapopo != null) {
            dakapopo.dismiss();
            dakapopo = null;
        }
    }

    public interface OnItemclickLinter {
        public void onItemClicj();
    }

    private OnItemclickLinter onItemclickLinter;

    public void setOnItemclickLinter(OnItemclickLinter onItemclickLinter) {
        this.onItemclickLinter = onItemclickLinter;
    }


    private void initlogin() {
        View loginview = context.getLayoutInflater().inflate(R.layout.login_popup, null);
        loginpopup = new PopupWindow(loginview);
        loginpopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        loginpopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        loginpopup.setBackgroundDrawable(new BitmapDrawable());
        loginpopup.setOutsideTouchable(true);
        loginpopup.setFocusable(true);
        loginpopup.showAtLocation(this.context.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //关闭事件
        loginpopup.setOnDismissListener(new popupDismissListener());
        ImageView guanbi = loginview.findViewById(R.id.guanbi);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginpopup.dismiss();
            }
        });
        backgroundAlpha(0.5f);
        ImageView denglv = loginview.findViewById(R.id.denglv);

        denglv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(LandingActivity.getIntent(context));
            }
        });
    }
}
