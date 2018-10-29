package com.gkdtkd562.bsc_img_project.database;

import java.io.Serializable;

public class BscVO implements Serializable{

    private String key;
    private String bsc;
    private String genre;
    private String title;
    private String memo;
    private String link;
    private String imagurl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBsc() {
        return bsc;
    }

    public void setBsc(String bsc) {
        this.bsc = bsc;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImagurl() {
        return imagurl;
    }

    public void setImagurl(String imagurl) {
        this.imagurl = imagurl;
    }

    @Override
    public String toString() {
        return "BscVO{" +
                "key='" + key + '\'' +
                ", bsc='" + bsc + '\'' +
                ", genre='" + genre + '\'' +
                ", title='" + title + '\'' +
                ", memo='" + memo + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
