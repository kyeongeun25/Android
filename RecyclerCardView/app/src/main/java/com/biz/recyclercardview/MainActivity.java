package com.biz.recyclercardview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView cardList;
    List<CardVO> cards; // 여러곳에서(event) 사용해야 하기 때문에 member영역에 설정
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cardList = findViewById(R.id.card_list);

        cards = new ArrayList<CardVO>();

        cards.add(new CardVO(R.drawable.a, "미키마우스"));
        cards.add(new CardVO(R.drawable.b, "슈렉"));
        cards.add(new CardVO(R.drawable.c, "밤비"));
        cards.add(new CardVO(R.drawable.d, "알라딘"));
        cards.add(new CardVO(R.drawable.e, "겨울왕국"));
        cards.add(new CardVO(R.drawable.a, "라이온킹"));
        cards.add(new CardVO(R.drawable.b, "포뇨"));

        cardList.setAdapter(new CardAdapter(cards, getApplicationContext()));

        // 화면을 그리면서 크기를 자유롭게 변경하지 말라
        cardList.setHasFixedSize(true);
        // 이미지 등을 표시할떄 단말기에 부담을 덜어주는 옵션

        layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(this,2);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        cardList.setLayoutManager(layoutManager);


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
