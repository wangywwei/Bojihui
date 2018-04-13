package com.hxbj.bijihui.utils;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

public class MediaPlayerUtils {

    private MediaPlayerUtils mediaPlayerUtils;
    public static MediaPlayer mediaPlayer;

    public MediaPlayerUtils() {
        mediaPlayer=new MediaPlayer();
        this.mediaPlayerUtils = mediaPlayerUtils;

    }


    public static void mediaStart(Context context,String filePath){

        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

    }


}
