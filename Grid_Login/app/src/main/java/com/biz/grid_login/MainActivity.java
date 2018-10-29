package com.biz.grid_login;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText txt_email ;
    EditText txt_password ;

    Button btn_login ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = txt_email.getText().toString();
                String strPassword = txt_password.getText().toString();

                // Email 정규화 패턴
                String matchEmail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

                if(strEmail.isEmpty()){

                    /*
                        Toast나 SnackBar는 App의 진행을 방해하지 않는 popup Message라고 한다.
                        보통 일반적인 경우는 효율적으로 사용이 가능하지만,
                        어떤때는 사용자의 확실한 확인이 필요한 경우도 있다.
                        이럴때 사용하는 popup은 App의 진행을 잠시 멈추고 사용자의 확인을 받는다.
                        Android에서는 이런 popup을 Dialog라고 한다.
                     */
                    Toast.makeText(MainActivity.this, "Email 주소는 필수로 입력해야 합니다.",Toast.LENGTH_LONG).show();

                    // 다이얼로그 생성
                    AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);

                    // 다이얼로그의 제목을 설정
                    ab.setTitle("필수항목 누락");

                    // 나머지 항목 설정
                    ab.setMessage("Email을 입력해야 합니다.");
                    ab.setCancelable(false);    // 취소버튼 없애기
                    ab.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // 확인버튼을 클릭했을때 할 일들...
                        }
                    });

                    ab.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // 취소버튼을 클릭했을때 할 일들...
                        }
                    });
                    AlertDialog dialog = ab.create();
                    dialog.show();

                    return ;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()){
                    Toast.makeText(MainActivity.this,"이메일 형식이 잘못 되었습니다.",Toast.LENGTH_LONG).show();
                };

                if(strPassword.isEmpty()){
                    Toast.makeText(MainActivity.this,"비밀번호를 입력하세요.",Toast.LENGTH_LONG).show();
                    return ;
                }

            }
        });

    }

}
