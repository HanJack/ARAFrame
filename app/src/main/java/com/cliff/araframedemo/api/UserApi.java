package com.cliff.araframedemo.api;

import com.cliff.araframedemo.db.User;

import retrofit.Call;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by jack on 2015/9/28.
 */
public interface UserApi {
    @POST("/api/v1/login.do")
    Call<User> login(@Query("email") String email, @Query("password") String password);
}
