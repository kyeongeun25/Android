package com.biz.woo_project.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.biz.woo_project.fragment.MainFragment;

import java.util.List;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    List<String> pageTitle ;
    int menu_id = 0;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainPagerAdapter(FragmentManager fm, List<String> pageTitle) {
        super(fm);
        this.pageTitle = pageTitle;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
        return pageTitle.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return MainFragment.newInstance(pageTitle.get(i));
    }

    @Override
    public int getCount() {
        return pageTitle.size();
    }
}
