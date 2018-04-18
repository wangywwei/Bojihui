package com.hxbj.bijihui.module.landing;

import com.hxbj.bijihui.model.BojihuiModelImp;
import com.hxbj.bijihui.model.bean.HomeBannerBean;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.model.bean.OssBean;
import com.hxbj.bijihui.network.MyCallBack;

public class GerenPresenter implements GerenContract.GerenPresenter{
    private GerenContract.GerenView gerenView;
    private BojihuiModelImp bojihuiModelImp;

    public GerenPresenter(GerenContract.GerenView gerenView) {
        this.gerenView = gerenView;
        bojihuiModelImp=new BojihuiModelImp();
        gerenView.setPresenter(this);
    }

    @Override
    public void start(String nickname, String sex, String picUrl, String birthday, String keyWord) {
        bojihuiModelImp.getUpdateUser(new MyCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                gerenView.setResult(loginBean);
            }

            @Override
            public void onFaile(String msg) {

            }
        },nickname,sex,picUrl,birthday,keyWord);
    }

    @Override
    public void start() {
        bojihuiModelImp.getOSSToken(new MyCallBack<OssBean>() {
            @Override
            public void onSuccess(OssBean osStoken) {
                gerenView.setOSStoken(osStoken);
            }

            @Override
            public void onFaile(String msg) {

            }
        });


    }
}
