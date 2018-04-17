package com.hxbj.bijihui.module.landing;

import com.google.gson.Gson;
import com.hxbj.bijihui.constants.Urls;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.BojihuiModelImp;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.network.MyCallBack;
import com.hxbj.bijihui.utils.LogUtils;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LandingPresenter implements LandingContract.LandingPresenter {
    private LandingContract.LancingView lancingView;
    private BojihuiModelImp bojihuiModelImp;

    public LandingPresenter(LandingContract.LancingView lancingView) {
        this.lancingView = lancingView;
        bojihuiModelImp=new BojihuiModelImp();
        lancingView.setPresenter(this);
    }

    @Override
    public void start() {
        OkHttpClient mOkHttpClient=new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();

        Map<String, String> params = new HashMap<String, String>();
        params.put("iphone", "17600996535");
        params.put("password", "123123");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(params);
        builder.add("",jsonStr);

        RequestBody body=  RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
        Request request = new Request.Builder().url(Urls.LOGIN).post(body).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.headers().get("Authorization");
                lancingView.setResultData(jsonData);
            }
        });

    }
}
