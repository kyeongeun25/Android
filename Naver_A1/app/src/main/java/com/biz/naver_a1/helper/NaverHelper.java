package com.biz.naver_a1.helper;

import java.util.List;

// naverHelper는 기본적으로 VO의 성질을 갖는다.
public class NaverHelper {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;

    private List<Naver_Book_item> items ;



    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public List<Naver_Book_item> getItems() {
        return items;
    }

    public void setItems(List<Naver_Book_item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "NaverHelper{" +
                "lastBuildDate='" + lastBuildDate + '\'' +
                ", total=" + total +
                ", start=" + start +
                ", display=" + display +
                ", items=" + items +
                '}';
    }
}
