package com.cliff.araframedemo.db;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.cliff.hsj.db.DBModel;

import java.io.Serializable;

/**
 * Created by jack on 2015/9/28.
 */
@Table(name = DBContracts.UserEntry.TABLE_NAME)
public class User extends DBModel<User> implements Serializable{
    @Column(name = DBContracts.UserEntry.COLUMN_USER_ID)
    private String userId;
    @Column(name = DBContracts.UserEntry.COLUMN_EMAIL)
    private String email;
    @Column(name = DBContracts.UserEntry.COLUMN_PASS)
    private String password;
    @Column(name = DBContracts.UserEntry.COLUMN_PHONE)
    private String phone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
