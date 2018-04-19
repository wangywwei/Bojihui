package com.hxbj.bijihui.model;


import com.hxbj.bijihui.constants.Urls;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.bean.GuanVideoBean;
import com.hxbj.bijihui.model.bean.HomeBannerBean;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.model.bean.OssBean;
import com.hxbj.bijihui.network.HttpFactory;
import com.hxbj.bijihui.network.MyCallBack;
import com.hxbj.bijihui.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class BojihuiModelImp implements BojihuiModel {

    @Override
    public void getLogin(MyCallBack<LoginBean> callBack,String iphone) {
        HttpFactory.create().get(Urls.USERINFO+"/"+iphone, null, callBack);
    }

    @Override
    public void getBanner(MyCallBack<HomeBannerBean> callBack) {
        HttpFactory.create().get(Urls.BANNER, null, callBack);
    }

    @Override
    public void getUpdateUser(MyCallBack<LoginBean> callBack, String nickname, String sex, String picUrl, String birthday, String keyWord) {
        Map<String ,String> map=new HashMap<>();
        map.put("iphone", MyApp.instance.getIphone());
        map.put("nickname",nickname);
        map.put("sex",sex);
        map.put("picUrl",picUrl);
        map.put("birthday",birthday);
        map.put("keyWord",keyWord);

        HttpFactory.create().post(Urls.UPDATEUSER, map, callBack);
    }

    @Override
    public void getOSSToken(MyCallBack<OssBean> callBack) {
        HttpFactory.create().get(Urls.GETOSSTOKEN, null, callBack);
    }

    @Override
    public void getVideo(MyCallBack<GuanVideoBean> callBack, String grade, String actionType) {
        Map<String ,String> map=new HashMap<>();
        map.put("grade", grade);
        if (!StringUtils.isBlank(actionType)){
            map.put("actionType",actionType);
        }
        HttpFactory.create().get(Urls.VIDEO, map, callBack);
    }

    @Override
    public void getPaihanVideo(MyCallBack<GuanVideoBean> callBack, String pageCurrent, String pageSize, String sortType) {
        Map<String ,String> map=new HashMap<>();
        map.put("id", MyApp.instance.getId());
        map.put("pageCurrent", pageCurrent);
        map.put("pageSize",pageSize);
        map.put("sortType",sortType);
        HttpFactory.create().get(Urls.VIDEO, map, callBack);
    }

    @Override
    public void getGerenVideo(MyCallBack<GuanVideoBean> callBack) {
        Map<String ,String> map=new HashMap<>();
        map.put("iphone", MyApp.instance.getIphone());
        HttpFactory.create().get(Urls.VIDEO, map, callBack);
    }


}
