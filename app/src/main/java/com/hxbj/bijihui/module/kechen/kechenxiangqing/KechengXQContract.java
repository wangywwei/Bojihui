package com.hxbj.bijihui.module.kechen.kechenxiangqing;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;

public class KechengXQContract {

    interface KechengXQView extends BaseView<KechengXQPresenter>{
        void setResultData(String resultData);

    }

    interface KechengXQPresenter extends BasePresenter{

    }
}
