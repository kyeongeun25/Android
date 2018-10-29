package com.biz.myapp06;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 클래스의 member변수를 선언하는 영역
    // 여기에 클래스를 객체변수로 선언하면 값은 기본적으로 null로 생성
    // null로 선언된 객체변수는 어디에선가 반드시 정상적인 객체로 생성(new) 해주어야 접근(사용)할 수 있다.
    FloatingActionButton fb ; // null;

    Button btn1 ;
    Button btn2 ;
    Button btn3 ;

    // onCreate는 java에서 public static void main()과 같은 일을 하는 곳
    // 시작점, 진입점
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 안드로이드는 선언된 객체를 생성 할 때
        // onCreate method 내에서 실행한다.
        // *activity.xml(화면디자인 파일)에 작성된 요소들(widget 객체)은 new 키워드로 생성하지 않는다.
        // findViewById 메서드를 이용해서 객체를 생성한다.
        // 실제는 *activity.xml에 정의된 요소들은 이미 객체로 생성되어서 사용준비가 끝난 상태이다.
        // 이 객체들을 java 코드내로 가져오는 절차이다.
        fb = findViewById(R.id.fab);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        // R 값은 xml파일에 있는 widget의 id 값을 메모리 어딘가에 한꺼번에 보관하고 있으며
        // R.id.fab라는 값은 실제 fab객체의 정보가 보관된 메모리 주소

        // * xml에 있는 widget의 기능을 java에서 사용할때는
        // 1. 먼저 해당 widget과 같은 형식의 클래스로 객체를 선언하고
        // 2. onCreate method내에서 findViewById 메서드를 사용해서 객체를 초기화 해줘야 한다.

        // 이벤트를 등록하는 첫번째 방법
        // 1. inner class를 작성하고
        // 2. new 키워드로 생성해서 등록
        fb.setOnClickListener(new FbClick());

        btn1.setOnClickListener(new FbClick());
        btn2.setOnClickListener(new FbClick());
        btn3.setOnClickListener(new FbClick());

        // 이번트를 등록하는 두번째 방법
        // 1. set...Listener() 괄호 내에 OnClick... 인터페이스를 new 키워드로 직접 생성하고
        // 2. 그 내부에 method를 직접 정의한다.
        //    >> 무명(익명) 클래스 선언 방식이다.
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "두번째 토스트",Toast.LENGTH_SHORT).show();
            }
        });

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }

    // 안드로이드는 PC에 비해 성능, 메모리등이 매우 작기때문에
    // 가급적 객체, 변수들을 적게 만들어 쓰는 것이 좋다.

    // 또한 클래스도 한번 쓰고 버릴 것은 외부에 별도로 선언하지 않고
    // 클래스 내에 선언하여 사용한다.
    // inner class : 클래스 내에 선언된 클래스
    class FbClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            // view.getId()의 return값은 이벤트를 통해서 클릭(터치)된 버튼의 id값이다.
            int id = view.getId();
            String msg = "나는" + id + "버튼 입니다";


            // id 값을 비교해보면 어떤 버튼이 눌렸는지 알 수 있다.
            switch (id){
                case R.id.btn1 :
                    msg = "1번 버튼";
                    break;
                case R.id.btn2 :
                    msg = "2번 버튼";
                    break;
                case R.id.btn3 :
                    msg = "3번 버튼";
                    break;
            }
            msg = "나는" + msg + "입니다.";
            Button btn = (Button)view ;     // View 클래스인데 Button으로 변환시켜라
            msg += "\n" + btn.getText();
            // Button, TextView, EditText 등에 보이는 Text 문자열을 이벤트 핸들러 내에서 사용하려면
            // 먼저 view를 해당 widget으로 변환시키는 절차가 필요하다.

            // 한줄로 쓰기
            msg += "\n" + ((Button)view).getText();

            // 안녕하세요 라는 문자열 하나를 표시하고 더이상 할일이 없을 객체를 별도로 생성하지 않고
            // Channing 방법을 통해서 그냥 사용하는 것
            // class Channing 이다.
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }

}
