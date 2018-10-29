package com.gkdtkd562.bsc_project.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gkdtkd562.bsc_project.R;
import com.gkdtkd562.bsc_project.database.BscVO;

import java.util.List;

public class MainFragment extends Fragment {

    List<BscVO> bsclist;
    int buttonid;

    public MainFragment() {
    }

    @SuppressLint("ValidFragment")
    public MainFragment(List<BscVO> lists, int buttonid) {
        this.bsclist = lists;
        this.buttonid = buttonid;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//        return inflater.inflate(R.layout.fragment_main, container, false);

        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        return view ;
    }
}
