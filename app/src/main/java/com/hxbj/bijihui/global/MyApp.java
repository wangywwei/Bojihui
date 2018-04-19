package com.hxbj.bijihui.global;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;

import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.utils.SPUtils;
import com.tencent.rtmp.TXLiveBase;


public class MyApp extends Application {
    public static BaseActivity mContext = null;

    public static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        TXLiveBase.setConsoleEnabled(true);
    }


    public static MyApp getApplication() {
        return instance;
    }


    private String authorization;//token
    private String iphone;//手机号——唯一
    private String keyWord;//会员等级
    private String nickname;//昵称
    private String sex;//性别
    private String picUrl;//头像
    private String birthday;//生日
    private String soundUrl;
    private String type;
    private String level;
    private String id;

    /*
    * 保存用户信息
    * */
    public void saveLogin(final LoginBean.DataBean user , Activity activity) {
        if(user == null){
            return ;
        }
        this.iphone=user.getIphone();
        this.keyWord=user.getKeyWord();
        this.nickname=user.getNickname();
        this.sex=user.getSex();
        this.picUrl=user.getPicUrl();
        this.birthday=user.getBirthday();
        this.soundUrl=user.getSoundUrl();
        this.type=user.getType();
        this.level=user.getLevel();
        this.id=user.getId();

        SPUtils.put(activity, "id", id);
        SPUtils.put(activity, "type", type);
        SPUtils.put(activity, "iphone", iphone);
        SPUtils.put(activity, "keyWord", keyWord);
        SPUtils.put(activity, "nickname", nickname);
        SPUtils.put(activity, "sex", sex);
        SPUtils.put(activity, "picUrl", picUrl);
        SPUtils.put(activity, "birthday", birthday);
        SPUtils.put(activity, "soundUrl", soundUrl);
        SPUtils.put(activity, "level", level);
    }

    public String getId() {
        if (!TextUtils.isEmpty(id)) {
            return id;
        } else {
            return (String) SPUtils.get(this, "id", "");
        }
    }

    public void setId(String id) {
        if (!TextUtils.isEmpty(id)) {
            this.id = id;
            SPUtils.put(this, "id", id);
        }
    }

    public String getLevel() {
        if (!TextUtils.isEmpty(level)) {
            return level;
        } else {
            return (String) SPUtils.get(this, "level", "");
        }
    }

    public void setLevel(String level) {
        if (!TextUtils.isEmpty(level)) {
            this.level = level;
            SPUtils.put(this, "level", level);
        }
    }

    public String getType() {
        if (!TextUtils.isEmpty(type)) {
            return type;
        } else {
            return (String) SPUtils.get(this, "type", "");
        }
    }

    public void setType(String type) {
        if (!TextUtils.isEmpty(type)) {
            this.type = type;
            SPUtils.put(this, "type", type);
        }
    }
    public String getSoundUrl() {
        if (!TextUtils.isEmpty(soundUrl)) {
            return soundUrl;
        } else {
            return (String) SPUtils.get(this, "soundUrl", "");
        }
    }

    public void setSoundUrl(String soundUrl) {
        if (!TextUtils.isEmpty(soundUrl)) {
            this.soundUrl = soundUrl;
            SPUtils.put(this, "soundUrl", soundUrl);
        }
    }

    public String getKeyWord() {
        if (!TextUtils.isEmpty(keyWord)) {
            return keyWord;
        } else {
            return (String) SPUtils.get(this, "keyWord", "");
        }
    }

    public void setKeyWord(String keyWord) {
        if (!TextUtils.isEmpty(keyWord)) {
            this.keyWord = keyWord;
            SPUtils.put(this, "keyWord", keyWord);
        }
    }

    public String getNickname() {
        if (!TextUtils.isEmpty(nickname)) {
            return nickname;
        } else {
            return (String) SPUtils.get(this, "nickname", "");
        }
    }

    public void setNickname(String nickname) {
        if (!TextUtils.isEmpty(nickname)) {
            this.nickname = nickname;
            SPUtils.put(this, "nickname", nickname);
        }
    }

    public String getSex() {
        if (!TextUtils.isEmpty(sex)) {
            return sex;
        } else {
            return (String) SPUtils.get(this, "sex", "");
        }
    }

    public void setSex(String sex) {
        if (!TextUtils.isEmpty(sex)) {
            this.sex = sex;
            SPUtils.put(this, "sex", sex);
        }
    }

    public String getPicUrl() {
        if (!TextUtils.isEmpty(picUrl)) {
            return picUrl;
        } else {
            return (String) SPUtils.get(this, "picUrl", "");
        }
    }

    public void setPicUrl(String picUrl) {
        if (!TextUtils.isEmpty(picUrl)) {
            this.picUrl = picUrl;
            SPUtils.put(this, "picUrl", picUrl);
        }
    }

    public String getBirthday() {
        if (!TextUtils.isEmpty(birthday)) {
            return birthday;
        } else {
            return (String) SPUtils.get(this, "birthday", "");
        }
    }

    public void setBirthday(String birthday) {
        if (!TextUtils.isEmpty(birthday)) {
            this.birthday = birthday;
            SPUtils.put(this, "birthday", birthday);
        }
    }

    public String getIphone() {
        if (!TextUtils.isEmpty(iphone)) {
            return iphone;
        } else {
            return (String) SPUtils.get(this, "iphone", "");
        }
    }

    public void setIphone(String iphone) {
        if (!TextUtils.isEmpty(iphone)) {
            this.iphone = iphone;
            SPUtils.put(this, "iphone", iphone);
        }
    }

    public String getAuthorization() {
        if (!TextUtils.isEmpty(authorization)) {
            return authorization;
        } else {
            return (String) SPUtils.get(this, "authorization", "");
        }
    }

    public void setAuthorization(String authorization) {
        if (!TextUtils.isEmpty(authorization)) {
            this.authorization = authorization;
            SPUtils.put(this, "authorization", authorization);
        }

    }
}
