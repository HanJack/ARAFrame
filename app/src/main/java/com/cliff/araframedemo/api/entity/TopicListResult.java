package com.cliff.araframedemo.api.entity;


import com.cliff.araframedemo.db.Topic;
import com.cliff.hsj.common.BaseEntity;

import java.util.List;


/**
 * @Description 话题的访问结果
 * @File TopicListResult.java
 * @Package com.martin.ionichina.module.topic.entity
 * @Date 2015年6月16日下午11:19:45
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class TopicListResult extends BaseEntity {

    private List<Topic> data;

    public List<Topic> getData() {
        return data;
    }

    public void setData(List<Topic> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TopicResult [data=" + data + "]";
    }

}
