package com.biz.photogallery.helper;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.biz.photogallery.R;
import com.biz.photogallery.data.GalleryVO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoViewAdapter extends RecyclerView.Adapter {

    List<GalleryVO> galleryVOList;
    Context context ;

    public PhotoViewAdapter(List<GalleryVO> galleryVOList) {
        this.galleryVOList = galleryVOList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item_view, parent, false);

        context = parent.getContext();

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        PhotoViewHolder photoViewHolder = (PhotoViewHolder) holder;

        // 이미지 View에 내부에서 읽은 사진정보를 통해 실제 사진을 보여주는 일
        Picasso.get().load("file:"+galleryVOList.get(position).getImageUri())           // 이미지의 Uri를 건네준다.
                     .centerCrop()                                              // 사진을 화면 가운데부터 채워라
                     // .fit()                                                     // 가득 채워라
                     .resize(getScreenWidth(),getScreenHeight()/2)
                     .into(photoViewHolder.photo);                              // 보여줄곳

    }

    @Override
    public int getItemCount() {
        return galleryVOList.size();
    }

    // 현재 폰 화면의 가로 픽셀수(해상도)를 추출하는 method
    private int getScreenWidth(){
        DisplayMetrics metrics = new DisplayMetrics();
        Activity activityContext = (Activity)context;
        activityContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.widthPixels;
    }

    // 현재 폰 화면의 세로 픽셀수(해상도)를 추출하는 method
    private int getScreenHeight(){
        DisplayMetrics metrics = new DisplayMetrics();
        Activity activityContext = (Activity)context;
        activityContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.heightPixels;
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        ImageView photo ;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.item_photo);
        }
    }
}
