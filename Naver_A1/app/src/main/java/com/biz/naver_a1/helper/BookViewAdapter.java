package com.biz.naver_a1.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.biz.naver_a1.R;

import java.util.List;

public class BookViewAdapter extends RecyclerView.Adapter{

    List<Naver_Book_item> items ;

    public BookViewAdapter(List<Naver_Book_item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent,false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{

        TextView txt_title;
        TextView txt_desc;
        ImageView img_book;

        public BookViewHolder(View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.book_item_title);
            txt_desc = itemView.findViewById(R.id.book_item_desc);
            img_book = itemView.findViewById(R.id.book_item_image);

        }
    }
}
