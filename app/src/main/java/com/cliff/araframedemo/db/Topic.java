package com.cliff.araframedemo.db;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.cliff.araframedemo.api.entity.Author;
import com.cliff.araframedemo.api.entity.Replies;
import com.cliff.hsj.db.DBModel;

import java.io.Serializable;
import java.util.List;


/**
 * @Description 主题的数据实体
 * @File Topic.java
 * @Package com.martin.ionichinabystudio.model.entity
 * @Date 2015年6月16日下午11:20:24
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
@Table(name = DBContracts.TopicEntry.TABLE_NAME)
public class Topic extends DBModel<Topic> implements Serializable {
    @Column(name = DBContracts.TopicEntry.COLUMN_NAME_ID)
    private String id; // : 555f205aef16811c3ea0a79a,
    @Column(name = DBContracts.TopicEntry.COLUMN_NAME_AID)
    private String author_id;// : 54e175958d90a57f050e1625,
    @Column(name = DBContracts.TopicEntry.COLUMN_NAME_TAB)
    private String tab;// : share,
    @Column(name = DBContracts.TopicEntry.COLUMN_NAME_CONTENT)
    private String content;// content,
    @Column(name = DBContracts.TopicEntry.COLUMN_NAME_TITLE)
    private String title;//
    @Column(name = DBContracts.TopicEntry.COLUMN_NAME_LRA)
    private String last_reply_at;// : 2015-06-08T12:17:49
    @Column(name = DBContracts.TopicEntry.COLUMN_NAME_GOOD)
    private Boolean good;// : true,
    @Column(name = DBContracts.TopicEntry.COLUMN_NAME_TOP)
    private Boolean top;// : true,
    @Column(name = DBContracts.TopicEntry.COLUMN_NAME_TC)
    private String reply_count;// : 24,
    @Column(name = DBContracts.TopicEntry.COLUMN_NAME_VC)
    private String visit_count;// : 1421,
    @Column(name = DBContracts.TopicEntry.COLUMN_NAME_CA)
    private String create_at;// : 2015-05-22T12:26:02.597Z,
    private Author author;// :
    private List<Replies> replies;// 回复

    /**
     * 必须实现，初始化数据库参数
     */
    public Topic(){
        super();
    }

    public Topic(String id, String author_id, String tab, String content,
                 String title, String last_reply_at, Boolean good, Boolean top,
                 String reply_count, String visit_count, String create_at) {
        this.id = id;
        this.author_id = author_id;
        this.tab = tab;
        this.content = content;
        this.title = title;
        this.last_reply_at = last_reply_at;
        this.good = good;
        this.top = top;
        this.reply_count = reply_count;
        this.visit_count = visit_count;
        this.create_at = create_at;
    }

    /**
     * @return the id
     */
    public String getTpId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the author_id
     */
    public String getAuthor_id() {
        return author_id;
    }

    /**
     * @param author_id the author_id to set
     */
    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    /**
     * @return the tab
     */
    public String getTab() {
        return tab;
    }

    /**
     * @param tab the tab to set
     */
    public void setTab(String tab) {
        this.tab = tab;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the last_reply_at
     */
    public String getLast_reply_at() {
        return last_reply_at;
    }

    /**
     * @param last_reply_at the last_reply_at to set
     */
    public void setLast_reply_at(String last_reply_at) {
        this.last_reply_at = last_reply_at;
    }

    /**
     * @return the good
     */
    public Boolean getGood() {
        return good;
    }

    /**
     * @param good the good to set
     */
    public void setGood(Boolean good) {
        this.good = good;
    }

    /**
     * @return the top
     */
    public Boolean getTop() {
        return top;
    }

    /**
     * @param top the top to set
     */
    public void setTop(Boolean top) {
        this.top = top;
    }

    /**
     * @return the reply_count
     */
    public String getReply_count() {
        return reply_count;
    }

    /**
     * @param reply_count the reply_count to set
     */
    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    /**
     * @return the visit_count
     */
    public String getVisit_count() {
        return visit_count;
    }

    /**
     * @param visit_count the visit_count to set
     */
    public void setVisit_count(String visit_count) {
        this.visit_count = visit_count;
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
     * @return the replies
     */
    public List<Replies> getReplies() {
        return replies;
    }

    /**
     * @param replies the replies to set
     */
    public void setReplies(List<Replies> replies) {
        this.replies = replies;
    }

    @Override
    public boolean equals(Object o) {
        Topic topic = (Topic) o;
        if (this.id.equals(topic.id))
            return true;
        return false;
    }

    /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
    @Override
    public String toString() {
        return "Topic [id=" + id + ", author_id=" + author_id + ", tab=" + tab
                + ", content=" + content + ", title=" + title
                + ", last_reply_at=" + last_reply_at + ", good=" + good
                + ", top=" + top + ", reply_count=" + reply_count
                + ", visit_count=" + visit_count + ", create_at=" + create_at
                + ", author=" + author + ", replies=" + replies + "]";
    }

}
