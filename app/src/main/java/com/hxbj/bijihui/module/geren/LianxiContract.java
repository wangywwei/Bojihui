package com.hxbj.bijihui.module.geren;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;
import com.hxbj.bijihui.model.bean.LiuyanBean;

public class LianxiContract  {

    interface LianxiView extends BaseView<LianxiPresenter>{
        void setResultData(LiuyanBean resultData);
    }

    interface LianxiPresenter extends BasePresenter{
        void start(String detail);
    }
}
