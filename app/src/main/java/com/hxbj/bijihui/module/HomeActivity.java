package com.hxbj.bijihui.module;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.base.FragmentManager;
import com.hxbj.bijihui.constants.Urls;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.model.bean.OssBean;
import com.hxbj.bijihui.module.geren.LianxiActivity;
import com.hxbj.bijihui.module.home.HomeFragment;
import com.hxbj.bijihui.module.landing.GerenActivity;
import com.hxbj.bijihui.module.landing.LandingActivity;
import com.hxbj.bijihui.module.web.MyService;
import com.hxbj.bijihui.module.web.WebViewCurrencyActivity;
import com.hxbj.bijihui.network.HttpFactory;
import com.hxbj.bijihui.network.MyCallBack;
import com.hxbj.bijihui.utils.AppUtils;
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

public class HomeActivity extends BaseActivity implements View.OnClickListener, HomeFragment.UpData {

    private AudioRecoderUtils mAudioRecoderUtils;
    private ImageView yinyue;
    private ArcProgress myProgress;
    private TextView mubiao;
    private TextView time;
    private ImageView bofang;
    private LinearLayout mubiaolinear;
    private HomeFragment homeFragment;


    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    private ImageView cehualan;
    private FrameLayout homeframe;
    private ActionBarDrawerToggle drawerbar;
    public DrawerLayout activity_na;
    //侧滑栏头部
    private ImageView cehua_touxiang;
    private TextView cehua_name;
    private TextView cehua_huiyuan;
    private TextView cehua_qianjun;
    private TextView cehua_lianxi;
    private TextView cehua_back;

    private PopupWindow popupWindow;
    private RelativeLayout main_left_drawer_layout;
    private MediaPlayer mediaPlayer;

    private String armpath;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AppUtils.setTitle(this);
        initView();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (StringUtils.isBlank((String) SPUtils.get(HomeActivity.this, StringStatic.DIYICI, ""))) {
                    if (!MyApp.instance.getType().equals("游客")) {
                        if (StringUtils.isBlank(MyApp.instance.getSoundUrl())) {
                            initData();
                        }
                    }
                }
//                initData();
            }
        }, 300);
        SPUtils.put(HomeActivity.this, StringStatic.DIYICI, "111");

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }


    private void initView() {
        cehualan = findViewById(R.id.cehualan);
        homeframe = findViewById(R.id.homeframe);

        activity_na = findViewById(R.id.activity_na);
        main_left_drawer_layout = findViewById(R.id.main_left_drawer_layout);
        cehua_touxiang = findViewById(R.id.cehua_touxiang);
        cehua_name = findViewById(R.id.cehua_name);
        cehua_huiyuan = findViewById(R.id.cehua_huiyuan);
        cehua_qianjun = findViewById(R.id.cehua_qianjun);
        cehua_lianxi = findViewById(R.id.cehua_lianxi);
        cehua_back = findViewById(R.id.cehua_back);


        cehualan.setOnClickListener(this);
        cehua_qianjun.setOnClickListener(this);
        cehua_lianxi.setOnClickListener(this);
        cehua_touxiang.setOnClickListener(this);
        cehua_back.setOnClickListener(this);
        homeFragment = new HomeFragment();
        homeFragment.setUpData(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.homeframe, homeFragment);
        transaction.commit();

        mAudioRecoderUtils = new AudioRecoderUtils();

        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time2) {
                //根据分贝值来设置录音时话筒图标的上下波动，下面有讲解
                myProgress.setProgress((int) (time2 / 1000));
                if (myProgress.getProgress() >= 10) {
                    myProgress.setVisibility(View.GONE);
                    mubiao.setVisibility(View.VISIBLE);
                    time.setVisibility(View.VISIBLE);
                    time.setText(TimeUtils.getTimedanqian() + "目标");
                    bofang.setVisibility(View.VISIBLE);
                    //结束录音（保存录音文件）
                    mAudioRecoderUtils.stopRecord();
                }

            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                armpath = filePath;
                LogUtils.e("TAG", filePath);
                try {
                    mediaPlayer.setDataSource(filePath);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private ImageView luzhi;
    private LinearLayout popo;
    private View popoview;
    private boolean Isquanxian;

    private void initData() {
        View view = getLayoutInflater().inflate(R.layout.home_popup, null);
        popupWindow = new PopupWindow(view);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //关闭事件
        popupWindow.setOnDismissListener(new popupDismissListener());
        backgroundAlpha(0.5f);

        AndPermission.with(HomeActivity.this)
                .permission(Permission.RECORD_AUDIO, Permission.WRITE_EXTERNAL_STORAGE,
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
                ToastUtils.showToast(HomeActivity.this, "请打开麦克风权限");
            }
        }).start();

        luzhi = view.findViewById(R.id.luzhi);
        popo = view.findViewById(R.id.popo);
        yinyue = view.findViewById(R.id.yinyue);
        mubiao = view.findViewById(R.id.mubiao);
        time = view.findViewById(R.id.time);
        bofang = view.findViewById(R.id.bofang);
        mubiaolinear = view.findViewById(R.id.mubiaolinear);

        myProgress = view.findViewById(R.id.myProgress);

        myProgress.setOnCenterDraw(new OnTextCenter());

        myProgress.setVisibility(View.GONE);
        mubiao.setVisibility(View.GONE);
        time.setVisibility(View.GONE);
        bofang.setVisibility(View.GONE);
        mubiaolinear.setVisibility(View.GONE);

        ImageView guanbi = view.findViewById(R.id.guanbi);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        setProgressBarVisibility(true);
        luzhi.setOnTouchListener(onTouchListener);

        bofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {

                } else {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.reset();
                    if (!StringUtils.isBlank(MyApp.instance.getSoundUrl())) {
                        try {
                            mediaPlayer.setDataSource(MyApp.instance.getSoundUrl());
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
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
                        mubiaolinear.setVisibility(View.VISIBLE);
                        yinyue.setVisibility(View.GONE);
                        myProgress.setVisibility(View.VISIBLE);

                        mubiao.setVisibility(View.GONE);
                        time.setVisibility(View.GONE);
                        bofang.setVisibility(View.GONE);
                        mAudioRecoderUtils.startRecord();
                        break;
                    //抬起
                    case MotionEvent.ACTION_UP:
                        if (myProgress.getProgress() >= 10) {

                        } else {
                            myProgress.setVisibility(View.GONE);
                            mubiao.setVisibility(View.VISIBLE);
                            time.setVisibility(View.VISIBLE);
                            time.setText(TimeUtils.getTimedanqian() + "目标");
                            bofang.setVisibility(View.VISIBLE);
                            //结束录音（保存录音文件）
                            mAudioRecoderUtils.stopRecord();
                        }
                        if (StringUtils.isBlank(accessKeyId)) {
                            gettokent();
                        }

                        break;
                }

            } else {
                ToastUtils.showToast(HomeActivity.this, "请打开麦克风权限");
            }


            return true;
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //侧滑菜单键
            case R.id.cehualan:
                if (activity_na.isDrawerOpen(main_left_drawer_layout)) {
                    activity_na.closeDrawer(main_left_drawer_layout);
                } else {
                    activity_na.openDrawer(main_left_drawer_layout);
                }

                break;
            case R.id.cehua_qianjun:
                startActivity(WebViewCurrencyActivity.getIntent(HomeActivity.this, "", "千钧体育"));

                break;
            case R.id.cehua_lianxi:
                startActivity(LianxiActivity.getIntent(HomeActivity.this));

                break;
            case R.id.cehua_back:
                try {
                    SPUtils.deleteAll(HomeActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(LandingActivity.getIntent(HomeActivity.this));
                finish();
                break;
            case R.id.cehua_touxiang:
                startActivity(GerenActivity.getIntent(HomeActivity.this, "no"));
//                loginBean
                break;

        }
    }

    //设置开关监听
    private void initEvent() {
        drawerbar = new ActionBarDrawerToggle(this, activity_na, null, R.string.open, R.string.close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        activity_na.setDrawerListener(drawerbar);
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

    public void gettokent() {
        HttpFactory.create().get(Urls.GETOSSTOKEN, null, new MyCallBack<OssBean>() {
            @Override
            public void onSuccess(OssBean ossBean) {
                if (ossBean.getCode() == 2000) {
                    accessKeySecret = ossBean.getData().getAccessKeySecret();
                    accessKeyId = ossBean.getData().getAccessKeyId();
                    securityToken = ossBean.getData().getSecurityToken();
                }
                if (!StringUtils.isBlank(armpath)) {
                    setShangChuan(armpath);
                }

            }

            @Override
            public void onFaile(String msg) {

            }
        });


    }

    private void setShangChuan(String imagePath) {
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
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);

//        String originalFilename=".amr";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        //重命名文件名称
//        String fileName = sdf.format(new Date());
//        //随机5位数
//        String random  = RandomStringUtils.randomNumeric(5);
//        if (originalFilename.lastIndexOf(".") != -1){
//            fileName = fileName+random+originalFilename.substring(originalFilename.lastIndexOf("."));
//        }

        PutObjectRequest put = new PutObjectRequest("heixiong-club", "sound/" + MyApp.instance.getId() + ".amr", imagePath);
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
                        MyApp.instance.saveLogin(loginBean.getData(), HomeActivity.this);

                    }

                    @Override
                    public void onFaile(String msg) {

                    }
                });

            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                ToastUtils.showToast(HomeActivity.this, "服务器异常");

            }
        });


    }

    private LoginBean loginBean;

    @Override
    public void upData(LoginBean loginBean) {
        this.loginBean = loginBean;
        GlidUtils.setGrid2(this, loginBean.getData().getPicUrl(), cehua_touxiang);
        cehua_name.setText(loginBean.getData().getNickname());
        if (loginBean.getData().getType().equals("会员")) {
            cehua_huiyuan.setVisibility(View.VISIBLE);
        } else {
            cehua_huiyuan.setVisibility(View.INVISIBLE);
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

    @Override
    protected void onDestroy() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
        if (mAudioRecoderUtils != null) {
            mAudioRecoderUtils.stopRecord();
            mAudioRecoderUtils = null;
        }
        unbindService(serviceConnection);
        super.onDestroy();
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(HomeActivity.this, "再按一次退出程序!", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }


}
