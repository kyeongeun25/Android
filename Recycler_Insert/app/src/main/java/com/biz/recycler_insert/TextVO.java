package com.biz.recycler_insert;

import android.media.Image;

public class TextVO {

    private int item_image;
    private String item_text;

    public TextVO() {
    }

    public TextVO(int item_image, String item_text) {
        this.item_image = item_image;
        this.item_text = item_text;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }

    public String getItem_text() {
        return item_text;
    }

    public void setItem_text(String item_text) {
        this.item_text = item_text;
    }
}
