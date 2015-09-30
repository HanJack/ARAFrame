package com.cliff.araframedemo.ui.swiple;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cliff.araframedemo.R;
import com.cliff.hsj.ui.BaseActivity;
import com.cliff.hsj.ui.widget.SwipeBackLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EActivity(R.layout.swip_activity_list_view)
public class ListViewActivity extends BaseActivity {

    @ViewById(R.id.listview)
    ListView lv;
    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    private List<HashMap<String, String>> data;

    @ViewById(R.id.swipeBackLayout)
    SwipeBackLayout swipeBackLayout;


    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title", "Test" + i);
            data.add(map);
        }
    }

    @AfterViews
    void initView() {
        initData();
        toolbar.setTitle("activity_list_view");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.TOP);

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                data, R.layout.item_test, new String[]{"title"},
                new int[]{R.id.tv_test});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        data.get(position).get("title"), Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

}
