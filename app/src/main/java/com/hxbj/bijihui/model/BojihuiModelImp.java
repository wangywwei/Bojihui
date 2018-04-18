package com.hxbj.bijihui.model;


import com.hxbj.bijihui.constants.Urls;
import com.hxbj.bijihui.global.MyApp;
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


}
