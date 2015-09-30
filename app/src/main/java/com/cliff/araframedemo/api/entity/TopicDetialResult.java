package com.cliff.araframedemo.api.entity;


import com.cliff.araframedemo.db.Topic;
import com.cliff.hsj.common.BaseEntity;

/**
 * @Description 话题的详情的结果 数据对象
 * @File TopicDetialResult.java
 * @Package com.martin.ionichina.module.topic.entity
 * @Date 2015年6月17日下午6:14:07
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class TopicDetialResult extends BaseEntity {

    private Topic data;

    /**
     * @return the data
     */
    public Topic getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Topic data) {
        this.data = data;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TopicDetialResult [data=" + data + "]";
    }

}
