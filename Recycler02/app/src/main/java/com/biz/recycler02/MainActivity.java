package com.biz.recycler02;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.biz.recycler02.biz.com.recycler02.vo.MemberVO;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mylist ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<MemberVO> members = new ArrayList<MemberVO>();


        // 안드로이드에서는 상당부분
        // 클래스를 배열로 만들고 초기홯는 코드를 사용하지 않는다.

        // getDrawable() 메서드는 Vector Asset을 열어서
        // 이미지로 변환하고, Drawable 객체를 초기화 한 후
        // 값을 return 한다
        // 따라서 drawables의 배열요소를 초기화 하는 코드는
        // 사용 할 필요가 없다.
        Drawable[] drawables = new Drawable[3];
        drawables[0] = getDrawable(R.drawable.ic_adb_black_24dp);
        drawables[1] = getDrawable(R.drawable.ic_android_black_24dp);
        drawables[2] = getDrawable(R.drawable.ic_filter_vintage_black_24dp);

        // 30개짜리 memberVO list를 생성성
       for(int i = 0 ; i < 30 ; i++){

            MemberVO vo = new MemberVO();
            vo.setImage(drawables[i%3]);

            int r = (int)(Math.random() * 100 );

            vo.setName(r + " 번째 값");
            members.add(vo);
        }

        // activity_main.xml에 정의된 RecyclerView를 사용하기 위한 연결
        mylist = findViewById(R.id.mylist);

        // 데이터 표시방법 연결
        mylist.setAdapter(new MemberAdapter(members));

        // RecyclerView를 Scroll하거나 하는 기능을 쓸 수 있도록 연결
        mylist.setLayoutManager(new LinearLayoutManager(this));

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
}
