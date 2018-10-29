package com.biz.photogallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.biz.photogallery.data.GalleryLoadHelper;
import com.biz.photogallery.data.GalleryVO;
import com.biz.photogallery.helper.PhotoViewAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    RecyclerView galleryView ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        galleryView = findViewById(R.id.photo_list_view);

        List<GalleryVO> galleryVOList = GalleryLoadHelper.getImageList(MainActivity.this);

        Log.d("사진개수 : ", ""+ galleryVOList.size());

        PhotoViewAdapter adapter = new PhotoViewAdapter(galleryVOList);
        galleryView.setAdapter(adapter);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        ((LinearLayoutManager)lm).setOrientation(LinearLayoutManager.HORIZONTAL);
        galleryView.setLayoutManager(lm);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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



}
