package com.biz.photo_memo.vo;

public class PhotoVO {

    private int id;
    private String title ;  // item_photo_view의 title과 연결

    // item_photo_view의 이미지 부분에는
    // url 값을 이용한 사진 불러오기 부분이 별도로 표시될 예정
    private String url ;    // phone에 있는 이미지가 저장된 주소값
    private String memo ;   // item_photo_view의 memo와 연결

    public PhotoVO() {
    }

    public PhotoVO(int id, String title, String url, String memo) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.memo = memo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
