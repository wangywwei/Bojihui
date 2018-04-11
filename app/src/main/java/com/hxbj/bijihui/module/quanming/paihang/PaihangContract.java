package com.hxbj.bijihui.module.quanming.paihang;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;

public class PaihangContract {

    interface PaihangView extends BaseView<PaihangPresenter>{
        void setResultData(String resultData);
    }


    interface PaihangPresenter extends BasePresenter{

    }
}
