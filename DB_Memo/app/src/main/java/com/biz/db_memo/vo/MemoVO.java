package com.biz.db_memo.vo;

public class MemoVO {

    private int id;
    private String subject;
    private String date ;
    private String memo;


    public MemoVO() {
    }

    public MemoVO(int id, String subject, String date, String memo) {
        this.id = id;
        this.subject = subject;
        this.date = date;
        this.memo = memo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    @Override
    public String toString() {
        return "MemoVO{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", date='" + date + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
