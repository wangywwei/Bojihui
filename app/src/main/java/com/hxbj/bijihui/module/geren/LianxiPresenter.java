package com.hxbj.bijihui.module.geren;

import com.hxbj.bijihui.model.BojihuiModelImp;
import com.hxbj.bijihui.model.bean.LiuyanBean;
import com.hxbj.bijihui.network.MyCallBack;

public class LianxiPresenter implements LianxiContract.LianxiPresenter {
    private LianxiContract.LianxiView lianxiView;
    private BojihuiModelImp bojihuiModelImp;

    public LianxiPresenter(LianxiContract.LianxiView lianxiView) {
        this.lianxiView = lianxiView;
        bojihuiModelImp=new BojihuiModelImp();
        lianxiView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void start(String detail) {
        bojihuiModelImp.contact(new MyCallBack<LiuyanBean>() {
            @Override
            public void onSuccess(LiuyanBean s) {
                if (s.getCode()==2000){
                    lianxiView.setResultData(s);
                }
            }

            @Override
            public void onFaile(String msg) {

            }
        },detail);
    }
}
