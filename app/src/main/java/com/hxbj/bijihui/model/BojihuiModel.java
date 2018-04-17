package com.hxbj.bijihui.model;


import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.network.MyCallBack;

/**
 * 请求各个模块数据
 */

public interface BojihuiModel {

//    /**
//     * 首页数据
//     * @param callBack
//     */
//    void getHomeData(MyCallBack<HomeDataBean> callBack);


    void getLogin(MyCallBack<LoginBean> callBack, String iphone);

}
