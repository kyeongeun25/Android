package com.biz.photo_memo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.biz.photo_memo.vo.PhotoVO;

import java.util.List;

/*
    item_photo_view.xml을 RecyclerView에 표현하기 위한 photoViewAdapter를 생성
 */
public class PhotoViewAdapter extends RecyclerView.Adapter {

    List<PhotoVO> photos;

    public PhotoViewAdapter(List<PhotoVO> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_view, parent, false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        PhotoViewHolder photoViewHolder = (PhotoViewHolder)holder;

        PhotoVO vo = photos.get(position);
        photoViewHolder.txt_photo_title.setText(vo.getTitle());
        //photoViewHolder.txt_photo_url.setImageResource(vo.getUrl());
        photoViewHolder.txt_photo_memo.setText(vo.getMemo());

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        TextView txt_photo_title ;
        ImageView txt_photo_url ;
        TextView txt_photo_memo;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            txt_photo_title = itemView.findViewById(R.id.item_photo_title);
            //txt_photo_url = .findViewById(R.id.item_photo_image);
            txt_photo_memo = itemView.findViewById(R.id.item_photo_memo);
        }
    }
}
