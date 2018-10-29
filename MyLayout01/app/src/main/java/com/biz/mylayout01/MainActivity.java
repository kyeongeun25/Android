package com.biz.mylayout01;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1;
    Button btn2;
    Button btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn1 = findViewById(R.id.btn1); //생성
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        // 값 버튼에 이벤트 등록
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        // view(Button)의 setVisibility라는 setter값을 (visibility라는 member변수가 있을것이다 라고 예상)
        // View.GONE 이라는 값으로 세팅하라
        // view.setVisibility(View.GONE);  // 현재 터치된 view(Button)을 감추어라

        int id = view.getId();
        if(id == R.id.btn1){
            btn2.setVisibility(View.VISIBLE);   // 보이기
        }
        if(id == R.id.btn2){
            btn2.setVisibility(View.GONE);  // 없애기
        }
        if(id == R.id.btn3){
            btn2.setVisibility(View.INVISIBLE); // 감추기
        }

    }
}
