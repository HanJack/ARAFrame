package com.cliff.araframedemo.api.entity;

import com.cliff.hsj.common.BaseEntity;

import java.util.List;

/**
 * @Description 话题的回复实体
 * @File Replies.java
 * @Package com.martin.ionichina.entity
 * @Date 2015年6月17日下午1:40:18
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class Replies extends BaseEntity {
    private String id;// : 555f3f4fef16811c3ea0a79b,
    private Author author;
    private String content;// :content
    private List<String> ups;// : [],
    private String create_at;// : 2015-05-22T14:38:07.010Z

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the ups
     */
    public List<String> getUps() {
        return ups;
    }

    /**
     * @param ups the ups to set
     */
    public void setUps(List<String> ups) {
        this.ups = ups;
    }

    /**
     * @return the create_at
     */
    public String getCreate_at() {
        return create_at;
    }

    /**
     * @param create_at the create_at to set
     */
    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Replies [id=" + id + ", author=" + author + ", content="
                + content + ", ups=" + ups + ", create_at=" + create_at + "]";
    }

}
