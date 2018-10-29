package com.biz.db_memo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.db_memo.vo.MemoVO;

import java.util.List;

public class MemoViewAdapter extends RecyclerView.Adapter {

    List<MemoVO> memos;

    public MemoViewAdapter(List<MemoVO> memos) {
        this.memos = memos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MemoViewHolder memoViewHolder = (MemoViewHolder)holder;
        memoViewHolder.txt_subject.setText(memos.get(position).getSubject());
        memoViewHolder.txt_date.setText(memos.get(position).getDate());
        memoViewHolder.txt_memo.setText(memos.get(position).getMemo());
    }

    @Override
    public int getItemCount() {
        return memos.size();
    }

    class MemoViewHolder extends RecyclerView.ViewHolder {

        TextView txt_subject ;
        TextView txt_date ;
        TextView txt_memo ;
        public MemoViewHolder(View itemView) {
            super(itemView);

            txt_subject = itemView.findViewById(R.id.item_txt_subject);
            txt_date = itemView.findViewById(R.id.item_txt_date);
            txt_memo = itemView.findViewById(R.id.item_txt_memo);

        }
    }
}
