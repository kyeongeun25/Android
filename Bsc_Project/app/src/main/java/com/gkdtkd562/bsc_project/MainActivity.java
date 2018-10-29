package com.gkdtkd562.bsc_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gkdtkd562.bsc_project.database.MemberVO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.security.interfaces.DSAKey;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    TextView txt_userid;
    TextView txt_password;
    TextView txt_nick;
    TextView txt_email;
    RadioGroup radioGroup;
    RadioButton txt_gender;
    Spinner txt_age;
    String str_age;

    Button btn_join;
    DatabaseReference databaseReference;
    MemberVO memberVO;

    Button btn_login;
    Button btn_welcome ;




    // 아이디 중복체크하기 위해서 데이터 검색 이벤트 선언
    ValueEventListener checkId = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
            while(child.hasNext()){
                if(child.next().getKey().equalsIgnoreCase(txt_userid.getText().toString())){
                    Toast.makeText(getApplicationContext(),"존재하는 아이디 입니다.",Toast.LENGTH_LONG).show();

                    // 이벤트가 자꾸 반복 실행되는 것을 방지하기 위해 이벤트를 제거
                    databaseReference.removeEventListener(this);
                    return;
                }
            }
            // 중복 ID가 없으면 새로운 member로 등록처리
            databaseReference.child(memberVO.getUserId()).setValue(memberVO);
            Toast.makeText(getApplicationContext(),"회원가입완료",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // memberVO 생성하고 이벤트에서 값을 세팅
        memberVO = new MemberVO();
        txt_userid = findViewById(R.id.txt_userid);
        txt_password = findViewById(R.id.txt_password);
        txt_nick = findViewById(R.id.txt_nick);
        txt_email = findViewById(R.id.txt_email);
//        txt_gender = findViewById(R.id.txt)

        radioGroup = findViewById(R.id.layout_gender);
        txt_age = findViewById(R.id.txt_age);
        txt_nick = findViewById(R.id.txt_nick);

        // Spinner 값을 추출하는 부분
        txt_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str_age = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginintent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginintent);
            }
        });

        btn_welcome = findViewById(R.id.btn_welcome);
        btn_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcome = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(welcome);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("members");
        btn_join = findViewById(R.id.btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txt_userid.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"사용자 ID를 입력해야 합니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                memberVO.setUserId(txt_userid.getText().toString());

                if(txt_password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"비밀번호를 입력해야 합니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                memberVO.setPassword(txt_password.getText().toString());


                // RadioGroup에서 현재 선택된 RadioButton Id를 추출하고
                // 그 Id를 이용해서 RadioButton 초기화
                int rid = radioGroup.getCheckedRadioButtonId();
                txt_gender = findViewById(rid);
                memberVO.setGender(txt_gender.getText().toString());

                if(str_age.equalsIgnoreCase("연령선택")){
                    Toast.makeText(MainActivity.this, "연령을 선택해주세요.",Toast.LENGTH_LONG).show();
                    return;
                }
                memberVO.setAge(str_age);

                // txt_gender(RadioButton) 초기화 된 후에는 TextView와 같은 방법으로 문자열을 추출 할 수 있다.
                Toast.makeText(MainActivity.this, txt_gender.getText().toString(), Toast.LENGTH_LONG).show();

                memberVO.setEmail(txt_email.getText().toString());
                memberVO.setNick(txt_nick.getText().toString());

                // firebase에 저장하기
                // members라는 table을 열어라 없으면 새로 생성
//                databaseReference = FirebaseDatabase.getInstance().getReference("members");

                // members table에 userid를 키값으로 하는 데이터를 생성하거나 이미 있으면 Update를 실행하라
//                databaseReference.child(memberVO.getUserId()).setValue(memberVO);

                // 이벤트 선언, 중복ID가 있는지 검사하도록
                databaseReference.addListenerForSingleValueEvent(checkId);

//                Toast.makeText(MainActivity.this,"회원가입 완료",Toast.LENGTH_LONG).show();
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
