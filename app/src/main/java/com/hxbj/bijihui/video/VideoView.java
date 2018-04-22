package com.hxbj.bijihui.video;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.utils.GlidUtils;
import com.hxbj.bijihui.utils.StringUtils;
import com.hxbj.bijihui.utils.TimeUtils;
import com.tencent.rtmp.ITXVodPlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class VideoView extends LinearLayout implements View.OnClickListener {
    private Activity context;
    private TXVodPlayer mVodPlayer;

    public static VideoView instance;
    private int progress;

    public VideoView(Activity context) {
        this(context, null);
    }

    public VideoView(Activity context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoView(Activity context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        instance = this;
        initView();
    }

    private TXCloudVideoView video_view;
    private ImageView bofangbeijing;
    private TextView video_name;
    private ImageView bofang;
    private ImageView bofang2;
    private TextView time;
    private SeekBar seekbar;
    private TextView time2;
    private ImageView quanping;

    public int getProgress() {
        return (int) mVodPlayer.getCurrentPlaybackTime();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.videoview, this);
        video_view = view.findViewById(R.id.video_view);
        bofangbeijing = view.findViewById(R.id.bofangbeijing);
        video_name = view.findViewById(R.id.video_name);
        bofang = view.findViewById(R.id.bofang);
        bofang2 = view.findViewById(R.id.bofang2);
        time = view.findViewById(R.id.time);
        seekbar = view.findViewById(R.id.seekbar);
        time2 = view.findViewById(R.id.time2);
        quanping = view.findViewById(R.id.quanping);

        bofang.setOnClickListener(this);
        bofang2.setOnClickListener(this);
        quanping.setOnClickListener(this);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 调整进度
                mVodPlayer.seek(seekBar.getProgress());
            }
        });

        //创建player对象
        mVodPlayer = new TXVodPlayer(context);
        //关键player对象与界面view
        mVodPlayer.setPlayerView(video_view);

        mVodPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
//        mVodPlayer.setRenderRotation(90);
        if (mVodPlayer.isPlaying()) {
            bofang.setImageResource(R.drawable.bofang);
        } else {
            bofang.setImageResource(R.drawable.bofang);
        }




        mVodPlayer.setVodListener(new ITXVodPlayListener() {
            @Override
            public void onPlayEvent(TXVodPlayer txVodPlayer, int i, Bundle bundle) {
                if (i == TXLiveConstants.PLAY_EVT_PLAY_PROGRESS) {
                    // 播放进度, 单位是秒
                    progress = bundle.getInt(TXLiveConstants.EVT_PLAY_PROGRESS);
                    seekbar.setProgress(progress);
                    time.setText(TimeUtils.getTime(progress));

                    // 视频总长, 单位是秒
                    int duration = bundle.getInt(TXLiveConstants.EVT_PLAY_DURATION);
                    // 可以用于设置时长显示等等
                    seekbar.setMax(duration);
                    time2.setText(TimeUtils.getTime(duration));

                } else if (i == TXLiveConstants.PLAY_EVT_PLAY_END) {
                    bofang.setImageResource(R.drawable.bofang);
                    bofang.setVisibility(VISIBLE);
                    bofang2.setImageResource(R.drawable.bofang_2);

                }
            }

            @Override
            public void onNetStatus(TXVodPlayer txVodPlayer, Bundle bundle) {

            }
        });

    }





    public VideoView setJindu(int jindu) {
//        mVodPlayer.pause();
//        mVodPlayer.stopPlay(true);
//        // 调整进度
//        setStart(videourl,imgurl,title);
        mVodPlayer.seek(jindu);
        return instance;
    }
    private String videourl;
    private String imgurl;
    private String title;
    public VideoView setStart(String videourl, String imgurl, String title) {
        this.videourl = videourl;
        this.imgurl = imgurl;
        this.title = title;
        GlidUtils.setGrid(context, imgurl, bofangbeijing);
        video_name.setText(title);
        if (!StringUtils.isBlank(videourl)) {
            //开始播放
            mVodPlayer.startPlay(videourl);
            bofangbeijing.setVisibility(GONE);
            video_view.setVisibility(VISIBLE);
            bofang2.setImageResource(R.drawable.zanting_2);
            bofang.setVisibility(GONE);
        }
        return instance;
    }

    public void zanting(){
        mVodPlayer.pause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bofang:
                if (mVodPlayer.isPlaying()) {
                    mVodPlayer.pause();
                    mVodPlayer.stopPlay(true); // true代表清除最后一帧画面
                } else {
                    if (!StringUtils.isBlank(videourl)) {
                        mVodPlayer.startPlay(videourl);
                        bofangbeijing.setVisibility(GONE);
                        video_view.setVisibility(VISIBLE);
                        bofang2.setImageResource(R.drawable.zanting_2);
                        bofang.setVisibility(GONE);
                    }
                }
                break;
            case R.id.bofang2:
                if (mVodPlayer.isPlaying()) {
                    mVodPlayer.pause();
                    bofang2.setImageResource(R.drawable.bofang_2);
                } else {
                    mVodPlayer.resume();
                    bofang2.setImageResource(R.drawable.zanting_2);
                }
                break;
            case R.id.quanping:

                if (null!=onItemclickLinter){
                    onItemclickLinter.onItemClicj(v);
                }
                break;
        }
    }



    public void onDestroy() {
        if (mVodPlayer!=null){
            mVodPlayer.pause();
            mVodPlayer.stopPlay(true); // true代表清除最后一帧画面
            video_view.onDestroy();
        }

    }

    private OnItemclickLinter onItemclickLinter;

    public void setOnItemclickLinter(OnItemclickLinter onItemclickLinter) {
        this.onItemclickLinter = onItemclickLinter;
    }

    public interface OnItemclickLinter{
        public void onItemClicj(View view);
    }

}
