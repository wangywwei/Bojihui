package com.hxbj.bijihui.model;


import com.hxbj.bijihui.constants.Urls;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.network.HttpFactory;
import com.hxbj.bijihui.network.MyCallBack;

import java.util.HashMap;
import java.util.Map;

public class BojihuiModelImp implements BojihuiModel {

//    @Override
//    public void getHomeData(MyCallBack<HomeDataBean> callBack) {
//        HttpFactory.create().get(Urls.HOMEURLALL, null, callBack);
//    }
//
//    @Override
//    public void getPandaBroadData(String path, String primaryId, String serviceId, MyCallBack<PandaBroadBean> callBack) {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("path", path);
//        params.put("primaryId", primaryId);
//        params.put("serviceId", serviceId);
//        HttpFactory.create().get(Urls.PANDAREPORT, params, callBack);
//    }


    @Override
    public void getLogin(MyCallBack<LoginBean> callBack,String iphone) {

    }
}
