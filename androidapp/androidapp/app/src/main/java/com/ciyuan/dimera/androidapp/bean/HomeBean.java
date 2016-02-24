package com.ciyuan.dimera.androidapp.bean;

import java.util.List;

/**
 * ClassName : HomeBean
 * Author   : 史翔宇
 * Time     : 2015/11/25
 * Desc     :首页的实体类
 */
public class HomeBean {
    public List<String> carousel_pic;
    public List<HotPictureBean> hot_picture;
    public List<NewModelBean> newly_modelgroup;
//    public List<TalentBean> talents;
//
//    public List<TalentBean> getTalents() {
//        return talents;
//    }
//
//    public void setTalents(List<TalentBean> talents) {
//        this.talents = talents;
//    }

    public List<HotPictureBean> getHot_picture() {
        return hot_picture;
    }

    public void setHot_picture(List<HotPictureBean> hot_picture) {
        this.hot_picture = hot_picture;
    }

    public List<String> getCarousel_pic() {
        return carousel_pic;
    }

    public void setCarousel_pic(List<String> carousel_pic) {
        this.carousel_pic = carousel_pic;
    }

    public List<NewModelBean> getNewly_modelgroup() {
        return newly_modelgroup;
    }

    public void setNewly_modelgroup(List<NewModelBean> newly_modelgroup) {
        this.newly_modelgroup = newly_modelgroup;
    }

    @Override
    public String toString() {
        return "HomeHean [carousel_pic=" + carousel_pic + ",hot_picture=" + hot_picture + ",newly_modelgroup=" + newly_modelgroup + "]";
    }
}
