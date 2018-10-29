package com.gkdtkd562.bsc_img_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.gkdtkd562.bsc_img_project.database.BscVO;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailViewActivity extends AppCompatActivity {

    BscVO bscVO;

    ImageView detail_image;
    TextView detail_title;
    TextView detail_memo;
    TextView detail_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        bscVO = (BscVO) getIntent().getSerializableExtra("BSCLIST");

        detail_image = findViewById(R.id.detail_image);
        detail_title = findViewById(R.id.detail_title);
        detail_link = findViewById(R.id.detail_link);
        detail_memo = findViewById(R.id.detail_memo);

        detail_title.setText(bscVO.getTitle());
        detail_memo.setText(bscVO.getMemo());
        detail_link.setText(bscVO.getLink());

        Picasso.get().load(bscVO.getImagurl()).into(detail_image);
    }
}
