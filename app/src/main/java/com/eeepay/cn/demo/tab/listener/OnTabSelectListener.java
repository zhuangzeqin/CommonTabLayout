package com.eeepay.cn.demo.tab.listener;

/**
 * 描述：定义接口事件监听
 * 作者：zhuangzeqin
 * 时间: 2016/12/30-13:17
 * 邮箱：zhuangzeqin@szxys.cn
 */
public interface OnTabSelectListener {
    /**
     * tab 选择
     **/
    void onTabSelect(int position);

    /**
     * tab 重新选择
     **/
    void onTabReselect(int position);
}