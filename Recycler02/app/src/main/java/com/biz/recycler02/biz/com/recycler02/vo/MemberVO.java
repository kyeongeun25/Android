package com.biz.recycler02.biz.com.recycler02.vo;

import android.graphics.drawable.Drawable;

public class MemberVO {

    // Vector Asset을 담기위한 자료형(클래스)
    private Drawable image ;
    private String name ;

    public MemberVO() {
    }

    public MemberVO(Drawable image, String name) {
        this.image = image;
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
