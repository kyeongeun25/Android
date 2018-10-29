package com.gkdtkd562.bsc_img_project.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gkdtkd562.bsc_img_project.R;



import java.util.List;

public class TabFragment extends Fragment{

    String page;
    List<String> titles;


    public TabFragment() {
    }

    @SuppressLint("ValidFragment")
    public TabFragment(String page, List<String> titles) {
        this.page = page;
        this.titles = titles;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment, container,false);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager tabViewPager = view.findViewById(R.id.tab_view_pager);

        tabViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

            @Override
            public Fragment getItem(int i) {
                // db조회할때 사용할 page이름과 TabTitle을 전달
                return new BscListFragment(page,titles.get(i));
            }

            @Override
            public int getCount() {
                return titles.size();
            }
        });

        tabLayout.setupWithViewPager(tabViewPager);
        return view;
    }
}
