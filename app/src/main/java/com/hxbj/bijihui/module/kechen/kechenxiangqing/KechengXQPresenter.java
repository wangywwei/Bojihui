package com.hxbj.bijihui.module.kechen.kechenxiangqing;

import com.hxbj.bijihui.model.BojihuiModelImp;
import com.hxbj.bijihui.model.bean.GuanVideoBean;
import com.hxbj.bijihui.model.bean.OssBean;
import com.hxbj.bijihui.network.MyCallBack;

public class KechengXQPresenter implements KechengXQContract.KechengXQPresenter {
    private KechengXQContract.KechengXQView kechengXQView;
    private BojihuiModelImp modelImp;

    public KechengXQPresenter(KechengXQContract.KechengXQView kechengXQView) {
        this.kechengXQView = kechengXQView;
        modelImp=new BojihuiModelImp();
        kechengXQView.setPresenter(this);

    }

    @Override
    public void start() {

    }

    @Override
    public void start(String grade, String actionType) {
        modelImp.getVideo(new MyCallBack<GuanVideoBean>() {
            @Override
            public void onSuccess(GuanVideoBean guanVideoBean) {
                if (guanVideoBean.getCode()==2000){
                    kechengXQView.setResultData(guanVideoBean);
                }

            }

            @Override
            public void onFaile(String msg) {

            }
        },grade,actionType);

    }
}
