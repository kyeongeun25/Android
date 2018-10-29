package com.biz.tablayout_array;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.biz.tablayout_array.helper.BottomNavigationViewBehavior;
import com.biz.tablayout_array.models.PagerVO;
import com.biz.tablayout_array.models.TextVO;
import com.biz.tablayout_array.service.PagerDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ViewPager vp ;
    TabLayout tab ;

    // Navigation Event 핸들러
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            // Navigation 내부의 아이콘을 클릭하면 클릭된 아이콘의 id값을 추출한다.
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                   //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        List<String> strList = new ArrayList<String>();
//        strList.add("대한민국만세");
//        strList.add("우리나라만세");
//        strList.add("Republic of Korea");
//        strList.add("이몽룡");
//        strList.add("성춘향");
//        strList.add("홍길동");

        PagerDao dao = new PagerDao();
//        List<PagerVO> pageList = dao.makePager();
        List<PagerVO> pageList = dao.makeSB();
        Log.d("pageList",pageList.toString());

        vp = findViewById(R.id.view_pager);
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), pageList);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);

        tab = findViewById(R.id.tab_layout);
        tab.setupWithViewPager(vp);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)navigation.getLayoutParams();
//        lp.setBehavior(new BottomNavigationViewBehavior());
    }

}
