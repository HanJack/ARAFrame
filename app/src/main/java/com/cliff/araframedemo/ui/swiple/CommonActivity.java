package com.cliff.araframedemo.ui.swiple;

import com.cliff.araframedemo.R;
import com.cliff.hsj.ui.SwipeBackActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.swip_activity_common)
public class CommonActivity extends SwipeBackActivity {

    @AfterViews
    void init() {
        setTitle("Common");
    }

}
