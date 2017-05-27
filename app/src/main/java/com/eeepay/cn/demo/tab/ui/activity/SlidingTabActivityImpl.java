package com.eeepay.cn.demo.tab.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;

import com.eeepay.cn.demo.tab.R;
import com.eeepay.cn.demo.tab.ui.fragment.SimpleCardFragment;
import com.eeepay.cn.demo.tab.widget.AbstractSlidingTab;

import java.util.ArrayList;

/**
 * 描述：class describe
 * 作者：zhuangzeqin
 * 时间: 2017/1/3 14:35
 * 邮箱：zhuangzeqin@szxys.cn
 */
public class SlidingTabActivityImpl extends AbstractSlidingTab {
    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    /**
     * 设置布局文件
     */
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_sliding_tab);
    }
    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        setSelectDefaultIndex(2);//设置默认的选项
        setShowDot(3);
        setUnReadMsg(0, 50);
        setUnReadMsg(1, 1);
        setUnReadMsg(2, 5, Color.parseColor("#6D8FB0"));
        setDivisionLine(Color.parseColor("#ffffff"),0.5F,10);
        addNewTab("zzzzz");
    }
    /**
     * 获取标题
     *
     * @return 返回标题数组
     */
    @Override
    protected String[] getTitles() {
        return mTitles;
    }

    /**
     * Fragment 集合
     **/
    @Override
    protected ArrayList<Fragment> getFragmentList() {
        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance(title));
        }
        return mFragments;
    }

    /**
     * CommonTabLayout 资源id
     **/
    @Override
    protected int getCommonTabLayout() {
        return R.id.tl_1;
    }

    /**
     * ViewPager 资源id
     **/
    @Override
    protected int getCommonViewPager() {
        return R.id.vp;
    }
}
