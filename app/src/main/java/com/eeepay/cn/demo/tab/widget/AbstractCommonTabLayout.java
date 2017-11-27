package com.eeepay.cn.demo.tab.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.eeepay.cn.demo.tab.bean.CustomTabEntity;
import com.eeepay.cn.demo.tab.bean.TabEntity;
import com.eeepay.cn.demo.tab.listener.OnTabSelectListener;
import com.eeepay.cn.demo.tab.utils.SoundPlayUtils;
import com.eeepay.cn.demo.tab.utils.UnreadMsgUtils;
import com.eeepay.cn.demo.tab.utils.ViewFindUtils;

import java.util.ArrayList;

/**
 * 描述：通用的Tab框架
 * 作者：zhuangzeqin
 * 时间: 2016/12/30 16:30
 * 邮箱：zhuangzeqin@szxys.cn
 */
public abstract class AbstractCommonTabLayout extends AppCompatActivity {
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();//存放自定义tab 实体集合
    private ArrayList<Fragment> mFragments = null;//Fragment 集合
    private CommonTabLayout mTabLayout;//自定义Tablout 控件
    private View mDecorView;
    private ViewPager mViewPager;//viewpager 对象
    protected Context mContext;//上下文对象
    /**
     * 默认选中的索引
     **/
    private static final int SELECT_DEFAULT_INDEX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;
        setContentView();
        initViews();
        initTabEntities();
        initData();
        /** add by zhuangzeqin 2017年9月12日10:17:39 初始化默认的声音加载 **/
        SoundPlayUtils.getInstance().initDefaultSoundPool(mContext);
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
        mTabLayout = ViewFindUtils.find(mDecorView, getCommonTabLayout());
        mViewPager = ViewFindUtils.find(mDecorView, getCommonViewPager());
    }

    /**
     * 初始化 tab 选项实体信息
     * 标题，选中图标，未选中的图标
     */
    private void initTabEntities() {
        mFragments = getFragmentList();//要显示的Fragment集合
        String[] arrayTitles = getTitles();//标题
        int[] arrayIconSelectIds = getIconSelectIds();//选中图标
        int[] arrayIconUnSelectIds = getIconUnselectIds();//未选中的图标
        final int size = arrayTitles.length;
        for (int i = 0; i < size; i++) {
            mTabEntities.add(new TabEntity(arrayTitles[i], arrayIconSelectIds[i], arrayIconUnSelectIds[i]));
        }
        mTabLayout.setTabData(mTabEntities);//填充数据
        mViewPager.setAdapter(new CommonPagerAdapter(getSupportFragmentManager()));
        //设置监听事件
        mTabLayout.setOnTabSelectListener(mOnTabSelectListener);
        //设置viewpage滑动监听事件
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        //默认选择第一个
        setSelectDefaultIndex(SELECT_DEFAULT_INDEX);
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
     * 设置分割线
     *
     * @param dividerColor   颜色
     * @param dividerWidth   宽度
     * @param dividerPadding 间隔
     */
    protected void setDivisionLine(int dividerColor, float dividerWidth, float dividerPadding) {
        if (mTabLayout == null) return;
        mTabLayout.setDividerColor(dividerColor);
        mTabLayout.setDividerWidth(dividerWidth);
        mTabLayout.setDividerPadding(dividerPadding);
    }

    /**
     * 设置未读消息红点
     *
     * @param index 索引
     */
    protected void setShowDot(int index) {
        if (mTabLayout != null) {
            mTabLayout.showDot(index);
            MsgView msgView = mTabLayout.getMsgView(index);
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
        if (mTabLayout == null) return;
        //两位数
        if (count > 0 && count <= 99) {
            mTabLayout.showMsg(index, count);
            mTabLayout.setMsgMargin(0, -5, 5);
        } else if (count > 99) //三位数
        {
            mTabLayout.showMsg(index, count);
            mTabLayout.setMsgMargin(1, -5, 5);
        }
        if (backgroundColor != 0) {
            MsgView msgView = mTabLayout.getMsgView(index);
            if (msgView != null) {
                //msgView.setBackgroundColor(Color.parseColor("#6D8FB0"));
                msgView.setBackgroundColor(backgroundColor);
            }
        }
    }

    //设置监听
    private OnTabSelectListener mOnTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelect(int position) {
            SoundPlayUtils.getInstance().play();//播放声音
            //选中
            mViewPager.setCurrentItem(position, false);
        }

        @Override
        public void onTabReselect(int position) {
            //重复选中
            SoundPlayUtils.getInstance().play();//播放声音
        }
    };
    /**
     * 设置viewpage滑动监听事件
     */
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mTabLayout.setCurrentTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

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
     * 标题数组
     **/
    protected abstract String[] getTitles();

    /**
     * 选择图标数组
     **/
    protected abstract int[] getIconSelectIds();

    /**
     * 未选择图标数组
     **/
    protected abstract int[] getIconUnselectIds();

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
