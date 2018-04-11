package com.hxbj.bijihui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hxbj.bijihui.R;

/**
 * Created by Administrator on 2018/1/12.
 */

public class GlidUtils {


    //普通的图片
    public static void setGrid(Context context, String url, ImageView view){
        Glide
                .with(context)
                .load(url)
                .placeholder(R.drawable.jiazaishibai_1)
                .error(R.drawable.jiazaishibai_1)
                .crossFade(1000)
                .into(view);
    }

    //圆形图片
    public static void setGrid2(Context context, String url, ImageView view){
        Glide
                .with(context)
                .load(url)
                .error(R.drawable.touxiang1)
                .bitmapTransform(new GlideCircleTransform(context))
                .crossFade(1000)
                .into(view);
    }

    //设置背景图
    public static void setBackground(Context context, String url, final View view){
        Glide.with(context)
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(180,180) {//设置宽高
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Drawable drawable = new BitmapDrawable(resource);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            view.setBackground(drawable);//设置背景
                        }
                    }
                });
    }


    //将url转换成图片,支持GIF或者jpg等
    public static void getPic(String url, ImageView view, Context context) {
        if (!StringUtils.isEmpty(url)) {
            if (url.contains(".gif") || url.contains(".GIF")) {
                Glide.with(context)
                        .load(url)
                        .asGif()
                        .placeholder(R.drawable.jiazaishibai_1)
                        .error(R.drawable.jiazaishibai_1)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(view);
            } else {
                Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .placeholder(R.drawable.jiazaishibai_1)
                        .error(R.drawable.jiazaishibai_1)
                        .into(view);
            }
        } else {
            view.setImageResource(R.drawable.jiazaishibai_1);
        }
    }

}
