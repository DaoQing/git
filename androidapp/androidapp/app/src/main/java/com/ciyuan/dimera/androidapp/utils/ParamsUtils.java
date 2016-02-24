package com.ciyuan.dimera.androidapp.utils;

import java.util.HashMap;

/**
 * ClassName : ParamsUtils
 * Author   : 史翔宇
 * Time     : 2015/12/17
 * Desc     :
 */
public class ParamsUtils {

    /**
     * 获取基本参数
     *
     * @return
     */
    public static HashMap<String, String> getBaseRequestParams() {
        HashMap<String, String> map = new HashMap<String, String>();
        return map;
    }

    /**
     * 一个参数的请求
     *
     * @param url
     * @param value 参数
     * @return
     */
    public static HashMap<String, String> getRequestParams(String url, String value) {
        HashMap<String, String> map = new HashMap<String, String>();
        switch (url) {
            //注销登录
            case Constans.LOGOUT:
                //获取用户本身基本信息
            case Constans.USERINFO:
                map.put("number", value);
                break;
        }
        return map;
    }

    /**
     * 两个参数的请求
     *
     * @param url    请求地址
     * @param value1 参数 1
     * @param value2 参数 2
     * @return
     */
    public static HashMap<String, String> getRequestParams(String url, String value1, String value2) {
        HashMap<String, String> map = new HashMap<String, String>();
        switch (url) {
            //登录
            case Constans.LOGIN:
                map.put("phone", value1);
                map.put("password", value2);
                map.put("logtype", "phone");
                break;
            //注册
            case Constans.REGISTER:
                map.put("phone", value1);
                map.put("password", value2);
                map.put("regtype", "phone");
                break;
            //重置密码
            case Constans.RESET_PASSWORD:
                map.put("phone", value1);
                map.put("password", value2);
                map.put("restype", "phone");
                break;
            //编辑头像
            case Constans.EDIT_USER_AVATAR:
                map.put("number", value1);
                map.put("avatar", value2);
                break;
            //添加粉丝
            case Constans.ADD_FANS:
                //取消关注
            case Constans.CANCEL_FANS:
                map.put("fromNumber", value1);
                map.put("toNumber", value2);
                break;
            //获取粉丝
            case Constans.GET_ALL_FANS:
            //获取偶像
            case Constans.GET_TALENTS:
                map.put("number", value1);
                map.put("offset", value2);
                break;
        }
        return map;
    }

    /**
     * 三个参数的请求
     *
     * @param url
     * @param value1 参数 1
     * @param value2 参数 2
     * @param value3 参数 3
     * @return
     */
    public static HashMap<String, String> getRequestParams(String url, String value1, String value2, String value3) {
        HashMap<String, String> map = new HashMap<String, String>();
        switch (url) {
            //编辑头像
            case Constans.THIRD_LOGIN:
                map.put("thirdinfo", value1);
                map.put("nickname", value2);
                map.put("avatar", value3);
                break;

        }
        return map;
    }

    public static HashMap<String, String> getRequestParams(String url, String value1, String value2, String value3, String value4, String value5) {
        HashMap<String, String> map = new HashMap<String, String>();
        switch (url) {
            //编辑个人信息
            case Constans.THIRD_LOGIN:
                map.put("nickname", value1);
                map.put("gender", value2);
                map.put("signature", value3);
                map.put("birthday", value4);
                map.put("area", value5);
                break;
        }
        return map;
    }
}
