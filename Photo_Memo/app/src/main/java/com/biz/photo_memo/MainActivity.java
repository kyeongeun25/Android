package com.biz.photo_memo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import com.biz.photo_memo.db.GradeDBHelper;
import com.biz.photo_memo.db.MemoDBHelper;
import com.biz.photo_memo.vo.GradeVO;
import com.biz.photo_memo.vo.MemoVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText txt_memo ;
    private ImageButton btn_save;
    private RecyclerView memoListView ;

    // DB와 연동을 위해서는 데이터를 담을 VO리스트와
    // ViewAdapter를 member영역에 선언해주는 것이 덜피곤하다.
    private List<MemoVO> memos;
    private MemoViewAdapter memoViewAdapter ;

    private MemoDBHelper memoDBHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt_memo = findViewById(R.id.txt_memo);
        btn_save = findViewById(R.id.btn_save);
        memoListView = findViewById(R.id.memo_list);
        memoDBHelper = new MemoDBHelper(MainActivity.this);

        GradeDBHelper gradeDBHelper = new GradeDBHelper(MainActivity.this);
        List<GradeVO> grades = gradeDBHelper.selectAll();

        /*
        memos = new ArrayList<MemoVO>();
        SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd");
        String date = sd.format(Calendar.getInstance().getTime());
        memos.add(new MemoVO(date, "메모하기"));
        memos.add(new MemoVO(date, "메모하기1"));
        memos.add(new MemoVO(date, "메모하기2"));
        memos.add(new MemoVO(date, "메모하기3"));
        memos.add(new MemoVO(date, "메모하기4"));
        memos.add(new MemoVO(date, "메모하기5"));
        */
        // DB로 부터 데이터를 가져와서 memos를 초기화 시켜줘야 한다.

        memos = memoDBHelper.selectAll();

        memoViewAdapter = new MemoViewAdapter(memos);
        memoListView.setAdapter(memoViewAdapter);

        // RecyclerView.LayoutManager로 선언한 이유는
        // layoutManager를 바꿔가면서 테스트를 해볼 수 있기 유리하게 하려고
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager = new GridLayoutManager(this,2);
        // layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        memoListView.setLayoutManager(layoutManager);

        ItemTouchHelper helper ;
        helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });

        // 실제 좌우로 밀기 동작이 일어나도록 설정
        helper.attachToRecyclerView(memoListView);

        // save 버튼을 클릭하면 메모를 database에 저장하기
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 메모를 저장한 날짜를 생성
                SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd");
                String date = sd.format(Calendar.getInstance().getTime());

                // TextEdit로부터 memo 추출
                String memo = txt_memo.getText().toString();

                MemoVO vo = new MemoVO();
                vo.setDate(date);
                vo.setMemo(memo);

                long id = memoDBHelper.insertMemo(vo);

                // 정상적으로 추가가 되었으면
                if(id>0){
                    // 화면을 갱신
                    // 1. 다시 데이터를 selectAll()하고
                    // 2. 그 값으로 Adapter를 다시 생성하고
                    // 3. 화면 갱신 알림을 설정

                    memos = memoDBHelper.selectAll();
                    memoViewAdapter = new MemoViewAdapter(memos);
                    memoListView.setAdapter(memoViewAdapter);

                    // 추가되었음을 알림
                    memoListView.getAdapter().notifyItemInserted(
                            memos.size()-1 // 추가된 위히치
                    );

                    // RecyclerView를 scroll 해서 제일 끝의 항목이 화면에 나타나도록
                    memoListView.scrollToPosition(
                            memoListView.getAdapter().getItemCount()-1
                    );

                }

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
