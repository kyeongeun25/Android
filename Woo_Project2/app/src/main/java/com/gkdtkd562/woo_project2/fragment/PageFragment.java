package com.gkdtkd562.woo_project2.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gkdtkd562.woo_project2.R;
import com.gkdtkd562.woo_project2.database.GoDataVO;
import com.gkdtkd562.woo_project2.helper.GetGoData;
import com.gkdtkd562.woo_project2.helper.GoDataViewAdapter;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PageFragment extends Fragment {

    List<GoDataVO> goDataList ;


    @SuppressLint("ValidFragment")
    public PageFragment(String mainTitle, String pageTitle) {
        try {
            GetGoData getGoData = new GetGoData();              // 데이터 가져오기
            goDataList = getGoData.execute(mainTitle, pageTitle).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public PageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);

        View view
                = inflater.inflate(R.layout.main_fragment,
                container,false);

        RecyclerView recyclerView
                = view.findViewById(R.id.go_data_recycler_view);
        recyclerView.setAdapter(
                new GoDataViewAdapter(goDataList));
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));

        return view;
    }
}