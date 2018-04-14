package com.hxbj.bijihui.video;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.module.HomeActivity;

public class VideoQuanpingActivity extends AppCompatActivity {

    public static Intent getIntent(Context context,String url,String imgurl,String title,int jindu){
        Intent intent=new Intent(context,VideoQuanpingActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("imgurl",imgurl);
        intent.putExtra("title",title);
        intent.putExtra("jindu",jindu);
        return intent;
    }
    private String url;
    private String imgurl;
    private String title;
    private int jindu;
    private RelativeLayout quanpingvideo;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_quanping);
        url=getIntent().getStringExtra("url");
        imgurl=getIntent().getStringExtra("imgurl");
        title=getIntent().getStringExtra("title");
        jindu=getIntent().getIntExtra("jindu",0);

        initView();
        videoView = new VideoView(this);
        quanpingvideo.addView(videoView);
        videoView.setStart(url,imgurl,title);
        videoView.setJindu(jindu);

    }

    private void initView() {
        quanpingvideo = (RelativeLayout) findViewById(R.id.quanpingvideo);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                Intent intent=getIntent();
                Bundle bundle =new Bundle();
                bundle.putInt("jindu",jindu);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && Build.VERSION.SDK_INT >= 19){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onDestroy() {
        if (videoView!=null){
            videoView.onDestroy();
            videoView=null;
        }
        super.onDestroy();
    }
}
