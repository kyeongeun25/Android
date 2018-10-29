package com.biz.photo_memo.vo;

public class GradeVO {

    private long id; // DB와 연동하는 id값은 int보다는 큰 long으로 보통 선언
    private String number; // TEXT >> String
    private String name;
    private int kor;
    private int eng;
    private int math;

    public GradeVO() {
    }

    public GradeVO(long id, String number, String name, int kor, int eng, int math) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }
}
