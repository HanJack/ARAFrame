package com.cliff.araframedemo.api.entity;


import com.cliff.hsj.common.BaseEntity;

/**
 * @Description 用户实体
 * @File AuthorEntity.java
 * @Package com.martin.ionichina.entity
 * @Date 2015年6月16日下午11:20:36
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class Author extends BaseEntity{

    private String loginname;// DongHongfei,
    private String avatar_url;// https://avatars.githubusercontent.com/u/5700428?v=3&s=120

    /**
     * @return the loginname
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * @param loginname the loginname to set
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    /**
     * @return the avatar_url
     */
    public String getAvatar_url() {
        return avatar_url;
    }

    /**
     * @param avatar_url the avatar_url to set
     */
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Author [loginname=" + loginname + ", avatar_url=" + avatar_url
                + "]";
    }

}
