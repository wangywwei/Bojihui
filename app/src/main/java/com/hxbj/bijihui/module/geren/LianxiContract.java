package com.hxbj.bijihui.module.geren;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;

public class LianxiContract  {

    interface LianxiView extends BaseView<LianxiPresenter>{
        void setResultData(String resultData);
    }

    interface LianxiPresenter extends BasePresenter{

    }
}
