package com.biz.tablayout_array.models;

import java.util.List;

public class PagerVO {

    private String pageTitle;
    private List<TextVO> textList ;

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public List<TextVO> getTextList() {
        return textList;
    }

    public void setTextList(List<TextVO> textList) {
        this.textList = textList;
    }
}
