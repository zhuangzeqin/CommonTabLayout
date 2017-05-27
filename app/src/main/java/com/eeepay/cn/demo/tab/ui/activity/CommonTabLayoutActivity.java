package com.eeepay.cn.demo.tab.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;

import com.eeepay.cn.demo.tab.R;
import com.eeepay.cn.demo.tab.ui.fragment.SimpleCardFragment;
import com.eeepay.cn.demo.tab.widget.AbstractCommonTabLayout;

import java.util.ArrayList;

/**
 * 描述：通用的TabLayout 框架
 * 作者：zhuangzeqin
 * 时间: 2017/1/3-8:52
 * 邮箱：zhuangzeqin@szxys.cn
 */
public class CommonTabLayoutActivity extends AbstractCommonTabLayout {
   private String[] mTitles = {"首页", "消息", "联系人", "更多"};//标题

   private int[] mIconUnselectIds = {
           R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
           R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};//选中

   private int[] mIconSelectIds = {
           R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
           R.mipmap.tab_contact_select, R.mipmap.tab_more_select};//未选中

   private ArrayList<Fragment> mFragments = new ArrayList<>();//Fragment 集合

   /**
    * 设置布局文件
    */
   @Override
   protected void setContentView() {
       setContentView(R.layout.activity_flyco_tab_layout_main);
   }

   /**
    * 初始化数据
    */
   @Override
   protected void initData() {
       super.initData();
       setSelectDefaultIndex(0);//设置默认的选项
       setShowDot(3);
       setUnReadMsg(0, 55);
       setUnReadMsg(1, 100);
       setUnReadMsg(2, 5, Color.parseColor("#6D8FB0"));
       setDivisionLine(Color.parseColor("#6D8FB0"),0.5F,20);
   }

   /**
    * 标题数组
    **/
   @Override
   protected String[] getTitles() {
       return mTitles;
   }

   /**
    * 选择图标数组
    **/
   @Override
   protected int[] getIconSelectIds() {
       return mIconSelectIds;
   }

   /**
    * 未选择图标数组
    **/
   @Override
   protected int[] getIconUnselectIds() {
       return mIconUnselectIds;
   }

   /**
    * Fragment 集合
    **/
   @Override
   protected ArrayList<Fragment> getFragmentList() {
       for (String title : mTitles) {
           mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
       }
       return mFragments;
   }

   /**
    * CommonTabLayout 资源id
    **/
   @Override
   protected int getCommonTabLayout() {
       return R.id.tl_2;
   }

   /**
    * ViewPager 资源id
    **/
   @Override
   protected int getCommonViewPager() {
       return R.id.vp_2;
   }
}
