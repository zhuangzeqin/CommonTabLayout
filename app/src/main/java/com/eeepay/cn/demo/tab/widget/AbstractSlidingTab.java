package com.eeepay.cn.demo.tab.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;

import com.eeepay.cn.demo.tab.listener.OnTabSelectListener;
import com.eeepay.cn.demo.tab.utils.UnreadMsgUtils;
import com.eeepay.cn.demo.tab.utils.ViewFindUtils;

import java.util.ArrayList;

/**
 * 描述：通用的可滑动的Tab框架；类似新闻客户端；条目类型比较多的情况下
 * 作者：zhuangzeqin
 * 时间: 2017/1/3 13:31
 * 邮箱：zhuangzeqin@szxys.cn
 */
public abstract class AbstractSlidingTab extends AppCompatActivity {
    private ArrayList<Fragment> mFragments = null;//Fragment 集合
    private ViewPager mViewPager;//viewpager 对象
    protected Context mContext;//上下文对象
    private View mDecorView;
    protected SlidingTabLayout mSlidingTabLayout;//可滑动的Tab框架
    private static final int SELECT_DEFAULT_INDEX = 0;// 默认选中的索引
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;
        setContentView();
        initViews();
        initTabEntities();
        initData();
    }
    /**
     * 初始化数据
     */
    protected void initData() {
    }
    /**
     * 初始化组件
     */
    private void initViews() {
        mDecorView = getWindow().getDecorView();
        mSlidingTabLayout = ViewFindUtils.find(mDecorView, getCommonTabLayout());
        mViewPager = ViewFindUtils.find(mDecorView, getCommonViewPager());
    }
    /**
     * 初始化 tab 选项实体信息
     * 标题，选中图标，未选中的图标
     */
    private void initTabEntities() {
        mFragments = getFragmentList();//要显示的Fragment集合
        String[] arrayTitles = getTitles();//标题
        mViewPager.setAdapter(new CommonPagerAdapter(getSupportFragmentManager()));
        //关联ViewPager,用于不想在ViewPager适配器中设置titles数据的情况
        mSlidingTabLayout.setViewPager(mViewPager,arrayTitles);
        //设置监听事件
        mSlidingTabLayout.setOnTabSelectListener(mOnTabSelectListener);
        //默认选择第一个
        setSelectDefaultIndex(SELECT_DEFAULT_INDEX);
    }
    /**
     * 设置未读消息红点
     *
     * @param index 索引
     */
    protected void setShowDot(int index) {
        if (mSlidingTabLayout != null) {
            mSlidingTabLayout.showDot(index);
            MsgView msgView = mSlidingTabLayout.getMsgView(index);
            if (msgView != null) {
                UnreadMsgUtils.setSize(msgView, dp2px(7.5f));
            }
        }
    }

    /**
     * 设置未读条数
     *
     * @param index 索引
     * @param count 条数
     */
    protected void setUnReadMsg(int index, int count) {
        setUnReadMsg(index, count, 0);
    }

    /**
     * 设置未读条数
     *
     * @param index           索引
     * @param count           条数
     * @param backgroundColor 设置未读消息背景
     */
    protected void setUnReadMsg(int index, int count, int backgroundColor) {
        if (mSlidingTabLayout == null) return;
        //两位数
        if (count > 0 && count <= 99) {
            mSlidingTabLayout.showMsg(index, count);
            mSlidingTabLayout.setMsgMargin(index, 0, 10);
        } else if (count > 99) //三位数
        {
            mSlidingTabLayout.showMsg(index, count);
            mSlidingTabLayout.setMsgMargin(index, 0, 10);
        }
        if (backgroundColor != 0) {
            MsgView msgView = mSlidingTabLayout.getMsgView(index);
            if (msgView != null) {
                //msgView.setBackgroundColor(Color.parseColor("#6D8FB0"));
                msgView.setBackgroundColor(backgroundColor);
            }
        }
    }

    /**
     * 设置tab固定宽度
     * @param tabWidth
     */
    protected void setTabWidth(float tabWidth)
    {
        if (mSlidingTabLayout==null) return;
        mSlidingTabLayout.setTabWidth(tabWidth);
    }

    /**
     * 动态添加一个Title 标签
     * @param title
     */
    protected void addNewTab(String title)
    {
        if (mSlidingTabLayout!=null && !TextUtils.isEmpty(title))
        mSlidingTabLayout.addNewTab(title);
    }
    /**
     * 设置分割线
     *
     * @param dividerColor   颜色
     * @param dividerWidth   宽度
     * @param dividerPadding 间隔
     */
    protected void setDivisionLine(int dividerColor, float dividerWidth, float dividerPadding) {
        if (mSlidingTabLayout == null) return;
        mSlidingTabLayout.setDividerColor(dividerColor);
        mSlidingTabLayout.setDividerWidth(dividerWidth);
        mSlidingTabLayout.setDividerPadding(dividerPadding);
    }
    /**
     * 设置默认选择第几个
     *
     * @param index 索引值
     */
    protected void setSelectDefaultIndex(int index) {
        //默认选择第一个
        if (mViewPager != null)
            mViewPager.setCurrentItem(index, false);
    }
    /**
     * viewpage 适配器
     */
    private class CommonPagerAdapter extends FragmentPagerAdapter {
        public CommonPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getTitles()[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
    //设置监听
    private OnTabSelectListener mOnTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelect(int position) {
            //选中
            mViewPager.setCurrentItem(position, false);
        }

        @Override
        public void onTabReselect(int position) {
            //重复选中
        }
    };

    /**
     * db 转换 px
     *
     * @param dp
     * @return
     */
    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    /**
     * 设置布局文件
     */
    protected abstract void setContentView();
    /**
     * 获取标题
     *
     * @return 返回标题数组
     */
    protected abstract String[] getTitles();

    /**
     * Fragment 集合
     **/
    protected abstract ArrayList<Fragment> getFragmentList();
    /**
     * CommonTabLayout 资源id
     **/
    protected abstract int getCommonTabLayout();

    /**
     * ViewPager 资源id
     **/
    protected abstract int getCommonViewPager();
}
