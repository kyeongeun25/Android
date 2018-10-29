package com.gkdtkd562.bsc_project.helper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;

import com.gkdtkd562.bsc_project.R;
import com.gkdtkd562.bsc_project.database.BscVO;
import com.gkdtkd562.bsc_project.fragment.MainFragment;

import java.util.List;

public class MainPageAdapter extends FragmentStatePagerAdapter {

    List<String> pageTitles;
    List<BscVO> bsclist;
    int buttonid;

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainPageAdapter(FragmentManager fm, List<BscVO> bsclist){
        super(fm);
        this.bsclist = bsclist;
    }

    public MainPageAdapter(FragmentManager fm, List<BscVO> bsclist, List<String> pageTitles){
        super(fm);
        this.bsclist = bsclist;
        this.pageTitles = pageTitles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
//        return bsclist.get(position).getBsc();

        return pageTitles.get(position).toString();
    }

    @Override
    public Fragment getItem(int position) {
        return new MainFragment(bsclist, buttonid);
    }

    @Override
    public int getCount() {
        return pageTitles.size();
    }
}
