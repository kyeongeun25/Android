package com.gkdtkd562.bsc_img_project.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gkdtkd562.bsc_img_project.DetailViewActivity;
import com.gkdtkd562.bsc_img_project.MainActivity;
import com.gkdtkd562.bsc_img_project.R;
import com.gkdtkd562.bsc_img_project.database.BscVO;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class BscRecyclerViewAdapter extends RecyclerView.Adapter {

    List<BscVO> bscList;

    public BscRecyclerViewAdapter(List<BscVO> bscList) {
        this.bscList = bscList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {



        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bsc_item_view,viewGroup,false);
        return new BscRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final int p = i;
        BscRecyclerViewHolder bscHolder = (BscRecyclerViewHolder)viewHolder;
        bscHolder.textView.setText(bscList.get(i).getTitle());
        Picasso.get().load(bscList.get(i).getImagurl()).into(bscHolder.imageView);

        bscHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), DetailViewActivity.class);

                intent.putExtra("BSCLIST",(Serializable)bscList.get(p));

                v.getContext().startActivity(intent);
               // Toast.makeText(v.getContext(), bscList.get(p).getMemo(),Toast.LENGTH_LONG).show();
            }
        });
        // bscHolder.imageView

    }

    @Override
    public int getItemCount() {
        return bscList.size();
    }

    class BscRecyclerViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        CardView cardView;

        public BscRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.bsc_item_card);
            imageView = itemView.findViewById(R.id.bsc_item_image);
            textView = itemView.findViewById(R.id.bsc_item_title);

        }
    }
}
