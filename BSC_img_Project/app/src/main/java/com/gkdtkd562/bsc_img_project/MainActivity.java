package com.gkdtkd562.bsc_img_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.gkdtkd562.bsc_img_project.adapter.TabFragment;
import com.gkdtkd562.bsc_img_project.adapter.TitleFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    List<String> bscTitles;
    List<String> genreTitles;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_nav_bsc:
                    viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int i) {
                            return new TabFragment("BSC",bscTitles);
                        }

                        @Override
                        public int getCount() {
                            return 1;
                        }
                    });
                    return true;
                case R.id.main_nav_genre:
                    viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int i) {
                            return new TabFragment("GENRE", genreTitles);
                        }

                        @Override
                        public int getCount() {
                            return 1;
                        }
                    });
                    return true;
                case R.id.main_nav_fav:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] bsvString = getResources().getStringArray(R.array.bsc_names);
//        List<String> bscList = Arrays.asList(bsvString);
//        bscTitles = new ArrayList<>(bscList);

        bscTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.bsc_names)));
        genreTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.genre_names)));

        viewPager = findViewById(R.id.main_view_pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return new TitleFragment();
            }

            @Override
            public int getCount() {
                return 1;
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
