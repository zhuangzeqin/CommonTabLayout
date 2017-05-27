package com.eeepay.cn.demo.tab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.eeepay.cn.demo.tab.adapter.SimpleHomeAdapter;
import com.eeepay.cn.demo.tab.ui.activity.CommonTabLayoutActivity;
import com.eeepay.cn.demo.tab.ui.activity.SegmentTabActivity;
import com.eeepay.cn.demo.tab.ui.activity.SlidingTabActivityImpl;

/**
 * 描述：封装通用的一个tab框架
 * 1 支持显示消息数量和红点
 * 2 滑动切换
 * 作者：zhuangzeqin
 * 时间: 2017/5/27-9:14
 * 邮箱：zzq@eeepay.cn
 */
public class MainActivity extends AppCompatActivity {
    private final String[] mItems = {"CommonTabLayoutActivity","SlidingTabActivityImpl","SegmentTabActivity"};
    private final Class<?>[] mClasses = {CommonTabLayoutActivity.class, SlidingTabActivityImpl.class,SegmentTabActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        initViews();
    }

    protected void initViews() {
        ListView lv = new ListView(this);
        lv.setCacheColorHint(Color.TRANSPARENT);
        lv.setFadingEdgeLength(0);
        lv.setAdapter(new SimpleHomeAdapter(this, mItems));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, mClasses[position]);
                intent.putExtra("username", "zzq");
                startActivity(intent);
            }
        });
        setContentView(lv);
    }
}
