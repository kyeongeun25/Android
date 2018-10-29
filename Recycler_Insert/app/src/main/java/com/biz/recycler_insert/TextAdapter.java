package com.biz.recycler_insert;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter {

    List<TextVO> textList ;

    public TextAdapter(List<TextVO> textList) {
        this.textList = textList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 1번 방법 : 일반적인 Java Coding
        LayoutInflater lf = LayoutInflater.from(parent.getContext()); // LayoutInflater를 만듦
        View view = lf.inflate(R.layout.item_view, parent, false);

        // 2번 방법 : 안드로이드식 Coding
        // Chainnig 기법 : 클래스.method().method().method();
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);

        return new TextHolder(view);

        // 3번 방법 : 내부에서 어떤 객체나 변수를 생성해서 메모리가 사용되는 것을 막는 방법
        // return new TextHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TextHolder textHolder = (TextHolder)holder;

        // textHolder.item_image.setImageDrawable();
        textHolder.item_image.setImageResource(textList.get(position).getItem_image());
        textHolder.item_text.setText(textList.get(position).getItem_text());

    }

    @Override
    public int getItemCount() {
        return textList.size();
    }

    class TextHolder extends RecyclerView.ViewHolder {

        ImageView item_image ;
        TextView item_text ;

        public TextHolder(@NonNull View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.item_image);
            item_text = itemView.findViewById(R.id.item_text);
        }
    }
}
