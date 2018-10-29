package com.biz.db_phonebook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PhoneViewAdapter extends RecyclerView.Adapter{

    List<PhoneVO> phones;

    public PhoneViewAdapter(List<PhoneVO> phones) {
        this.phones = phones;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone_view,parent,false);

        return new PhoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PhoneViewHolder phoneViewHolder = (PhoneViewHolder)holder;
        phoneViewHolder.name.setText(phones.get(position).name);
        phoneViewHolder.phone.setText(phones.get(position).phone);

        // setImageBitmap()
        //  안드로이드에 기본으로 제공되는 이미지 변환(관련) method() 이다.
        //  이 method()는 성능상, 구조상 별로 실무에서는 많이 사용하지 안흔ㄴ다.
        //  하지만, 기본 method() 이기 때문에 한번 사용해 본다.
        // setImageBitmap() method는 전달되는 값이 null이면
        //  대책없이 app이 멈춰 버리므로
        //  최소한 값이 null 인가를 검사 한 후
        //  사용한다.
        if(phones.get(position).image != null) {
            phoneViewHolder.image.setImageBitmap(phones.get(position).image);
        }
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    class PhoneViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView phone;


        public PhoneViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_name);
            phone = itemView.findViewById(R.id.item_phone);
        }
    }

}
