package com.hxbj.bijihui.module.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hxbj.bijihui.utils.GlidUtils;

import java.util.ArrayList;

public class HomeBannerAdapter extends PagerAdapter {
    private ArrayList<String> list;
    private Context context;

    public HomeBannerAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv=new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GlidUtils.setGrid(context,list.get(position % list.size()),iv);
        if (container != null) {
            container.removeView(iv);
        }
        container.addView(iv);
        return iv;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
