package com.biz.recycler_0720_addressbook;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.biz.recycler_0720_addressbook.adapter.AddrRecyclerAdapter;
import com.biz.recycler_0720_addressbook.vo.AddrBookVO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView addrRecyclerView ;
    private List<AddrBookVO> addrs ;

    private TextView txt_name ;
    private TextView txt_tel ;

    private ImageButton btn_save ;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addrRecyclerView = findViewById(R.id.addr_list_view);
        addrs = new ArrayList<AddrBookVO>();

        txt_name = findViewById(R.id.txt_name);
        txt_tel = findViewById(R.id.txt_tel);
        btn_save = findViewById(R.id.btn_save);

        // 1. vo를 생성하고, 각 요소 값을 setter를 이용해서 추가
        AddrBookVO vo = new AddrBookVO();
        vo.setAddr_image(R.drawable.ic_person_black_24dp);
        vo.setAddr_name("홍길동");
        vo.setAddr_tel("010-1111-1111");
        addrs.add(vo);

        // 2. 생성자에 각 요소의 값을 주고 vo를 만드는 방법
        vo = new AddrBookVO(R.drawable.ic_person_black_24dp,"이몽룡","010-2222-3333");
        addrs.add(vo);

        vo = new AddrBookVO(R.drawable.ic_person_black_24dp,"성춘향","010-2255-3333");
        addrs.add(vo);

        // 3. 아예 vo 자체를 만들지 않고 add()메서드에서 직접 생성자를 실행하는 방법
        addrs.add(new AddrBookVO(R.drawable.ic_person_black_24dp,"임꺽정","010-3322-3333"));

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName;
                String strTel ;

                strName = txt_name.getText().toString();
                strTel = txt_tel.getText().toString();

                if(strName.isEmpty()){
                    Toast.makeText(MainActivity.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(strTel.isEmpty()){
                    Toast.makeText(MainActivity.this,"전화번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                AddrBookVO vo = new AddrBookVO();
                vo.setAddr_image(R.drawable.ic_person_black_24dp);
                vo.setAddr_name(strName);
                vo.setAddr_tel(strTel);

                addrs.add(vo);

                addrRecyclerView.getAdapter().notifyDataSetChanged();

                // 입력이 끝나고 리스트가 보이도록 키보드를 감추기
                ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(),0);

                //inputbox도 비우자
                txt_name.setText("");
                txt_tel.setText("");
            }


        });

        addrRecyclerView.setAdapter(new AddrRecyclerAdapter(addrs));

        LinearLayoutManager lm = new LinearLayoutManager(this);

        // 좌우로 스크롤 하도록
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);

        // 상하로 스크롤 , 기본값
        lm.setOrientation(LinearLayoutManager.VERTICAL);

        addrRecyclerView.setLayoutManager(lm);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();
                addrs.remove(position);
                addrRecyclerView.getAdapter().notifyItemRemoved(position);

            }
        });

        helper.attachToRecyclerView(addrRecyclerView);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
