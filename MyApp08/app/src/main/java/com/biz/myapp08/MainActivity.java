package com.biz.myapp08;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// MainActivity에 View.OnClickListener 인터페이스를 implements 하면 onClick() 메서드가 생성되고
// MainActivity가 Click 이벤트를 자체적으로 처리 할 수 있게 된다.
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtView;
    Button btn1;
    TextInputEditText txtName;

    Button btn2;
    Button btn3;
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtView = findViewById(R.id.txtView);
        txtName = findViewById(R.id.txtName);
        btn1 = findViewById(R.id.btn1);

        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);


        // 익명클래스로
        // 나 혼자만 이벤트를 독점하겠다.
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // txtName에 입력된 문자열을 추출
                // 입력박스에 입력된 문자열을 추출할때는 마지막에 toString() 메서드를 붙여준다.
                String txtName1 = txtName.getText().toString();

                // txtView에 보이기
                txtView.setText(txtName1);
            }
        });

        // MainActivity가 이벤트 처리 클래스로 등록되었기 때문에
        // setOnClickListener에 this만 전달하면 등록이 완료
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            // onClick method를 모든 widget에 공통으로 사용 할 수 있도록 설계되어
            // 매개변수(파라메터, parameter)를 View로 설정해 두었다.
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // 형변환을 시켜서 사용한다.
                // ((Button)view).getText();
                //((TextView)view).getText();
                //((TextInputEditText)view).getText();
                ((FloatingActionButton)view).getBackgroundTintList();
            }
        });
    }

    // 이벤트 핸들러(Event Handler)
    @Override
    public void onClick(View view) {

        // 이벤트 핸들러를 여러 버튼이 공유 할 때는
        // view로 부터 id 값을 추출하여 어떤 버튼이 이벤트를 발생했는지 알아낸다.
        int id = view.getId();

        // TextInput 입력한 문자열 추출
        String txt = txtName.getText().toString();
        txt = txt + "\n" + ((Button)view).getText().toString();

        if(id == R.id.btn2){

            // 키보드 보이기기
           InputMethodManager mgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.showSoftInput(txtName.getCurrentFocus(), InputMethodManager.SHOW_FORCED);

            // Toast
            Toast.makeText(MainActivity.this,txt,Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.btn3){
            // SnackBar

            // SnackBar는 특성상 화면에 키보드가 있으면 보이질 않는다.
            // 그래서 강제로 키보드를 감추는 코드드
           InputMethodManager mgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(view.getWindowToken(),0);
            Snackbar.make(view,txt,Snackbar.LENGTH_LONG).show();
        }
        if(id == R.id.btn4){
            // Intent >> 안드로이드에서 화면을 구성하는 Activity*.java와 Activity*.xml 두개를 묶어서 인텐트라고 표현한다.
            //           인텐트를 연다 = 새로운 화면을 연다
            Intent i = new Intent(MainActivity.this,Main2Activity.class);

            // MainActivity(.this)에서
            // MainActivity(.class)를 열도록 준비하라

            // intent 열기
            // txtName에 있는 text(String)을 Main2Activity로 전달해서
            // Main2에 있는 TextView에 표시 해보자

            // 다른 Activity로 값을 전달하기
            i.putExtra("name", txt);
            //                  key, value 형태의 자료형
            i.putExtra("age", 30);
            i.putExtra("tel", "112");

            startActivity(i);
        }
    }
}
