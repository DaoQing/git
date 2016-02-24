package com.ciyuan.dimera.androidapp.bean;

/**
 * ClassName : CircleBean
 * Author   : 史翔宇
 * Time     : 2015/12/15
 * Desc     :
 */
public class CircleBean {

    private int dynamic; //动态id
    private String   picurl ;//动态图片地址
    private String   description ;//动态描述
    private String    pubtime ;//发布时间
    private int   praisetimes; //动态赞数
    private int   sharetimes;//动态分享数
    private Author author;

    public int getDynamic() {
        return dynamic;
    }

    public void setDynamic(int dynamic) {
        this.dynamic = dynamic;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public int getPraisetimes() {
        return praisetimes;
    }

    public void setPraisetimes(int praisetimes) {
        this.praisetimes = praisetimes;
    }

    public int getSharetimes() {
        return sharetimes;
    }

    public void setSharetimes(int sharetimes) {
        this.sharetimes = sharetimes;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "CircleBean [dynamic=" + dynamic + ",picurl=" + picurl + "," +
                "description=" + description + ",pubtime=" + pubtime + ",praisetimes=" +
                praisetimes + ",sharetimes=" + sharetimes + ",author=" + author +"]";
    }
}
