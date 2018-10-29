package com.gkdtkd562.bsc_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gkdtkd562.bsc_project.database.MemberVO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class LoginActivity extends AppCompatActivity {

    TextView login_userid;
    TextView login_password;
    Button btn_login ;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseReference = FirebaseDatabase.getInstance().getReference("members");
        login_userid = findViewById(R.id.login_userid);
        login_password = findViewById(R.id.login_password);

        btn_login = findViewById(R.id.btn_login2);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                        while(child.hasNext()){

                            MemberVO vo = child.next().getValue(MemberVO.class);
                            if(vo.getUserId().equalsIgnoreCase(login_userid.getText().toString())){
                                if(!vo.getPassword().equals(login_password.getText().toString())){
                                    Toast.makeText(getApplicationContext(),"비밀번호가 틀립니다", Toast.LENGTH_LONG).show();
                                    return;
                                }
//                                Toast.makeText(getApplicationContext(),"로그인 성공", Toast.LENGTH_LONG).show();

                                // 새로운 intent를 생성하고
                                // 로그인 정보가 담긴 VO 넘겨주기
                                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                                intent.putExtra("MEMBER",vo);
                                startActivity(intent);
                                finish();
                                return;
                            }

                        }
                        Toast.makeText(getApplicationContext(),"아이디가 존재하지 않습니다.",Toast.LENGTH_LONG).show();
                        return;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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

}
