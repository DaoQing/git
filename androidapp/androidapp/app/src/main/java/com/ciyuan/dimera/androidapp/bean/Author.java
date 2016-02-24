package com.ciyuan.dimera.androidapp.bean;

/**
 * ClassName : Author
 * Author   : 史翔宇
 * Time     : 2015/12/15
 * Desc     :
 */
public class Author {

    private long number; //作者number
    private String nickname;// 作者昵称
    private String avatar;//作者头像地址

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
