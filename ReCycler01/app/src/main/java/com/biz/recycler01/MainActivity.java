package com.biz.recycler01;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    RecyclerView myRView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRView = findViewById(R.id.mylist);

        List<ItemVO> items = new ArrayList<ItemVO>();
        items.add(new ItemVO("홍길동"));
        items.add(new ItemVO("이몽룡"));
        items.add(new ItemVO("성춘향"));
        items.add(new ItemVO("임꺽정"));
        items.add(new ItemVO("장보고"));
        items.add(new ItemVO("장영실"));
        items.add(new ItemVO("정도전"));
        items.add(new ItemVO("홍길동"));
        items.add(new ItemVO("이몽룡"));
        items.add(new ItemVO("성춘향"));
        items.add(new ItemVO("임꺽정"));
        items.add(new ItemVO("장보고"));
        items.add(new ItemVO("장영실"));
        items.add(new ItemVO("정도전"));

        // RecyclerView를 메인화면에 표시하는 방법
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        // RecyclerView의 Layout 세팅
        myRView.setLayoutManager(layoutManager);
        myRView.setAdapter(new MyAdapter(items));

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }   // onCreate 끝


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
    };  // BottomNavi 이벤트 끝

    // innerClass로 Adapter를 생성
    class MyAdapter extends RecyclerView.Adapter {

        // VO리스트에 담겨있는 데이터를 Adapter로 불러오기 위한 장바구니
        List<ItemVO> items ;

        // MyAdapter 생성자
        // 외부에서 Adapter를 사용하기 위해 new 하면서 List를 전달해 줄 수 있도록 생성자를 만든다.
        public MyAdapter(List<ItemVO> items) {
            this.items = items;
        }

        // 아래에서 만든 ViewHolder를 Adapter에 주입시키는 부분

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            // activity_main.xml 같은 Layout 파일은 그 파일을 열어서 화면을 그릴 class 친구가 있다.
            // ==>> MainActivity.java

            // 그런데 RecyclerView에서 사용하려고 만든 item_view.xml은 그 파일을 열어 화면을 그릴 class 친구가 없다.
            // 그래서 item_view.xml을 불러 화면을 그려주기 위해서는 LayoutInflater 클래스의 도움을 받아야 한다.
            View item_view = LayoutInflater.from(viewGroup.getContext()) // viewGroup에서 정보를 추출
                                    .inflate(R.layout.item_view,viewGroup,false); // 화면에 그릴 준비를 하라
            return new ItemHolder(item_view);
            // item_view를 ItemHolder로 변환시켜서 return하라.
        }

        // 실제 데이터와 Adapter를 연동하는 부분
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            // RecyclerView.ViewHolder를 내가 만든 ItemHolder로 변환시킨다.
            ItemHolder myholder = (ItemHolder)viewHolder;

            myholder.strName.setText(items.get(i).getStrName());
        }

        // 현재 holder가 몇개 복제 되었나를 main에게 알려주는 method
        @Override
        public int getItemCount() {
            return items.size();
            // return값이 기본이 0인데 이 값이 0이면 list가 안보인다.
        }
    }   // Adapter 끝나는 부분

    // VO에 담겨있는 member변수를 Adapter와 연결하기 위한 설정
    // item_view.xml에 설정된 내부 view를 사용 할 수 있도록 선언과 생성을 ViewHolder의 생성자에 코드를 추가한다.
    class ItemHolder extends RecyclerView.ViewHolder {

        TextView strName ;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            strName = itemView.findViewById(R.id.txtName);
        }
    }



}   // MainActivity 끝
