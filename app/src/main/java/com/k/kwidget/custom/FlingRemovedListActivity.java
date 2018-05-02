package com.k.kwidget.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.k.kwidget.R;
import com.k.kwidget.custom.widget.FlingRemovedListView2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlingRemovedListActivity extends AppCompatActivity {


    @BindView(R.id.lv)
    FlingRemovedListView2 lv;

    private List<Map<String,String>> list=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fling_removed_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        for (int i = 0; i <15 ; i++) {
            Map<String,String> map=new HashMap<>();
            map.put("title","移动开发"+i);
            map.put("description","安卓自定义组件开发详解"+i);
            list.add(map);
        }

        lv.setOnRemoveItemListener(new FlingRemovedListView2.OnRemoveItemListener() {
            @Override
            public void itemRemove(int position, ListAdapter adapter) {
                list.remove(position);
                ((BaseAdapter)adapter).notifyDataSetChanged();
            }
        });


        SimpleAdapter simpleAdapter=new SimpleAdapter(this,list,
                R.layout.item_contact,new String[]{"title","description"},
                new int[]{R.id.title,R.id.description});

        lv.setAdapter(simpleAdapter);
    }


}
