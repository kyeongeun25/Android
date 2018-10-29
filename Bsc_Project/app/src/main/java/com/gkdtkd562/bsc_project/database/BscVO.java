package com.gkdtkd562.bsc_project.database;

public class BscVO {

    private String title;
    private String no;
    private String bsc;
    private String genre;
    private String link;
    private String memo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "BscVO{" +
                "title='" + title + '\'' +
                ", no='" + no + '\'' +
                ", bsc='" + bsc + '\'' +
                ", genre='" + genre + '\'' +
                ", link='" + link + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
