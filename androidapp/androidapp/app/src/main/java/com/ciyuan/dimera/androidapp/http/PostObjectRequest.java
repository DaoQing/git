package com.ciyuan.dimera.androidapp.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * ClassName : PostObjectRequest
 * Author   : 史翔宇
 * Time     : 2015/12/14
 * Desc     :
 */
public class PostObjectRequest extends Request<JSONObject> {


    /**
     * 正确数据的时候回掉用
     */
    private Response.Listener<JSONObject> mListener;
    private Map<String, String> mMap;

    public PostObjectRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Map<String, String> map) {
        super(Method.POST, url, errorListener);

        mListener = listener;
        mMap = map;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        //返回post携带的参数.
        return mMap;
    }


    /**
     * 这里开始解析数据
     *
     * @param response Response from the network
     * @return
     */
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

        try {
            /**
             * 得到返回的数据
             */
            Map<String, String> responseHeaders = response.headers;

            String jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            /**
             * 转化成对象
             */
            return Response.success(new JSONObject(jsonStr), HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));

        }
    }

    /**
     * 回调正确的数据
     * @param response The parsed response returned by
     */
    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }

}
