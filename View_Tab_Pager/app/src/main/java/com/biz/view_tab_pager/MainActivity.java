package com.biz.view_tab_pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager vp ;
    TabLayout tabMenu ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // viewPager와 tabLayout 객체 생성
        vp = findViewById(R.id.view_main);
        tabMenu = findViewById(R.id.tab_menu);

        // viewPager에 Adapter 연결
        vp.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        // tabLayout의 구성화면을 ViewPager로 세팅
        // 연동
        tabMenu.setupWithViewPager(vp, true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private class PagerAdapter extends FragmentStatePagerAdapter{

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "첫번째 탭" ;
                case 1 : return "두번째 탭" ;
                case 2 : return "세번째 탭";
            }

            return super.getPageTitle(position);
        }

        @Override
        public Fragment getItem(int position) {
            // position은 정수값을 갖는 매개변수

            switch(position) {
                case 0:
                    /*
                        new FirstFragment()를 실행하면 화면을 스크롤, 다시열기 할 때마다 새로운 객체를 생성한다.
                        화면을 많이 스크롤 하게 되면, 어느순간 App이 무거워지고 메모리, 시스템 관련 오류가 날 수 있다.
                     */
                    //return new FirstFragment();
                    /*
                        이미 1번 생성된 fragment 객체를 재 활용한다.
                     */
                    return FirstFragment.newInstance("첫번째화면", "아녕!");
                case 1:
                    return SecondFragment.newInstance("두번째화면", "반가워");
                default:return null;
            }

        }

        /*
           getCount()의 return 값은
           getItem 메서드에 정의된 fragement 개수와 일치 해야한다.
           그렇지 않으면 App이 실행되면서 오류가 난다.

           view Pager는 App을 생성하면서 getCount를 참조하여 Fragment를 미리 Loading하기 때문에
         */
        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
