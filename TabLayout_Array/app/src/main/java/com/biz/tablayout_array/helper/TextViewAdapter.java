package com.biz.tablayout_array.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.tablayout_array.R;
import com.biz.tablayout_array.models.TextVO;

import java.util.List;

public class TextViewAdapter extends RecyclerView.Adapter {

    List<TextVO> textList ;

    public TextViewAdapter(List<TextVO> textList) {
        this.textList = textList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_view,parent,false);

        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextViewHolder textViewHolder = (TextViewHolder)holder ;
        textViewHolder.txt_title.setText(textList.get(position).getTitle());
        textViewHolder.txt_text.setText(textList.get(position).getMainText());
    }

    @Override
    public int getItemCount() {
        return textList.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        TextView txt_title;
        TextView txt_text ;

        public TextViewHolder(View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.item_title);
            txt_text = itemView.findViewById(R.id.item_text);


        }
    }
}
