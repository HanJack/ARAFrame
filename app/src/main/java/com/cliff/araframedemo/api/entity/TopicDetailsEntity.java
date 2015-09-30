package com.cliff.araframedemo.api.entity;

import com.cliff.hsj.common.BaseEntity;

/**
 * @Description 话题的详情实体，这里实现使用的是ListView,所以这个实体是拼接出来的，根据type进行标识<br>
 * 包含话题标题，话题图片，话题内容，
 * @File TopicDetailsEntity.java
 * @Package com.martin.ionichinabystudio.model.entity
 * @Date 2015/7/1113:52
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class TopicDetailsEntity extends BaseEntity {

    //话题标题
    private String topicTitle;

    //话题的发表日期
    private String topicCreateDate;

    //话题的作者
    private Author topicAuthor;

    //话题的图片
    private String topicImgUrl;

    //话题的内容
    private String topicContent;

    //话题的内容中的超链接
    private String topicContentLink;

    //话题的内容类型
    private int topicType;

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicCreateDate() {
        return topicCreateDate;
    }

    public void setTopicCreateDate(String topicCreateDate) {
        this.topicCreateDate = topicCreateDate;
    }

    public String getTopicImgUrl() {
        return topicImgUrl;
    }

    public void setTopicImgUrl(String topicImgUrl) {
        this.topicImgUrl = topicImgUrl;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getTopicContentLink() {
        return topicContentLink;
    }

    public void setTopicContentLink(String topicContentLink) {
        this.topicContentLink = topicContentLink;
    }

    public int getTopicType() {
        return topicType;
    }

    public void setTopicType(int topicType) {
        this.topicType = topicType;
    }

    public Author getTopicAuthor() {
        return topicAuthor;
    }

    public void setTopicAuthor(Author topicAuthor) {
        this.topicAuthor = topicAuthor;
    }

    @Override
    public String toString() {
        return "TopicDetailsEntity{" +
                "topicTitle='" + topicTitle + '\'' +
                ", topicCreateDate='" + topicCreateDate + '\'' +
                ", topicAuthor=" + topicAuthor +
                ", topicImgUrl='" + topicImgUrl + '\'' +
                ", topicContent='" + topicContent + '\'' +
                ", topicContentLink='" + topicContentLink + '\'' +
                ", topicType=" + topicType +
                '}';
    }
}
