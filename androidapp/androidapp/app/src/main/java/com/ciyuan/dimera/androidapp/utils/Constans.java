package com.ciyuan.dimera.androidapp.utils;

/**
 * ClassName : Constans
 * Author   : 史翔宇
 * Time     : 2015/11/25
 * Desc     :
 */
public interface Constans {

    /**
     * 网络接口部分
     */
    String SERVER_URL = "http://www.dimeratech.com/";
//String SERVER_URL = "http://192.168.1.213/LR/index.php/";

    String HOME_INIT_URL = SERVER_URL + "Home/Homepage/init";

    String IMAGE_BASE_URL = SERVER_URL + "Public/Uploads/pictures/";

    String DYNAMIC_PIC_BASE_URL = SERVER_URL + "Public/Uploads/dynamic_pic/";

    /**
     * 服务器接口
     */
    String HOST = "http://www.dimeratech.com/";

    /**
     * 用户模块基本接口
     */
    String USER_BASE = HOST + "Home/User/";

    // 用户登录
    String LOGIN = USER_BASE + "login";
    //用户注册
    String REGISTER = USER_BASE + "register";
    //用户密码重置
    String RESET_PASSWORD = USER_BASE + "reset";
    //第三方登录
    String THIRD_LOGIN = USER_BASE + "thirdLogin";
    //用户注销
    String LOGOUT = USER_BASE + "logout";


    /**
     * 用户信息模块基本接口
     */
    String USERINFO_BASE = HOST + "Home/Userinfo/";

     //获取用户信息
    String USERINFO = USERINFO_BASE + "getUserinfo";
     //编辑个人头像
    String EDIT_USER_AVATAR = USERINFO_BASE + "eidtAvatar";
     // 编辑个人信息
    String EDIT_USER_INFO = USERINFO_BASE + "editUserinfo";
     //获取达人
    String GET_TALENTS = USERINFO_BASE + "getTalents";


    /**
     * 粉丝模块基本接口
     */
    String FANS_BASE = HOST + "Home/Fans/";

     // 添加关注
    String ADD_FANS = FANS_BASE + "fan";
     //编辑个人头像
    String CANCEL_FANS = FANS_BASE + "cancelFan";
     //获取所有粉丝
    String GET_ALL_FANS = FANS_BASE + "getFans";
     //获取偶像
    String GET_IDOLS = FANS_BASE + "getIdols";


    /**
     * 动态模块基本接口
     */
    String DYNAMIC_BASE = HOST + "Home/Dynamic/";

     //用户发布一条动态
    String PUBLISH_DYNAMIC = DYNAMIC_BASE + "publish";
     //删除一条动态
    String DELETE_DYNAMIC = DYNAMIC_BASE + "delete";
     //获取所有动态，可用于首页加载热门图片
    String GET_ALL_DYNAMIC = DYNAMIC_BASE + "getByTime";
     //获获取某一个用户的动态
    String GET_DYNAMIC_BYNUMBER = DYNAMIC_BASE + "getByAuthor";
     //圈子初始化
    String INIT_MOMENTS = DYNAMIC_BASE + "initMoments";
}

