package com.cliff.araframedemo.db;

import android.provider.BaseColumns;

/**
 * Created by Spiros I. Oikonomakis on 12/9/14.
 */
public final class DBContracts {

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_USER_ID = "userId";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASS = "password";
        public static final String COLUMN_PHONE = "phone";
    }


    public static abstract class TopicEntry implements BaseColumns {
        public static final String TABLE_NAME = "topic";
        public static final String COLUMN_NAME_ID = "tid";
        public static final String COLUMN_NAME_AID = "author_id";
        public static final String COLUMN_NAME_TAB = "tab";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_LRA = "last_reply_at";
        public static final String COLUMN_NAME_GOOD = "good";// : true,
        public static final String COLUMN_NAME_TOP = "top";// : true,
        public static final String COLUMN_NAME_TC = "reply_count";// : 24,
        public static final String COLUMN_NAME_VC = "visit_count";// : 1421,
        public static final String COLUMN_NAME_CA = "createat";// : 2015-05-22T12:26:02.597Z,
    }
}
