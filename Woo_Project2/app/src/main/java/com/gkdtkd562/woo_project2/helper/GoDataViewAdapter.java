package com.gkdtkd562.woo_project2.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gkdtkd562.woo_project2.R;
import com.gkdtkd562.woo_project2.database.GoDataVO;

import java.util.List;

public class GoDataViewAdapter extends RecyclerView.Adapter {

    List<GoDataVO> goDataList ;

    public GoDataViewAdapter(List<GoDataVO> goDataList) {
        this.goDataList = goDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // viewGroup = parent
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.go_data_item_view,viewGroup,false);

        return new GoDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        GoDataViewHolder goDataViewHolder = (GoDataViewHolder)viewHolder ;
        goDataViewHolder.txt_serv_nm.setText(goDataList.get(i).getServNm());
        goDataViewHolder.txt_jur_nm.setText(goDataList.get(i).getJurMnofNm());
        goDataViewHolder.txt_serv_dgst.setText(
                Html.fromHtml( goDataList.get(i).getServDgst() ) );

    }

    @Override
    public int getItemCount() {
        return goDataList.size();
    }

    class GoDataViewHolder extends RecyclerView.ViewHolder {

        TextView txt_serv_nm;
        TextView txt_jur_nm;
        TextView txt_serv_dgst ;

        public GoDataViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_serv_nm = itemView.findViewById(R.id.item_txt_serv_nm);
            txt_jur_nm = itemView.findViewById(R.id.item_txt_jur_nm);
            txt_serv_dgst = itemView.findViewById(R.id.item_txt_serv_dgst);

        }
    }

}