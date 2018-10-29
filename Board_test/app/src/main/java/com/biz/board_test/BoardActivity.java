package com.biz.board_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BoardActivity extends AppCompatActivity {

    Button btn_mo;
    Button btn_hu;
    Button btn_mytext ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_main);

        btn_mo = findViewById(R.id.btn_mo);
        btn_mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BoardActivity.this, "모집버튼입니다.", Toast.LENGTH_LONG).show();
            }
        });

    }

}
