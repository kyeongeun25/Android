package com.gkdtkd562.woo_project2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.gkdtkd562.woo_project2.helper.MainFragmentAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> preg_titles;
    List<String> baby_titles ;

    TabLayout tabLayout;
    ViewPager viewPager ;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_navigation_preg :
                    viewPager.setAdapter(
                            new MainFragmentAdapter(
                                    getSupportFragmentManager(),"PREG",preg_titles));
                    return true;
                case R.id.main_navigation_baby :
                    viewPager.setAdapter(
                            new MainFragmentAdapter(
                                    getSupportFragmentManager(),"BABY",baby_titles));
                    return true;
                case R.id.main_navigation_children :
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.main_tab_layout);
        viewPager = findViewById(R.id.main_view_pager);

        preg_titles
                = new ArrayList<>(Arrays
                .asList(getResources()
                        .getStringArray(R.array.title_preg)));
        baby_titles
                = new ArrayList<>(Arrays
                .asList(getResources()
                        .getStringArray(R.array.title_baby)));

        viewPager.setAdapter(
                new MainFragmentAdapter(
                        getSupportFragmentManager(),"PREG",preg_titles));
        tabLayout.setupWithViewPager(viewPager);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.main_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}





