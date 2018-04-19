package com.hxbj.bijihui.model;


import com.hxbj.bijihui.model.bean.GuanVideoBean;
import com.hxbj.bijihui.model.bean.HomeBannerBean;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.model.bean.OssBean;
import com.hxbj.bijihui.network.MyCallBack;

/**
 * 请求各个模块数据
 */

public interface BojihuiModel {
    //用户信息
    void getLogin(MyCallBack<LoginBean> callBack, String iphone);
    //首页banner
    void getBanner(MyCallBack<HomeBannerBean> callBack);
    //首页banner
    void getUpdateUser(MyCallBack<LoginBean> callBack,String nickname,String sex,String picUrl,String birthday,String keyWord);

    //首页banner
    void getOSSToken(MyCallBack<OssBean> callBack);

    //官方视频
    void getVideo(MyCallBack<GuanVideoBean> callBack, String grade, String actionType);

    //排行视频
    void getPaihanVideo(MyCallBack<GuanVideoBean> callBack, String pageCurrent, String pageSize,String sortType);

    //个人视频
    void getGerenVideo(MyCallBack<GuanVideoBean> callBack);
}
