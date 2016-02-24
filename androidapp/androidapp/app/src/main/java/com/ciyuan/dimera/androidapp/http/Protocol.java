package com.ciyuan.dimera.androidapp.http;

/**
 * ClassName : Protocol
 * Author   : 史翔宇
 * Time     : 2015/12/16
 * Desc     :
 */
public class Protocol {

    // 请求成功状态码
    public final static int TOWER_CODE_STATUS_SUCCESS = 1;
    // 通用的或未知的错误
    public final static int TOWER_CODE_STATUS_UNKNOWN_ERROR = 0x8000;
    // session超时
    public final static int TOWER_CODE_STATUS_SESSION_OVERTIME = 0x8001;
    // 未找到(期望存在的)记录
    public final static int TOWER_CODE_STATUS_DATA_NOT_FOUND = 0x8002;
    // 无数据
    public final static int TOWER_CODE_STATUS_NOTHING = 0x8003;
    // 参数错误
    public final static int TOWER_CODE_STATUS_PARAMETER_ERROR = 0x8004;
    // 数据库操作时发生未知错误
    public final static int TOWER_CMD_REQUEST_DATABASE_UNKNOWN_ERROR = 0x81001;
    // 数据库插入数据时发生错误
    public final static int TOWER_CMD_REQUEST_DATABASE_INSERT_ERROR = 0x8101;
    // 用户不存在
    public final static int TOWER_CODE_STATUS_LOGIN_FAIL_NOT_FOUND=0x8005;
    // 密码错误
    public final static int TOWER_CODE_STATUS_LOGIN_FAIL_INVALID_PASSWORD=0x8006;
}