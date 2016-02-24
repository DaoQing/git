package com.ciyuan.dimera.androidapp.bean;

/**
 * ClassName : NewModelBean
 * Author   : 史翔宇
 * Time     : 2015/11/25
 * Desc     :最新模型的实体类
 */
public class NewModelBean {
    private int modelgroup_id;       //模型id
    private String modelgroup_picurl;    //模型链接

    public int getModelgroup_id() {
        return modelgroup_id;
    }

    public void setModelgroup_id(int modelgroup_id) {
        this.modelgroup_id = modelgroup_id;
    }

    public String getModelgroup_picurl() {
        return modelgroup_picurl;
    }

    public void setModelgroup_picurl(String modelgroup_picurl) {
        this.modelgroup_picurl = modelgroup_picurl;
    }

    @Override
    public String toString() {
        return "NewModelBean [modelgroup_id="+modelgroup_id+",modelgroup_picurl="+modelgroup_picurl+" ]";
    }
}
