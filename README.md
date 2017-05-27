# CommonTabLayout

封装通用的TabLayout 框架（Viewpage+Fragment+CommonTabLayout）

使用的时；特别方便；继承AbstractCommonTabLayout 实现以下几个参数即可

1 显示的标题，

2 选中或者未选中的图标

3 Fragment 集合 

4 传入ViewPager 资源id

5 传入CommonTabLayout 资源id

**********************部分代码例子*********************************

private String[] mTitles = {"首页", "消息", "联系人", "更多"};//标题

private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};//选中

private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};//未选中
	    
private ArrayList<Fragment> mFragments = new ArrayList<>();//Fragment 集合

另外可以

//****************根据需求设置相应的未读数**********************

setSelectDefaultIndex(0);//设置默认的选项

setShowDot(3);//第四个tab 显示红点

setUnReadMsg(0, 55);//第一个显示55个未读消息

setUnReadMsg(1, 100);//第二个显示99+ 个未读消息

setUnReadMsg(2, 5, Color.parseColor("#6D8FB0"));//第三个显示未读消息和设置背景颜色

setDivisionLine(Color.parseColor("#6D8FB0"), 0.5F, 20);//设置分割线

