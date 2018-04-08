package com.hxbj.bijihui.network;


public interface MyCallBack<T> {
    void onSuccess(T t);
    void onFaile(String msg);
}
