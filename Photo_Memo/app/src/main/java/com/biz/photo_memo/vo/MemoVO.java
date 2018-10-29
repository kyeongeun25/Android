package com.biz.photo_memo.vo;

public class MemoVO {

    String date ;
    String memo ;

    public MemoVO() {
    }

    public MemoVO(String date, String memo) {
        this.date = date;
        this.memo = memo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
