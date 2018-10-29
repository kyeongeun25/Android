package com.gkdtkd562.bsc_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gkdtkd562.bsc_project.database.BscVO;
import com.gkdtkd562.bsc_project.database.MemberVO;
import com.gkdtkd562.bsc_project.helper.MainPageAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference databaseReference;
    List<BscVO> bsclist;
    List<String> bscTitle;
    List<String> genreTitle;

    Button btn_bsc;
    Button btn_genre;
    Button btn_fav;

    MainPageAdapter mainPageAdapter;
    ViewPager vp;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn_bsc = findViewById(R.id.btn_bsc);
        btn_genre = findViewById(R.id.btn_genre);
        btn_fav = findViewById(R.id.btn_fav);

        btn_bsc.setOnClickListener(this);
        btn_genre.setOnClickListener(this);
        btn_fav.setOnClickListener(this);

        vp = findViewById(R.id.vp);
        tabLayout = findViewById(R.id.tab_layout);

//        Intent wel = getIntent();
//        MemberVO vo = (MemberVO)wel.getSerializableExtra("MEMBER");

        bsclist = new ArrayList<>();
        bscTitle = new ArrayList<>();
        genreTitle = new ArrayList<>();

        // data불러오기
        FirebaseDatabase.getInstance().getReference().child("doc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bsclist.clear();
                for(DataSnapshot sd : dataSnapshot.getChildren()){
                    BscVO vo = sd.getValue(BscVO.class);
                    bsclist.add(vo);
                    bscTitle.add(vo.getBsc());
                    genreTitle.add(vo.getGenre());
                }


                // bscTitle과 genreTitle의 중복제거
                bscTitle = new ArrayList(new TreeSet(bscTitle));
                genreTitle = new ArrayList(new TreeSet(genreTitle));

                // 기본 방송정보를 보여주도록 설정
                mainPageAdapter
                        = new MainPageAdapter(getSupportFragmentManager(),bsclist,bscTitle);
                vp.setAdapter(mainPageAdapter);
                tabLayout.setupWithViewPager(vp);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("데이터 가져오기 오류", databaseError.toString());
            }
        });


//        TextView txt_userid;
//        txt_userid = findViewById(R.id.text_userid);
//        txt_userid.setText(vo.getUserId());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btn_bsc :
                mainPageAdapter = new MainPageAdapter(getSupportFragmentManager(), bsclist, bscTitle);
                break;
            case R.id.btn_genre :
                mainPageAdapter = new MainPageAdapter(getSupportFragmentManager(), bsclist, genreTitle);
                break;

        }

        vp.setAdapter(mainPageAdapter);
        tabLayout.setupWithViewPager(vp);
    }
}
