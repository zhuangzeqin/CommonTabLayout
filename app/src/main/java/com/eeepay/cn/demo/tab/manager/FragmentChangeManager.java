package com.eeepay.cn.demo.tab.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * 描述：Fragment 管理类
 * 作者：zhuangzeqin
 * 时间: 2016/12/30-13:22
 * 邮箱：zhuangzeqin@szxys.cn
 */
public class FragmentChangeManager {
    /**
     * FragmentManager 管理类
     */
    private FragmentManager mFragmentManager;
    /**
     * 视图内容id
     */
    private int mContainerViewId;
    /**
     * Fragment切换数组
     */
    private ArrayList<Fragment> mFragments;
    /**
     * 当前选中的Tab
     */
    private int mCurrentTab;
    /**
     * 当前选中的Tab 索引
     */
    private static final int CURRENT_DEFALUT_INDEX = 0;
    /**
     * 构造函数
     *
     * @param fm
     * @param containerViewId
     * @param fragments
     */
    public FragmentChangeManager(FragmentManager fm, int containerViewId, ArrayList<Fragment> fragments) {
        this.mFragmentManager = fm;
        this.mContainerViewId = containerViewId;
        this.mFragments = fragments;
        /** 初始化fragments */
        initFragments();
    }

    /**
     * 初始化fragments
     */
    private void initFragments() {
        for (Fragment fragment : mFragments) {
            mFragmentManager.beginTransaction().add(mContainerViewId, fragment).hide(fragment).commitAllowingStateLoss();
        }
        setFragments(CURRENT_DEFALUT_INDEX);//默认为0
    }

    /**
     * 界面切换控制
     */
    public void setFragments(int index) {
        for (int i = 0; i < mFragments.size(); i++) {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            Fragment fragment = mFragments.get(i);
            if (i == index) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commitAllowingStateLoss();
        }
        mCurrentTab = index;
    }

    /**
     * 获取当前的tab
     */
    public int getCurrentTab() {
        return mCurrentTab;
    }

    /**
     * 获取当前的Fragment
     */
    public Fragment getCurrentFragment() {
        return mFragments.get(mCurrentTab);
    }
}