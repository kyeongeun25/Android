package com.biz.woo_page;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.biz.woo_page.dao.WooDao;
import com.biz.woo_page.database.BscVO;
import com.biz.woo_page.database.GoData;
import com.biz.woo_page.helper.MainPageAdapter;
import com.biz.woo_page.helper.StringPagerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ViewPager vp;
    TabLayout tabLayout;
    WooDao wooDao;

    // firebase에서 data를 가져와서
    // tablelayout의 tab list를 생성하기 위해 2개의 list변수를 선언
    List<String> bscList;
    List<String> genList;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            String[] pageTitle ;
            MainPageAdapter adapter ;
            StringPagerAdapter stringPagerAdapter;
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    stringPagerAdapter = new StringPagerAdapter(getSupportFragmentManager(),bscList);

                    vp.setAdapter(stringPagerAdapter);

//                    pageTitle = wooDao.getTitle_preg();
//                    adapter = new MainPageAdapter(getSupportFragmentManager(), pageTitle);
//                    vp.setAdapter(adapter);

                    return true;
                case R.id.navigation_dashboard:

                    stringPagerAdapter = new StringPagerAdapter(getSupportFragmentManager(),genList);

                    vp.setAdapter(stringPagerAdapter);

//                    pageTitle = wooDao.getTitle_baby();
//                    adapter = new MainPageAdapter(getSupportFragmentManager(), pageTitle);
//                    vp.setAdapter(adapter);

                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;

                case R.id. navigation_go :
                    GoData goData = new GoData();
                    // Async 호출 코드
                    //goData.execute(100);
/                   goData.getData();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        wooDao = new WooDao(getApplicationContext());
        wooDao.makeTitle();
        String[] pageTitle = wooDao.getTitle_preg();

        bscList = new ArrayList<>();
        genList = new ArrayList<>();

        // firebase로 부터 데이터 변화에 대한 event를 수신할 method를 선언
        // firebase로 부터 이벤트 신호가 오면, 데이터를 읽어서 bscList와 genList에 데이터를 저장해 놓을 예정
        FirebaseDatabase.getInstance()  // db 정도 획득
            .getReference()     // db 연결 객체 획득
            .child("doc")       // doc 데이터를 감시하라
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // dataSnapshot
                    // firebase의 doc데이터가 변화되면, 자동으로 데이터를 수신해서 보관할 객체

                    // dataSnapshot으로 부터 필요한 데이터를 추출하는 코드

                    bscList.clear();
                    genList.clear();

                    for(DataSnapshot sd : dataSnapshot.getChildren()){
                        /*
                            1. sd로 부터 각 칼럼값을 추출
                            2. 추출된 값을 vo에 세팅하는 절차
                            BscVO vo = new BscVO() ;
                            vo.setNo(sd.getNo())
                            vo.setTitle(sd.getTitle())
                            vo.setMemo(sd.getMemo())
                            vo.setGenre(sd.getGenre())
                         */


                        BscVO vo = sd.getValue(BscVO.class);

                        bscList.add(vo.bsc);
                        genList.add(vo.genre);

                    }
                    // 현재 추출된 data는 중복된 data
                    // >> 중복없는 data로 변환
                    Set<String> set = new TreeSet<>(bscList);       // 중복되지 않고, 오름차순
                    bscList = new ArrayList<>(set);
                    genList = new ArrayList<>(new TreeSet(genList));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("Data 오류", databaseError.toString());
                }
            });    // 데이터가 변화되면 알려달라

       // 최초에 보여줄 page
        MainPageAdapter adapter = new MainPageAdapter(getSupportFragmentManager(), pageTitle);
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
