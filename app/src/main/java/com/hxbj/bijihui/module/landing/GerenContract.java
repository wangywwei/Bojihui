package com.hxbj.bijihui.module.landing;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.model.bean.OssBean;

public class GerenContract {

    interface GerenView extends BaseView<GerenPresenter>{
        void setResult(LoginBean loginBean);
        void setOSStoken(OssBean osStoken);
    }

    interface GerenPresenter extends BasePresenter{
        void start(String nickname, String sex, String picUrl, String birthday, String keyWord);
    }

}
