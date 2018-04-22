package com.hxbj.bijihui.constants;


public class Urls {
    //服务器地址
    public static final String BASEURL = "http://192.168.2.19:9188/";
//    public static final String BASEURL = "http://47.94.90.131:9188/";
    //登陸
    public static final String LOGIN = BASEURL+"login";
    //个人信息
    public static final String USERINFO = BASEURL+"api/userInfo";
    //首页banner
    public static final String BANNER = BASEURL+"api/contents";
    //修改用户信息
    public static final String UPDATEUSER = BASEURL+"api/updateUser";
    //官方视频
    public static final String VIDEO = BASEURL+"api/video";
    //上传个人视频
    public static final String UPDATEVIDEO = BASEURL+"api/updateVideo";
    //点赞
    public static final String UPDATETHUMB = BASEURL+"api/updateThumb";
    //会员授权
    public static final String USERAUTHINFO = BASEURL+"api/userAuthInfo";


    //上传
    public static final String GETOSSTOKEN = BASEURL+"api/getOSSToken";
    public static final String OSS = "oss-cn-beijing.aliyuncs.com";

}
