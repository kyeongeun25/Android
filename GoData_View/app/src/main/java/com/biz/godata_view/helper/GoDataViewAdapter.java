package com.biz.godata_view.helper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.godata_view.R;
import com.biz.godata_view.WebViewActivity;
import com.biz.godata_view.models.GoDataListVO;

import java.util.List;

public class GoDataViewAdapter extends RecyclerView.Adapter{

    List<GoDataListVO> goDataLists ;
    Context context;

    public GoDataViewAdapter(List<GoDataListVO> goDataListVOList) {
        this.goDataLists = goDataListVOList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_go_data, parent, false);
        return new GoDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        GoDataViewHolder goDataViewHolder = (GoDataViewHolder)holder ;
        GoDataListVO vo = goDataLists.get(position);

        // Html.fromHtml(Text) : 문자열에 포함된 HTML 코드를 실제 기능으로 변환시키는 method
        goDataViewHolder.txt_item_servNm.setText(Html.fromHtml(vo.getServNm()));
        goDataViewHolder.txt_item_servDgst.setText(Html.fromHtml(vo.getServDgst()));
        goDataViewHolder.txt_item_junMnofNm.setText(Html.fromHtml(vo.getJurMnofNm()));
        goDataViewHolder.txt_item_servDtlLink.setText(Html.fromHtml(vo.getServDtlLink()));
        goDataViewHolder.go_link.setTag(vo.getServDtlLink());

        goDataViewHolder.go_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = (String)v.getTag();

                // activity를 하나 생성해서 activity에서 url 링크를 열기
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("HTML",url);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return goDataLists.size();
    }

    class GoDataViewHolder extends RecyclerView.ViewHolder {

        TextView txt_item_servNm;
        TextView txt_item_junMnofNm;
        TextView txt_item_servDgst;
        TextView txt_item_servDtlLink;
        TextView go_link;

        public GoDataViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();

            txt_item_servNm = itemView.findViewById(R.id.item_txt_servNm);
            txt_item_junMnofNm = itemView.findViewById(R.id.item_txt_jurMnofNm);
            txt_item_servDgst = itemView.findViewById(R.id.item_txt_servDgst);
            txt_item_servDtlLink = itemView.findViewById(R.id.item_txt_servDtlLink);
            go_link = itemView.findViewById(R.id.item_go_link);
        }
    }
}
