package com.hxbj.bijihui.module.quanming.paihang;

import com.hxbj.bijihui.model.BojihuiModelImp;
import com.hxbj.bijihui.model.bean.GuanVideoBean;
import com.hxbj.bijihui.network.MyCallBack;

public class PaihangPresenter implements PaihangContract.PaihangPresenter {
    private PaihangContract.PaihangView paihangView;
    private BojihuiModelImp bojihuiModelImp;

    public PaihangPresenter(PaihangContract.PaihangView paihangView) {
        this.paihangView = paihangView;
        bojihuiModelImp=new BojihuiModelImp();
        paihangView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void start(String pageCurrent, String pageSize, String sortType) {
        bojihuiModelImp.getPaihanVideo(new MyCallBack<GuanVideoBean>() {
            @Override
            public void onSuccess(GuanVideoBean guanVideoBean) {
                if (guanVideoBean.getCode()==2000){
                    paihangView.setResultData(guanVideoBean);
                }
            }

            @Override
            public void onFaile(String msg) {

            }
        },pageCurrent,pageSize,sortType);
    }
}
