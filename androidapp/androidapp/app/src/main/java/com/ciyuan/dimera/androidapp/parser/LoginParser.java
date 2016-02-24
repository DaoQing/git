package com.ciyuan.dimera.androidapp.parser;

import com.ciyuan.dimera.androidapp.bean.ErrorBean;
import com.ciyuan.dimera.androidapp.bean.LoginBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * ClassName : LoginParser
 * Author   : 史翔宇
 * Time     : 2015/12/25
 * Desc     :登录的解析
 */
public class LoginParser implements BaseParser {

	@Override
	public Object parseToObject(String response) {

		LoginBean bean = null;
		try {

			JSONObject json = new JSONObject(response);
			int status = json.optInt("status");
			if (status == 1) {//=1，表示解析成功
				bean = new LoginBean();
				Gson gson=new Gson();
				bean=gson.fromJson(json.getJSONObject("data").toString(),LoginBean.class);

			} else {
				ErrorBean error = new ErrorBean();
				error.status = status;
				if (json.has("message")) {
					error.message = json.getString("message");
				}
				return error;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
