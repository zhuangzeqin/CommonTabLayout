package com.eeepay.cn.demo.tab.bean;

/**
 * 描述：定义TAB 实体类
 * 作者：zhuangzeqin
 * 时间: 2016/12/30-13:51
 * 邮箱：zhuangzeqin@szxys.cn
 */
public class TabEntity implements CustomTabEntity {
    private String title;//标题
    private int selectedIcon;//选择的图片资源
    private int unSelectedIcon;//未选择时的图片资源

    /**
     * 构造函数
     * @param title
     * @param selectedIcon
     * @param unSelectedIcon
     */
    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }
    //get 方法
    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
