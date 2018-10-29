package com.biz.photogallery.data;

public class PhoneBookVO {

    private int id;
    private String phoneNumber ;    // 전화번호
    private String userName ;       // 이름
    private long photo_id;          // 전화번호에 연결된 사진 정보Key
    private long person_id;         // 전화번호 일련번호

    public PhoneBookVO() {
    }

    public PhoneBookVO(int id, String phoneNumber, String userName, long photo_id, long person_id) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.photo_id = photo_id;
        this.person_id = person_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(long photo_id) {
        this.photo_id = photo_id;
    }

    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(long person_id) {
        this.person_id = person_id;
    }
}
