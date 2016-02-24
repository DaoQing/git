package com.ciyuan.dimera.androidapp.bean;

/**
 * ClassName : HotPictureBean
 * Author   : 史翔宇
 * Time     : 2015/11/25
 * Desc     :热门图片的实体类
 */
public class HotPictureBean {

    private int dynamic_id;   //图片id
    private String dynamic_picurl;   //图片链接
    private String publish_user;

    public int getDynamic_id() {
        return dynamic_id;
    }

    public void setDynamic_id(int dynamic_id) {
        this.dynamic_id = dynamic_id;
    }

    public String getDynamic_picurl() {
        return dynamic_picurl;
    }

    public void setDynamic_picurl(String dynamic_picurl) {
        this.dynamic_picurl = dynamic_picurl;
    }

    public String getPublish_user() {
        return publish_user;
    }

    public void setPublish_user(String publish_user) {
        this.publish_user = publish_user;
    }

    @Override
    public String toString() {
        return "HotPictureBean [dynamic_id="+dynamic_id+",dynamic_url="+dynamic_picurl+ "]";
    }
}
