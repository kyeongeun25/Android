package com.gkdtkd562.bsc_img_project.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gkdtkd562.bsc_img_project.R;
import com.gkdtkd562.bsc_img_project.database.BscVO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BscListFragment extends Fragment {

    String page;
    String tabTitle;

    List<BscVO> bscList;
    RecyclerView bscRecyclerView ;

    public BscListFragment() {
    }

    @SuppressLint("ValidFragment")
    public BscListFragment(String page, String tabTitle){
        this.page = page;
        this.tabTitle = tabTitle;



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragment, container, false);

        bscRecyclerView = view.findViewById(R.id.bsc_list_view);

        bscList = new ArrayList<>();

        //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        /*
            firebase는 sql db처럼 where를 이용해서 조건 검색이 되지 않는다.
            필요한 데이터만 읽기 위해서는 편법을 사용해야 하는데

            일단, 조건을 부여할 컬럼으로 정렬을 하고
            정렬된 컬럼의 범위를 제한해서 검색을 실행한다.
         */

        String sort = page.toLowerCase(); //"bsc";
        if(page.equals("BSC")) sort = "bsc";
        if(page.equals("GENRE")) sort = "genre";

        FirebaseDatabase.getInstance().getReference("bscinfo").orderByChild(sort)   // 검색대상 컬럼으로 정렬을 하고
                .startAt(tabTitle) // 시작값을 제한
                .endAt(tabTitle)    // 끝값을 제한
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        bscList.clear();

                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            BscVO vo = ds.getValue(BscVO.class);
                            bscList.add(vo);

                            Log.d("Data",vo.toString());
                        }

                        BscRecyclerViewAdapter adapter = new BscRecyclerViewAdapter(bscList);
                        bscRecyclerView.setAdapter(adapter);
                        bscRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        return view;
    }
}
