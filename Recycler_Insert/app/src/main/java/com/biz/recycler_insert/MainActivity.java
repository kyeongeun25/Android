package com.biz.recycler_insert;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<TextVO> list ;
    int[] images ;
    RecyclerView textRecycler ;
    Button btnInsert ;
    TextInputEditText txt_memo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnInsert = findViewById(R.id.btnInsert);
        txt_memo = findViewById(R.id.txt_input);

        list = new ArrayList<TextVO>();

        images = new int[3];
        images[0] = R.drawable.ic_audiotrack_black_24dp;
        images[1] = R.drawable.ic_brightness_5_black_24dp;
        images[2] = R.drawable.ic_local_pizza_black_24dp;

        TextVO vo = new TextVO();
        vo.setItem_image(images[0]);
        vo.setItem_text("홍길동");
        list.add(vo);

        vo = new TextVO();
        vo.setItem_image(images[1]);
        vo.setItem_text("이몽룡");
        list.add(vo);

        vo = new TextVO();
        vo.setItem_image(images[2]);
        vo.setItem_text("성춘향");
        list.add(vo);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = txt_memo.getText().toString();

                // 값을 입력하지 않았으면
                if (txt.isEmpty()){

                    Toast.makeText(MainActivity.this, "문자열을 입력하세요", Toast.LENGTH_SHORT).show();
                    return ;

                }
                TextVO vo = new TextVO();
                vo.setItem_text(txt);

                int listLength = list.size();
                vo.setItem_image(images[listLength%3]);

                list.add(vo);
                textRecycler.getAdapter().notifyDataSetChanged();
            }
        });

        textRecycler = findViewById(R.id.text_list);
        textRecycler.setAdapter(new TextAdapter(list));
        textRecycler.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper helper ;
        helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // TODO : 밀기 했을때 처리할 코드

                final RecyclerView.ViewHolder v = viewHolder;

                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setTitle("삭제확인");
                ad.setMessage("정말 삭제할까요");
                ad.setCancelable(true);
                ad.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // 현재 밀기한 아이템 위치값
                        int position = v.getAdapterPosition();

                        // 리스트로 부터 position 위치의 아이템을 삭제하기 구현
                        list.remove(position);

                        // 화면을 새로 refresh
                        textRecycler.getAdapter().notifyItemRemoved(position);
                    }
                });

                ad.create().show();

            }
        });




        helper.attachToRecyclerView(textRecycler);


    }



}
