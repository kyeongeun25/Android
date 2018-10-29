package com.biz.cordilayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

// 버튼(btn3)이 클릭되었을때 event를 발생하면
// 2번째 Activity화면이 열리도록 한다.

public class MainActivity extends AppCompatActivity {

    //1. btn3을 java 코드내에서 사용 할 수 있도록
    //   비어있는 객체로 선언
    Button btn3;    // 여기이름은 xml에서 선언한 이름과는 관계 없음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 2. btn3을 사용 할 수 있도록 객체로 선언
        //    xml에서 선언한 btn3와 java 코드에서 선언한 btn3를 연결하는 것
        btn3 = findViewById(R.id.btn3);

        // 3. btn3에 클릭이벤트를 선언과 동시에 등록
        // 익명클래스 선언 : 1개의 버튼에서만 사용할 이벤트 등록하는 것
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    // 실행할이벤트를 정의한다.

                // 4. 2번째 Activity를 사용하기 위한 준비
                //      누가 누구를 intent로 생성 할 것인가를 알려줘야 한다.
                Intent i2 = new Intent(MainActivity.this, Main2Activity.class);

                // 5. 2번째 Activity를 시작(열기)한다.
                startActivity(i2);

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
