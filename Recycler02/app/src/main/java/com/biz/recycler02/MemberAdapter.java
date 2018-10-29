package com.biz.recycler02;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.biz.recycler02.biz.com.recycler02.vo.MemberVO;

import java.util.List;

// RecyclerView에 데이터 리스트를 표현하기위해 사용하는 도구
// 어떻게 어떤 데이터를 리스트로 표현할 것인지를 코드 구현
// 1. ViewHolder를 만들기 : onCreateViewHolder()에 구현
// 2. 데이터와 ViewHolder를 연결하기 : onBindViewHolder()에 구현
// 3. 데이터의 개수를 알려주기 : getItemCount()에 구현
public class MemberAdapter extends RecyclerView.Adapter {

    // 리스트를 받아서 보관할 객체변수
    List<MemberVO> members;

    // MainActivity에서 members를 전달하면 생성자에서 받아서 members 객체변수에 보관한다.
    public MemberAdapter(List<MemberVO> members) {
        this.members = members;
    }

    // inner클래스는 어떤 클래스(A 클래스) 내부에서 선언하여 A클래스에서만 사용하고 버릴 클래스
    class MyViewHolder extends RecyclerView.ViewHolder {
        // TODO : 나만의 ViewHolder 클래스
        // item_member.xml에 정의된 ImageView와 TextView를
        // Adapter에서 사용 할 수 있도록 정의하는 곳
        // Adapter가 데이터와 item_member.xml을 연결 할 수 있도록 준비하는 곳

        ImageView item_img;
        TextView item_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.item_img);
            item_name = itemView.findViewById(R.id.item_name);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // TODO : ViewHolder 객체를 생성하는 부분
        // ViewHolder를 생성하기 위해서
        // 안드로이드에 기본으로 제공하는 ViewHolder를 나만의 ViewHolder클래스를 다시 작성
        // 그리고 그 ViewHolder를 사용해서 viewHolder 객체를 생성

        // viewGroup.getContext()
        // rootView에 저장된, 보관된, 선언된 View에 대한 구체적인 정보를 끄집어 내는 method
        View item_view = LayoutInflater
                // rootView에 보관된 정보로 부터
                .from(viewGroup.getContext())
                // item_member.xml을 사용 할 수 있도록 준비(확장)
                .inflate(R.layout.item_member, viewGroup,
                        // 확장한 다음 가장 상위(OS 차원) 저장소에는 저장하지 마라
                        false);

        return new MyViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        // TODO : viewHolder와 실제 Data를 연결하는 부분

        // 연결할 DataList를 받아서 연결시킬 예정
        //      1. VO를 만들고
        //      2. VO를 담을 List를 만들고
        //      3. List를 여기로 받아올 예정
        //    1,2,3 항목은 Adapter 밖에 코드작성 한다.

        // RecyclerView.ViewHolder로 전달받은 Holder를 나만의 ViewHolder로 변환시키는 작업
        MyViewHolder myViewHolder = (MyViewHolder)viewHolder;

        // ImageView에게 Vacter Asset Icon을 전달
        myViewHolder.item_img.setImageDrawable(members.get(i).getImage());
        myViewHolder.item_name.setText(members.get(i).getName());

    }

    // 내가 RecyclerView에 표시할 데이터가 몇개인가를 알려준다.
    @Override
    public int getItemCount() {

        return members.size();
    }
}
