package com.hxbj.bijihui.module.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.hxbj.bijihui.R;
import com.hxbj.bijihui.model.bean.HomeBannerImageBean;
import com.hxbj.bijihui.utils.GlidUtils;
import com.hxbj.bijihui.utils.GsonUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;


import java.util.ArrayList;
import java.util.List;

/*
* 首页banner
* */
public class HomeBanner extends LinearLayout{
    private Context context;

    private List<HomeBannerImageBean> data2EntityList = new ArrayList<>();
    private Banner banner;

    public HomeBanner(Context context) {
        this(context,null);

    }

    public HomeBanner(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomeBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.home_banner, this);


        HomeBannerImageBean homeBannerImageBean=null;
        for (int i = 0; i <6; i++) {
            homeBannerImageBean=new HomeBannerImageBean();
            homeBannerImageBean.setImg("");
            homeBannerImageBean.setLabel("");
            homeBannerImageBean.setLabel2("");
            homeBannerImageBean.setTitle("");
            homeBannerImageBean.setAndroid_url("");
            data2EntityList.add(homeBannerImageBean);
        }


        banner = view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //设置图片集合
        banner.setImages(data2EntityList);
        List<String> titleList = getTitleList(data2EntityList);
        banner.setBannerTitles(titleList) ;

        List<String> lableList = getLableList(data2EntityList);
        banner.setBannerLables(lableList) ;

        List<String> lableList2 = getLableList2(data2EntityList);
        banner.setBannerLables2(lableList2) ;
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        //                        banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //Glide 加载图片简单用法
            HomeBannerImageBean pathbean = GsonUtil.GsonToBean(new Gson().toJson(path), HomeBannerImageBean.class);
            if (pathbean != null && pathbean.getImg() != null) {
                GlidUtils.getPic(pathbean.getImg(), imageView, context);
            }
        }
    }

    private List<String> getTitleList(List<HomeBannerImageBean> list) {
        List<String> titleList = new ArrayList<>();
        if (list == null) {
            return titleList;
        }
        for (int i = 0; i < list.size(); i++) {
            titleList.add(list.get(i).getTitle());
        }
        return titleList;
    }
    private List<String> getLableList(List<HomeBannerImageBean> list) {
        List<String> titleList = new ArrayList<>();
        if (list == null) {
            return titleList;
        }
        for (int i = 0; i < list.size(); i++) {
            titleList.add(list.get(i).getLabel());
        }
        return titleList;
    }

    private List<String> getLableList2(List<HomeBannerImageBean> list) {
        List<String> titleList = new ArrayList<>();
        if (list == null) {
            return titleList;
        }
        for (int i = 0; i < list.size(); i++) {
            titleList.add(list.get(i).getLabel2());
        }
        return titleList;
    }



}
