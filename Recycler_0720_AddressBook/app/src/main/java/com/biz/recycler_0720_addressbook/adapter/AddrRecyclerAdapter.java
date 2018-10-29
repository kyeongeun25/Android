package com.biz.recycler_0720_addressbook.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.biz.recycler_0720_addressbook.R;
import com.biz.recycler_0720_addressbook.vo.AddrBookVO;

import java.util.List;

public class AddrRecyclerAdapter extends RecyclerView.Adapter{

    List<AddrBookVO> addresses ;

    public AddrRecyclerAdapter(List<AddrBookVO> addresses) {
        this.addresses = addresses;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_addr, parent, false);

        return new AddrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AddrViewHolder addrViewHolder = (AddrViewHolder)holder ;
        AddrBookVO vo = addresses.get(position);

        addrViewHolder.item_image.setImageResource(vo.getAddr_image());
        addrViewHolder.item_name.setText(vo.getAddr_name());
        addrViewHolder.item_tel.setText(vo.getAddr_tel());

        // ((AddrViewHolder)holder).item_name.setText(addresses.get(position).getAddr_name());

    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    class AddrViewHolder extends RecyclerView.ViewHolder {

        ImageView item_image ;
        TextView item_name ;
        TextView item_tel ;

        public AddrViewHolder(View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.item_image);
            item_name = itemView.findViewById(R.id.item_name);
            item_tel = itemView.findViewById(R.id.item_tel);
        }
    }
}
