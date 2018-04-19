package com.hxbj.bijihui.network;

import com.google.gson.Gson;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.utils.LogUtils;
import com.hxbj.bijihui.utils.SPUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class HttpUtils implements IHttp {
    private static HttpUtils mHttpUtils;
    private OkHttpClient mOkHttpClient;

    private HttpUtils() {
        mOkHttpClient = new OkHttpClient();
    }

    public synchronized static HttpUtils getInstance() {
        if (mHttpUtils == null)
            mHttpUtils = new HttpUtils();
        return mHttpUtils;
    }

    // http://www.qq.com?name=xxx&pwd=xxx;
    @Override
    public <T> void get(String url, Map<String, String> params, final MyCallBack<T> callBack) {
        if (params!=null){
            StringBuffer sb = new StringBuffer(url);
            sb.append("?");
            Set<String> set = params.keySet();
            for (String key : set) {
                String value = params.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            url = sb.deleteCharAt(sb.length() - 1).toString();
        }

        Request request = new Request.Builder().url(url)
                .addHeader("Authorization",MyApp.instance.getAuthorization()).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                MyApp.mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFaile(e.getMessage());
                    }
                });
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {

                    final String jsonData = response.body().string();

                    MyApp.mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                            callBack.onSuccess(getGeneric(jsonData, callBack));
                            }catch (Exception i){

                            }
                        }
                    });



            }
        });


    }

    /**
     * Gson解析 获取实体对象
     * 通过反射机制获取当前接口实现类的类型
     * 根据类获取当前类对应的对象
     *
     * @param <T>
     * @param jsonData
     * @param callback
     * @return
     */
    private <T> T getGeneric(String jsonData, MyCallBack<T> callback) {
        Type[] types = callback.getClass().getGenericInterfaces();
        Type[] parameterTypes = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = parameterTypes[0];
        Gson gson = new Gson();
        T t = gson.fromJson(jsonData, type);
        return t;
    }


    @Override
    public <T> void post(String url, Map<String, String> params, final MyCallBack<T> callBack) {
        FormBody.Builder builder = new FormBody.Builder();
//        Set<String> set = params.keySet();
//        for (String key : set) {
//            builder.add(key, params.get(key));
//        }
//        FormBody body = builder.build();

            Gson gson = new Gson();
            String jsonStr = gson.toJson(params);
            builder.add("",jsonStr);
            RequestBody body=  RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);


        Request request = new Request.Builder().url(url)
                .addHeader("Authorization",MyApp.instance.getAuthorization())
                .post(body).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                MyApp.mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFaile(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    final String jsonData = response.body().string();
                    MyApp.mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callBack.onSuccess(getGeneric(jsonData, callBack));
                            }catch (Exception i){

                            }

                        }
                    });
                }catch (Exception io){

                }


            }
        });

    }
}
