package com.hxbj.bijihui.module.quanming.geren;

import com.hxbj.bijihui.model.BojihuiModelImp;
import com.hxbj.bijihui.model.bean.GuanVideoBean;
import com.hxbj.bijihui.network.MyCallBack;

public class GerenPresenter implements GerenContract.GerenPresenter {

    private GerenContract.GerenView gerenView;
    private BojihuiModelImp bojihuiModelImp;

    public GerenPresenter(GerenContract.GerenView gerenView) {
        this.gerenView = gerenView;
        bojihuiModelImp=new BojihuiModelImp();
        gerenView.setPresenter(this);

    }

    @Override
    public void start() {
        bojihuiModelImp.getGerenVideo(new MyCallBack<GuanVideoBean>() {
            @Override
            public void onSuccess(GuanVideoBean guanVideoBean) {
                if (guanVideoBean.getCode()==2000){
                    gerenView.setResultData(guanVideoBean);
                }
            }

            @Override
            public void onFaile(String msg) {

            }
        });
    }
}
