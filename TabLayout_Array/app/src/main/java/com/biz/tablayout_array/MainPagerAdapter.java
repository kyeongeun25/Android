package com.biz.tablayout_array;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.biz.tablayout_array.models.PagerVO;

import java.util.List;

public class MainPagerAdapter extends FragmentStatePagerAdapter{

    List<String> stringList ;
    List<PagerVO> pageList ;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }
//
//    public MainPagerAdapter(FragmentManager fm, List<String> list) {
//        super(fm);
//        this.stringList = list ;
//    }

    public MainPagerAdapter(FragmentManager fm, List<PagerVO> list) {
        super(fm);
        this.pageList = list ;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       // return super.getPageTitle(position);
        return  pageList.get(position).getPageTitle();
    }

    @Override
    public Fragment getItem(int position) {
//        return MainFragment.newInstance(pageList.get(position).getPageTitle(), "연습");
        return new MainFragment(pageList.get(position).getTextList());
    }

    @Override
    public int getCount() {
        return pageList.size();
    }
}
