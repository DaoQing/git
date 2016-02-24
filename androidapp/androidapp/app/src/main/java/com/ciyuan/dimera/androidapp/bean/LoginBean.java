package com.ciyuan.dimera.androidapp.bean;

public class LoginBean {
	private String uid; // 用户uid
	private String key; // 用户keyֵ

	public String getuid() {
		return uid;
	}

	public void setuid(String uid) {
		this.uid = uid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "LoginBean [uid=" + uid + ", key=" + key + "]";
	}

}
