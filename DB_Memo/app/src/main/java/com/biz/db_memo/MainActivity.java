package com.biz.db_memo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import android.widget.Button;
import android.widget.Toast;

import com.biz.db_memo.db.DBHelper;
import com.biz.db_memo.vo.MemoVO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_save ;
    TextInputEditText txt_subject ;
    TextInputEditText txt_memo ;
    RecyclerView dataList;
    MemoVO memoVO ;
    List<MemoVO> memos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_save = findViewById(R.id.btn_save);
        txt_subject = findViewById(R.id.txt_subject);
        txt_memo = findViewById(R.id.txt_memo);
        dataList = findViewById(R.id.list_db);

        final DBHelper dbHelper = new DBHelper(MainActivity.this);

        memos = dbHelper.selectAll();

        dataList.setAdapter(new MemoViewAdapter(memos));
        dataList.setLayoutManager(new LinearLayoutManager(this));

        btn_save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                String subject = txt_subject.getText().toString();
                String memo = txt_memo.getText().toString();

                // 현재 날짜를 시스템으로부터 추출
                Calendar calendar = Calendar.getInstance();
                // 날짜를 문자열로 바꿈
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd");

                String date = sd.format(calendar.getTime());

                // 안드로이드 8.0이상에서만 사용가능
                // LocalDate localDate = LocalDate.now();

                memoVO = new MemoVO();




                memoVO.setDate(date);
                memoVO.setSubject(subject);
                memoVO.setMemo(memo);

                // insert DBHelper의 도움을 받는다.
                long insertID = dbHelper.insertDB(memoVO);
                Toast.makeText(MainActivity.this,String.valueOf(insertID), Toast.LENGTH_SHORT).show();

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
}
