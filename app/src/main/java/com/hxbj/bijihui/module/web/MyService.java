package com.hxbj.bijihui.module.web;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.SeekBar;

import com.hxbj.bijihui.global.MyApp;

/**
 * Created by 52926 on 2017/4/28.
 */

public class MyService extends Service {
    private MediaPlayer player;
    private String path;
    private String[] str;
    private SeekBar seekBar;
    private int position;

    @Override
    public void onCreate() {
        super.onCreate();
        play();

    }

    class MyBinder extends Binder {
        public void start() {
            player.start();
        }
    }

    private void play() {
        player = new MediaPlayer();
        player.reset();
        try {
            player.setDataSource(MyApp.instance.getSoundUrl());
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }


}
