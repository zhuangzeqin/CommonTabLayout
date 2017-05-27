package com.eeepay.cn.demo.tab.bean;

import android.support.annotation.DrawableRes;

/**
 * 描述：自定义tab 实体
 * 作者：zhuangzeqin
 * 时间: 2016/12/30-13:18
 * 邮箱：zhuangzeqin@szxys.cn
 */
public interface CustomTabEntity {
    /**
     * tab 标题
     **/
    String getTabTitle();

    /**
     * tab 选中图标Icon
     **/
    @DrawableRes
    int getTabSelectedIcon();

    /**
     * tab 未选中图标Icon
     **/
    @DrawableRes
    int getTabUnselectedIcon();
}