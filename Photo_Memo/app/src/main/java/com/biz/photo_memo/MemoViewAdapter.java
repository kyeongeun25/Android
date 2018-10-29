package com.biz.photo_memo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.photo_memo.vo.MemoVO;

import org.w3c.dom.Text;

import java.util.List;

public class MemoViewAdapter extends RecyclerView.Adapter {

    List<MemoVO> memos ;

    public MemoViewAdapter(List<MemoVO> memos) {
        this.memos = memos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo_view, parent, false);

        return new MemoViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MemoViewHolder memoViewHolder = (MemoViewHolder)holder ;

        MemoVO vo = memos.get(position);
        memoViewHolder.txt_date.setText(vo.getDate());
        memoViewHolder.txt_memo.setText(vo.getMemo());

    }

    @Override
    public int getItemCount() {
        return memos.size();
    }

    class MemoViewHolder extends RecyclerView.ViewHolder {

        // item.xml에 설정된 항목들을 연결
        TextView txt_date ;
        TextView txt_memo ;

        public MemoViewHolder(View itemView) {
            super(itemView);

            txt_date = itemView.findViewById(R.id.item_date);
            txt_memo = itemView.findViewById(R.id.item_memo);

        }
    }
}
