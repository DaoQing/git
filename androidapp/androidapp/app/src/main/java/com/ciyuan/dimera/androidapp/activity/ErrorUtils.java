package com.ciyuan.dimera.androidapp.activity;

import com.ciyuan.dimera.androidapp.http.Protocol;

/**
 * ClassName : ErrorUtils
 * Author   : 史翔宇
 * Time     : 2015/12/18
 * Desc     :
 */
public class ErrorUtils {
    public static String processError(int code){
        String errorDesc=null;
        switch (code){
            case Protocol.TOWER_CODE_STATUS_SESSION_OVERTIME:
                errorDesc="登录超时";
                break;
            case Protocol.TOWER_CODE_STATUS_LOGIN_FAIL_NOT_FOUND:
                errorDesc = "用户不存在";
                break;
            case Protocol.TOWER_CODE_STATUS_LOGIN_FAIL_INVALID_PASSWORD:
                errorDesc = "密码错误";
                break;
        }
        return errorDesc;
    }
}
