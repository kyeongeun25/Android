package com.biz.db_addr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.biz.db_addr.helper.AddrViewAdapter;
import com.biz.db_addr.vo.AddrVO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 1. Activity.xml의 view의 정보를 보관할 객체 변수 선언
    TextInputEditText txt_name;
    TextInputEditText txt_phone;
    TextInputEditText txt_email;

    RecyclerView addrList;
    List<AddrVO> addrs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 2. 객체 변수와 view들을 연결
        txt_name = findViewById(R.id.txt_name);
        txt_email = findViewById(R.id.txt_email);
        txt_phone = findViewById(R.id.txt_phone);

        addrList = findViewById(R.id.addr_list);

        // addrList(RecyclerView)에 표시할 데이터 확보
        // DB를 기준으로 할 때는 List<AddrVO>를 생성하지 않아도 된다.
        addrs = new ArrayList<AddrVO>();

        // DB로 부터 데이터를 가져오는 부분 필요

        AddrViewAdapter adapter = new AddrViewAdapter(addrs);
        addrList.setAdapter(adapter);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        addrList.setLayoutManager(lm);

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
