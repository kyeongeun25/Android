package com.gkdtkd562.woo_project2.helper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gkdtkd562.woo_project2.fragment.PageFragment;

import java.util.List;

public class MainFragmentAdapter extends FragmentStatePagerAdapter {

    List<String> pageTitles;
    String mainTitle ;
    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainFragmentAdapter(FragmentManager fm,String mainTitle, List<String> pageTitles) {
        super(fm);
        this.pageTitles = pageTitles;
        this.mainTitle = mainTitle;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // return super.getPageTitle(position);
        return pageTitles.get(position);
    }

    @Override
    public Fragment getItem(int i) {

        return new PageFragment(mainTitle, pageTitles.get(i));
    }

    @Override
    public int getCount() {
        return pageTitles.size();
    }
}