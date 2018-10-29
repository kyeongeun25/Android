package com.biz.view_pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ViewPager vp ;
    Button btn_01 ;
    Button btn_02 ;
    Button btn_03 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vp = findViewById(R.id.vp_main);
        // App이 실행되었을때 최초로 보여줄 ViewPager를 구성
        // ViewPager를 생성하기 위해서 많은 코드를 사용해야 하지만
        // Adapter를 이용해서 코드부분을 절약할 수 있다.

        //1.
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pagerAdapter);


        // 2.
        // 둘중에하나!
        vp.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        // 3개의 버튼을 클릭하면 Fragment 3개를 번갈아 보이도록 한다.
        btn_01 = findViewById(R.id.btn_01);
        btn_02 = findViewById(R.id.btn_02);
        btn_03 = findViewById(R.id.btn_03) ;

        btn_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(0);
            }
        });

        btn_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(1);
            }
        });

        btn_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(2);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    /*
    ViewPager에 Fragment를 불러오기 위한 Helper Class
    PagerAdapter 클래스는 ViewPager가 이쓴 MainClass에 inner class로 선언하자.
 */
    private class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // Fragment를 ViewPager에 표시하기 위한 선택 기능의 method()
        // position의 값을 받아서 어떤 Fragment를 열것인가에 대한 코드를 작성
        @Override
        public Fragment getItem(int position) {
            // setCurrentItem(0) 으로 호출하면
            if (position == 0) {
                return new FirstFragment();
            }
            if(position == 1){
                return new SecondFragment();
            }
            if(position == 2){
                return new ThirdFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
