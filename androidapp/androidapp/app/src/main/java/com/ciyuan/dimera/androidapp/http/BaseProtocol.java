package com.ciyuan.dimera.androidapp.http;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ciyuan.dimera.androidapp.utils.Constans;
import com.ciyuan.dimera.androidapp.utils.FileUtils;
import com.ciyuan.dimera.androidapp.utils.UIUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ClassName : BaseProtocol
 * Author   : 史翔宇
 * Time     : 2015/11/26
 * Desc     :
 */
public abstract class BaseProtocol<T> {

    protected OnDataFromNetListener mOnDataFromNetListener;

    /**
     * @param <T>
     */
    public interface OnDataFromNetListener<T> {
        void setDataFromNet(T data);
    }

    public void setOnDataFromNetListener(OnDataFromNetListener listener) {
        this.mOnDataFromNetListener = listener;
    }

    /**
     * @return 接口的key
     */
    protected abstract String getInterfaceKey();

    /**
     * @return 缓存的key
     */
    protected abstract String getCacheKey();

    protected abstract T parseJson(String json);

    protected RequestQueue mQueue;

    public T getData() throws Exception {
        //到本地中去取缓存数据
        T data = getDataFromLocal();
        if (data != null) {
            System.out.println("###############################使用的本地缓存");
            return data;
        }
        //到网络中取数据

        getDatafromNet();

        return null;
    }

    /**
     * 去本地获取缓存
     *
     * @return
     * @throws Exception
     */
    protected T getDataFromLocal() throws Exception {
        System.out.println("###############################进入本地拿数据的方法");
        File file = getCacheFile();
        if (!file.exists()) {
            System.out.println("缓存文件不存在");
            return null;
        }
        //存在，拿到json字符
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String json = reader.readLine();
            return parseJson(json);
        } finally {
            reader.close();
        }
    }


    protected void getDatafromNet() throws Exception {
        String url = Constans.SERVER_URL + getInterfaceKey();
        mQueue = Volley.getQueueInstance(UIUtils.getContext());
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {


                write2Local(json);
                //System.out.println("write2Local(json)  json=" + json);

                mOnDataFromNetListener.setDataFromNet(parseJson(json));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                System.out.println("访问接口出错" );
            }
        });
        mQueue.add(request);


        //  ################### 上方代码采用Volley，封装有问题已经解决###################

    }


    private void write2Local(String json) {
        File file = getCacheFile();
        System.out.println("write2Local()    ：  缓存到本地文件");
        // 将json字符写入文件中
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            // 存储json字符
            System.out.println("write2Local()    ： 开始缓存");
            writer.write(json);
            System.out.println("write2Local(json)  json=" + json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取本地缓存文件
     *
     * @return 本地缓存文件
     */
    private File getCacheFile() {

        String dir = FileUtils.getDir("json");
        String name = getCacheKey();
        System.out.println("getCacheFile()    :获取本地缓存文件目录");
        System.out.println("文件名字" + name);
        return new File(dir, name);
    }
}
