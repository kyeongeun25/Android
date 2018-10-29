package com.biz.myapp03;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView myname;
    private Button mybtn;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            if(item.getItemId() == R.id.navigation_home){
                mTextMessage.setText("홈");
                myname.setText("반갑습니다.");
                return true;
            }
            if(item.getItemId() == R.id.navigation_dashboard){
                mTextMessage.setText("데쉬모드");
                myname.setText("무엇이 필요한가요.");
                return true;
            }
            if(item.getItemId() == R.id.navigation_notifications){
                mTextMessage.setText("알림판");
                myname.setText("대한민국만세");
                return true;
            }

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
        /*
        layout 폴더에 있는 activity.main.xml 파일을 읽어서 그 파일이 포함하고 있는
        다양한 요소(위젯)들을 생성해서 사용할 준비를 하고 화면을 그려라.
         */
        setContentView(R.layout.activity_main);

        /*
        setContentView에서 생성한 화면에 포함된 요소중에서
        id 값이 message인 항목(객체)를 찾아서 mTextMessage를 생성하라.
        mTextMessage는 위에서 TextView 클래스로 선언된 객체변수이다.
         */
        mTextMessage = (TextView) findViewById(R.id.message);
        myname = findViewById(R.id.myname);
        mybtn = findViewById(R.id.btn);

        // mybtn이 클릭되었을때 실행 할 행동(event)를 등록
        mybtn.setOnClickListener(new BtnClick());
        // 이벤트를 등록해 두면 mybtn 버튼을 터치했을때 BtnClick 클래스에 정의된 onClick() 메서드가 호출되어 실행된다.

        myname.setOnClickListener(new TextClick());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationmy);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    // Button 클릭(터치)했을 때 사용할 이벤트를 선언(준비)
    class BtnClick implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            myname.setText("나는 버튼입니다.");
            mybtn.setText("나도 버튼입니다.");
        }
    }

    class TextClick implements TextView.OnClickListener{

        @Override
        public void onClick(View view) {
            myname.setText("나는 텍스트뷰입니다.");
        }
    }


} //MainActivity
