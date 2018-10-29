package com.biz.recycler01;

// RecyclerView에 표현할 데이터를 담을 VO 클래스
public class ItemVO {

    private String strName ;

    // default 생성자
    public ItemVO() {
    }

    // member변수 초기화 생성자
    public ItemVO(String strName) {
        this.strName = strName;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }
}
