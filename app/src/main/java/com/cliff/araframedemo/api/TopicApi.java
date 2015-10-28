package com.cliff.araframedemo.api;

import com.cliff.araframedemo.api.entity.TopicDetialResult;
import com.cliff.araframedemo.api.entity.TopicListResult;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by jack on 2015/9/23.
 */
public interface TopicApi {

    /**
     * 获取话题
     *
     * @param tabType
     * @param page
     * @param limit
     * @param mdrender
     * @return
     */
    @GET("/api/v1/topics")
    Call<TopicListResult> getTopics(@Query("tabType") String tabType, @Query("page") int page,
                                    @Query("limit") int limit, @Query("mdrender") boolean mdrender);

    @GET("/api/v1/topics/{topicId}")
    Call<TopicDetialResult> getReplies(@Path("topicId") String topicId);
}
