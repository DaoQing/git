package com.ciyuan.dimera.androidapp.http;

import com.ciyuan.dimera.androidapp.bean.HomeBean;
import com.google.gson.Gson;

/**
 * ClassName : HomeProtocol
 * Author   : 史翔宇
 * Time     : 2015/11/26
 * Desc     :
 */
public class HomeProtocol extends BaseProtocol<HomeBean> {
    @Override
    protected String getInterfaceKey() {
        return "Home/Homepage/init";
    }

    @Override
    protected String getCacheKey() {
        return "home";
    }

    @Override
    protected HomeBean parseJson(String json) {
        HomeBean bean=new Gson().fromJson(json, HomeBean.class);
        System.out.println("wwwwwwwwwwwwwwwwwwww###########  HomeBean=" + bean);
        return bean;
    }
}
